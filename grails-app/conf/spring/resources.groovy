import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.apache.camel.impl.DefaultCamelContext;

import routes.OpcMessageRoute;


// Place your Spring DSL code here
beans = {
	camelContext(DefaultCamelContext){ bean ->
	
		bean.initMethod = 'start'
		bean.destroyMethod = 'stop'
	}
}
