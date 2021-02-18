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

import com.ait.validation.ErrorMessage;
import com.ait.validation.TimetableValidationException;
import com.ait.validation.TimetableValidator;

@Path("/timetable")
public class TimetableResource {
	
	TimetableValidator validator = new TimetableValidator();
	TimetableDAO timetableDAO =  new TimetableDAO();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Timetable> findAll() {
		return timetableDAO.findAll();
	}
	
	@GET @Path("{classCode}")
	@Produces({ MediaType.APPLICATION_JSON})
	//http://localhost:8080/SmartGym/rest/timetable/{classCode}
	public Response findById(@PathParam("classCode") int classCode) {
		Timetable timetable = timetableDAO.findById(classCode);
		return Response.status(200).entity(timetable).build();
	}
	
	@GET @Path("search/{query}")
	@Produces({MediaType.APPLICATION_JSON})
	//http://localhost:8080/SmartGym/rest/timetable/search/{query}
	public Response findByName(@PathParam("query") String className) {
		List<Timetable> timetable = timetableDAO.findByName(className);
		return Response.status(200).entity(timetable).build();
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	//http://localhost:8080/SmartGym/rest/timetable
	public Response create(Timetable timetable) {
			Response response;
			try {
				List<Timetable> list = timetableDAO.findAll();
				validator.validateTimetable(timetable);
				timetableDAO.create(timetable);
				response = Response.status(200).entity(timetable).build();
				
			} catch (TimetableValidationException e) {
				ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
				response = Response.status(403).entity(errorMessage).build();
			}
			return response;
	}
	
	@PUT @Path("{classCode}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	//http://localhost:8080/SmartGym/rest/timetable/{classCode}
	public Response update(Timetable timetable) {
		timetableDAO.update(timetable);
		return Response.status(201).entity(timetable).build();
	}
	
	@DELETE @Path("{classCode}")
	@Produces({MediaType.APPLICATION_JSON})
	//http://localhost:8080/SmartGym/rest/timetable/{classCode}
	public Response remove(@PathParam("classCode") int classCode) {
		timetableDAO.remove(classCode);
		return Response.status(204).build();
	}
}
	
