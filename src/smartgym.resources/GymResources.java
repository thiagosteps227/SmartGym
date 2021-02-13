package com.ait.smartgym;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("/customer")
public class GymResources {
	CustomerDAO dao = new CustomerDAO();

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/createCustomer")
	public int create(Customer customer) {
		System.out.println("Customer");
		int row = dao.create(customer);
		return row;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Customer> findAll() {
		System.out.println("findAll");
		return dao.findAllCustomers();
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
	

}
