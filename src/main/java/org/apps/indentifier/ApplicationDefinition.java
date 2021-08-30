package org.apps.indentifier;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Application;

@ApplicationScoped
@OpenAPIDefinition(info = @Info(title = "Lucky's Client Lookup",
        description = "Basic app for managing clients.",
        version = "1.0.0", contact = @Contact(name = "Lucky Khoza", email = "lucky.khoseni@gmail.com")),
        servers = {
                @Server(description = "LOCAL Environment", url = "http://localhost:8080/") })
public class ApplicationDefinition extends Application {

}
