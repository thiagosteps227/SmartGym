package com.ait.smartgym;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/timetable")
public class TimetableResource {
	
	TimetableDAO timetableDAO =  new TimetableDAO();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Timetable> findAll() {
		return timetableDAO.findAll();
	}
	
}
	
