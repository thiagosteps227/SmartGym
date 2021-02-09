package com.ait.smartgym;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TimetableDAO {

	public List<Timetable> findAll() {
        List<Timetable> list = new ArrayList<Timetable>();
        Connection c = null;
    	String sql = "SELECT * FROM timetable ORDER BY classID";
        try {
            c = ConnectionHelper.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return list;
    }

   protected Timetable processRow(ResultSet rs) throws SQLException {
        Timetable timetable = new Timetable();
        timetable.setClassCode(rs.getInt("classCode"));
        timetable.setClassName(rs.getString("className"));
        timetable.setInstructor(rs.getString("instructor"));
        timetable.setWeekDay(rs.getString("weekDay"));
        timetable.setClassTime(rs.getString("classTime"));
       
        return timetable;
    }
}
