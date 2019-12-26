package si.fri.prpo.nakupovanje.api.v1;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(info = @Info(title = "Nakupovalni Seznami", version = "v1", contact = @Contact(), license = @License(), description = "PRPO API for managing shopping carts."), security = @SecurityRequirement(name = "openid-connect"), servers = @Server(url ="http://localhost:8080/v1"))
@ApplicationPath("v1")
public class NakupovalniSeznamApplication extends Application{
}
