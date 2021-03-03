package smartgym.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smartgym.model.Classtable;
import smartgym.resources.ClasstableResource;

class IntegrationTest {

	ClasstableResource classtableResource;
	Classtable classTable;

	@BeforeEach
	void setUp() throws Exception {
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

	@AfterEach
	void tearDown() throws Exception {
	}

}
