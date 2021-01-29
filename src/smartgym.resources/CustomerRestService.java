package smartgym.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Date;
import java.util.List;

@Path("/customer")
public class CustomerRestService {
    private CustomerDataService dataService = CustomerDataService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomers() {
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
                                 @FormParam("dateOfBirth") Date dateOfBirth,
                                 @FormParam("newId") String newId) {
        return dataService.addCustomer(firstName, lastName, email, password, phoneNumber, gender, dateOfBirth, newId);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("id") String id) {
        Customer customer = dataService.getCustomerById(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND)
                      .build();
        } else {
            return Response.ok()
                           .entity(customer)
                           .build();
        }


    }
}