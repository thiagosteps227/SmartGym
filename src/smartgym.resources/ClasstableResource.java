package com.ait.smartgym;

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

@Path("/classtable")
public class ClasstableResource {
	
	ClasstableDAO dao = new ClasstableDAO();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public List<Classtable> findAll(){
		System.out.println("findAll");
		return dao.findAll();
	}
	
	@GET @Path("{classID}")
	@Produces({ MediaType.APPLICATION_JSON})
	//http://localhost:8080/SmartGym/rest/classtable/{id}
	public Response findById(@PathParam("classID") int classID) {
		Classtable classtable = dao.findById(classID);
		return Response.status(200).entity(classtable).build();
	}
	
	@GET @Path("search/{query}")
	@Produces({MediaType.APPLICATION_JSON})
	//http://localhost:8080/SmartGym/rest/classtable/search/{query}
	public Response findByName(@PathParam("query") String className) {
		List<Classtable> classtable = dao.findByName(className);
		return Response.status(200).entity(classtable).build();
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	//http://localhost:8080/SmartGym/rest/classtable
	public Response create(Classtable classtable) {
		Classtable classtableObj = dao.create(classtable);
		return Response.status(201).entity(classtableObj).build();
	}
	
	@PUT @Path("{classID}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	//http://localhost:8080/SmartGym/rest/classtable/{classID}
	public Response update(Classtable classtable) {
		dao.update(classtable);
		return Response.status(201).entity(classtable).build();
	}
	
	@DELETE @Path("{classID}")
	@Produces({MediaType.APPLICATION_JSON})
	//http://localhost:8080/SmartGym/rest/classtable/{classID}
	public Response remove(@PathParam("classID") int classID) {
		dao.remove(classID);
		return Response.status(204).build();
	}
	

}
