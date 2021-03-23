package smartgym.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mysql.jdbc.PreparedStatement;

import smartgym.dao.ClasstableDAO;
import smartgym.dao.ConnectionHelper;
import smartgym.model.Classtable;
import smartgym.model.Timetable;
import smartgym.resources.ClasstableResource;
import smartgym.resources.TimetableResource;

class IntegrationTest {

	ClasstableResource classtableResource;
	Classtable classTable;
	
	Timetable timeTable;
	TimetableResource timetableResource;	

	@BeforeEach
	void setUp() throws Exception {
		//for classtable
		classtableResource = new ClasstableResource();
		ResetTable reset = new ResetTable();
		List<Classtable> classes = new ArrayList<Classtable>();
		classTable = new Classtable();
		classTable.setClassName("test");
		classTable.setPersonLimit(10);
		classTable.setPricePerClass(100);
		classTable.setPriceTwelveWeeks(1200);
		classes.add(classTable);
		classTable = new Classtable();
		classTable.setClassName("test2");
		classTable.setPersonLimit(20);
		classTable.setPricePerClass(200);
		classTable.setPriceTwelveWeeks(2400);
		classes.add(classTable);
		reset.resetTable(classes);
		
		//for timetable
		timetableResource = new TimetableResource();
		ResetTable resetTt = new ResetTable();
		List<Timetable> timetables = new ArrayList<Timetable>();
		timeTable = new Timetable();
		timeTable.setClassCode(9999);
		timeTable.setClassName("test");
		timeTable.setInstructor("David Black");
		timeTable.setWeekDay("Saturday");
		timeTable.setClassTime("12:00");
		timetables.add(timeTable);
		timeTable = new Timetable();
		timeTable.setClassCode(0000);
		timeTable.setClassName("test2");
		timeTable.setInstructor("Audrey Clark");
		timeTable.setWeekDay("Sunday");
		timeTable.setClassTime("10:00");
		timetables.add(timeTable);
		resetTt.resetTimeTable(timetables);
		
	}
	
	@Test
	void testGetAllClasses() {
		Response response= classtableResource.findAll();
		List<Classtable> list =  (List<Classtable>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals(2, list.size());
		Classtable classTable= list.get(0);
		assertEquals("test", classTable.getClassName());
		classTable = list.get(1);
		assertEquals("test2", classTable.getClassName());
		assertEquals(20, classTable.getPersonLimit());
		assertEquals(200, classTable.getPricePerClass());
		assertEquals(2400, classTable.getPriceTwelveWeeks());
		

	}
	
	@Test
	void testGetAllTimetable() {
		Response response= timetableResource.findAll();
		List<Timetable> list =  (List<Timetable>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals(2, list.size());
		Timetable timeTable= list.get(0);
		assertEquals("test2", timeTable.getClassName());
		timeTable = list.get(1);
		assertEquals("test", timeTable.getClassName());
		assertEquals("David Black", timeTable.getInstructor());
		assertEquals("Saturday", timeTable.getWeekDay());
		assertEquals("12:00", timeTable.getClassTime());
		

	}
	@AfterAll
	public static void backToNormal(){
		
	    ClasstableDAO dao = new ClasstableDAO();
	    dao.remove(1);
	    dao.remove(2);
	
		Classtable class1 = new Classtable();
		Classtable class2 = new Classtable();
		Classtable class3 = new Classtable();
		Classtable class4 = new Classtable();
		
		class1.setClassName("Fitness for Kids");
		class1.setPersonLimit(40);
		class1.setPricePerClass(5);
		class1.setPriceTwelveWeeks(60);
		
		class2.setClassName("Zumba");
		class2.setPersonLimit(30);
		class2.setPricePerClass(10);
		class2.setPriceTwelveWeeks(120);
		
		class3.setClassName("Spinning");
		class3.setPersonLimit(30);
		class3.setPricePerClass(15);
		class3.setPriceTwelveWeeks(165);
		
		class4.setClassName("Aerobics");
		class4.setPersonLimit(30);
		class4.setPricePerClass(20);
		class4.setPriceTwelveWeeks(240);
		
		class4.setClassName("Fitness for over 60s");
		class4.setPersonLimit(20);
		class4.setPricePerClass(5);
		class4.setPriceTwelveWeeks(60);
		
		dao.create(class1);
		dao.create(class2);
		dao.create(class3);
		dao.create(class4);
		
	}
}
