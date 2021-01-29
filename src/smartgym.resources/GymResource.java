package smartgym.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/customer")
public class GymResource {
		
	@GET
	@Produces("text/plain")
	public String getIndex() {
		return "index.html";
	}
}
