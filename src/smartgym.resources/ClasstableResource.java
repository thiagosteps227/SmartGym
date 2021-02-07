package com.ait.smartgym;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/classtables")
public class ClasstableResource {
	
	ClasstableDAO dao = new ClasstableDAO();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
	public List<Classtable> findAll(){
		System.out.println("findAll");
		return dao.findAll();
	}
	

}
