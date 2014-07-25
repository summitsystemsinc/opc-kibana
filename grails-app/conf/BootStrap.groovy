import grails.util.Holders;

import org.apache.camel.CamelContext;
import org.apache.commons.logging.LogFactory;
import routes.OpcMessageRoute

class BootStrap {
	private static final log = LogFactory.getLog("opc-kibana.BootStrap")
    
	CamelContext camelContext;
	
	def init = { servletContext ->
		File dataDir = new File("${Holders.config.appDataPath}");
		log.info "Data Dir: ${dataDir.absolutePath}"
		if(!dataDir.exists()){
			log.info "Data Dir did not exist, creating..."
			dataDir.mkdirs();
			log.info "Success!"
		}
		File appLocalProperties = new File("${Holders.config.appLocalProperties}")
		log.info "Looking for local properties file: ${Holders.config.appLocalProperties}"
		if(!appLocalProperties.exists()){
			log.info "Creating empty local properties file at: ${Holders.config.appLocalProperties}"
			if(!appLocalProperties.createNewFile()){
				log.warn "Unable to create local properties file at: ${appLocalProperties.absolutePath}"
			}
		}
		
		File appLocalGroovy = new File("${Holders.config.appLocalGroovy}");
		log.info "Looking for local groovy config file: ${Holders.config.appLocalGroovy}"
		if(!appLocalGroovy.exists()){
			log.info "Creating empty local groovy config file at: ${Holders.config.appLocalGroovy}"
			if(!appLocalGroovy.createNewFile()){
				log.warn "Unable to create local groovy file at: ${appLocalGroovy.absolutePath}"
			}
			appLocalGroovy.withWriter { out ->
				out.writeLine("opcKibana {");
				out.writeLine("");
				out.writeLine("}");
			}
		}
		
		camelContext.addRoutes(new OpcMessageRoute());
    }
    def destroy = {
    }
}
