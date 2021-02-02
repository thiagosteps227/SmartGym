package smartgym.resources;

import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.view.Viewable;

@Path("customers")
public class GymResource {
	
	private CustomerDataService dataService = CustomerDataService.getInstance();
		
	@GET
	@Path(value = "/{register.html}")
	public Viewable getRegisterPage(@PathParam("register.html") String registerPage) {
		return new Viewable(registerPage, this);
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomers() {
		System.out.println(dataService.getCustomerList());
        return dataService.getCustomerList();
    }
	
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String createCustomer(@FormParam("firstName") String firstName,
                                 @FormParam("lastName") String lastName,
                                 @FormParam("email") String email,
    							 @FormParam("password") String password,
    							 @FormParam("phoneNumber") String phoneNumber,
    							 @FormParam("gender") String gender,
    							 @FormParam("dateOfBirth") String dateOfBirth){
        return dataService.addCustomer(firstName, lastName, email, password, phoneNumber, gender, dateOfBirth);
    }
}
