package smartgym.resources;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import smartgym.dao.CustomerDao;
import smartgym.model.Classtable;
import smartgym.model.Customer;



@Path("/customer")
public class GymResources {
	CustomerDao dao = new CustomerDao();

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/createCustomer")
	public int create(Customer customer) {
		int row = dao.create(customer);
		return row;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response findAll() {
		List<Customer> list = dao.findAllCustomers();
	return Response.status(200).entity(list).build();
	}
	
	@GET @Path("{customerID}")
	@Produces({ MediaType.APPLICATION_JSON})
	//http://localhost:8080/SmartGym/rest/customer/{id}
	public Response findById(@PathParam("customerID") int customerID) {
		Customer customer = dao.findById(customerID);
		return Response.status(200).entity(customer).build();
	}
	
	@PUT @Path("{customerID}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	//http://localhost:8080/SmartGym/rest/customer/{customerID}
	public Response update(Customer customer) {
		dao.update(customer);
		return Response.status(201).entity(customer).build();
	}
	
	@POST
	@Path("{authenticate}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response retrievePassword(Customer customer) {
		System.out.println("retrieve password");
		String userName=customer.getUserName();
		String password=customer.getPassword();
		System.out.println("User name:"+userName);
		System.out.println("Password:"+password);
		String dbpassword=dao.retrievePassword(userName);
		
		
		if(password.equals(dbpassword)) {
			return Response.status(200).entity("OK").build();
		}else {
			return Response.status(204).entity("FAIL").build();
		}
		
	}
	
	@DELETE @Path("{customerID}")
	@Produces({MediaType.APPLICATION_JSON})
	//http://localhost:8080/SmartGym/rest/customer/{customerID}
	public Response remove(@PathParam("customerID") int customerID) {
		dao.remove(customerID);
		return Response.status(204).build();
	}
	

}
