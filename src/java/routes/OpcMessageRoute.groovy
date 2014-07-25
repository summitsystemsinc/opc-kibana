package routes

import java.text.DateFormat;

import grails.converters.JSON
import grails.util.Holders;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.model.dataformat.JsonLibrary;

import com.google.gson.GsonBuilder;


class OpcMessageRoute extends RouteBuilder {
	def grailsApplication

	//private static final com.google.gson.Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd’T'HHmmss.SSSZ").create();
	private static final com.google.gson.Gson gson = new GsonBuilder().setDateFormat(DateFormat.SHORT).create();
	@Override
	void configure() {
		def config = Holders.config.opcKibana

		config.groups.each { group ->
			def path = group.path;
			if(!path.startsWith("/")){
				path = "/" + path;
			}

			String uriString = "opcda2:opcdaTest${group.path}?delay=${group.delay}&host=${group.host}&" +
					"clsId=${group.clsid}" +
					"&username=${group.user}&password=${group.password}&domain=${group.domain}&valuesOnly=false";

			from(uriString).process {Exchange exchange ->
				def newBody = [:];
				newBody["@timestamp"] = new Date();
				newBody["headers"] = exchange.getIn().getHeaders()
				
				exchange.getIn().getBody().each { item ->
					def key = item.key;
					//Get the last part of the key, on Matrikon, it is delimited by a "."
					String[] parts = key.split("\\.");
					key = parts[parts.length-1];
					newBody[key] = item.value
				}
				exchange.getIn().setBody(newBody);
			}.process { Exchange exchange ->
				exchange.getIn().setBody(exchange.getIn().getBody() as JSON)
			}.
			to("elasticsearch://local?operation=INDEX&indexName=opc-data&indexType=${group.indexType}");
		}
	}
}