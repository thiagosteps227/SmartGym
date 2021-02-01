package smartgym.resources;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.view.Viewable;

@Path("/register")
public class GymResource {
		
	@GET
	@Path(value = "register.html")
	public Viewable get(@PathParam("register.html") String template) {
		return new Viewable(template, this);
	}
	
	@POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Customer create(Customer customer) {
        return dao.create(customer);
    }
}
