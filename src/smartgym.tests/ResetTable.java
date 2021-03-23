package smartgym.tests;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;
import smartgym.dao.ConnectionHelper;
import smartgym.model.Classtable;
import smartgym.model.Timetable;

public class ResetTable {

	public void resetTable(List<Classtable> classes) throws Exception {
		String driver = null;
		Connection c = null;
		Statement statement = null;
		PreparedStatement ps = null;
		String url;
		try {
			c = ConnectionHelper.getConnection();
            Statement s = c.createStatement();
			String query = "TRUNCATE TABLE classtable";
			s.execute(query);
			for (Classtable classTable : classes) {

				ps = (PreparedStatement) c.prepareStatement("INSERT INTO classtable (className,personLimit,pricePerClass,"
						+ "priceTwelveWeeks) VALUES (?,?,?,?)",
						new String[] { "classID" });
				ps.setString(1, classTable.getClassName());
				ps.setInt(2, classTable.getPersonLimit());
				ps.setInt(3, classTable.getPricePerClass());
				ps.setInt(4, classTable.getPriceTwelveWeeks());
				ps.executeUpdate();
			}
		} catch (Exception e) {
			throw e;
		}

	}
	
	public void resetTimeTable(List<Timetable> timetables) throws Exception {
		String driver = null;
		Connection c = null;
		Statement statement = null;
		PreparedStatement ps = null;
		String url;
		try {
			c = ConnectionHelper.getConnection();
            Statement s = c.createStatement();
			String query = "TRUNCATE TABLE timetable";
			s.execute(query);
			for (Timetable timeTable : timetables) {

				ps = (PreparedStatement) c.prepareStatement("INSERT INTO timetable (classCode,className, instructor,weekDay,"
						+ "classTime) VALUES (?,?,?,?,?)");
				ps.setInt(1, timeTable.getClassCode());
				ps.setString(2, timeTable.getClassName());
				ps.setString(3, timeTable.getInstructor());
				ps.setString(4, timeTable.getWeekDay());
				ps.setString(5, timeTable.getClassTime());
				ps.executeUpdate();
			}
		} catch (Exception e) {
			throw e;
		}

	}
	
}
