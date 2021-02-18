package com.ait.smartgym;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

public class TimetableDAO {

	public List<Timetable> findAll() {
        List<Timetable> list = new ArrayList<Timetable>();
        Connection c = null;
    	String sql = "SELECT * FROM timetable ORDER BY classCode";
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
	
	public Timetable findById(int classCode) {
    	String sql = "SELECT * FROM timetable WHERE classCode=?";
    	Timetable timetable = null;
    	Connection c = null;
    	try {
    		c = ConnectionHelper.getConnection();
    		PreparedStatement ps = (PreparedStatement) c.prepareStatement(sql);
    		ps.setInt(1, classCode);
    		ResultSet rs = ps.executeQuery();
    		if(rs.next()) {
    			timetable = processRow(rs);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new RuntimeException(e);
    	} finally {
    		ConnectionHelper.close(c);
    	}
    	return timetable;
    }
	
	public List<Timetable> findByName(String className) {
    	List<Timetable> list = new ArrayList<Timetable>();
    	Connection c= null;
    	String sql = "SELECT * FROM timetable AS e WHERE UPPER(className) LIKE ? ORDER BY className";
    	try {
    		c = ConnectionHelper.getConnection();
    		java.sql.PreparedStatement ps = c.prepareStatement(sql);
    		ps.setString(1, "%"+className.toUpperCase()+"%");
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()) {
    			list.add(processRow(rs));
    		}
    	} catch (SQLException e){
    		e.printStackTrace();
    		throw new RuntimeException(e);
    	} finally {
    		ConnectionHelper.close(c);
    	}
    	return list;
    }
	
	public Timetable create(Timetable timetable) {
    	Connection c= null;
    	java.sql.PreparedStatement ps = null;
    	try {
    		c = ConnectionHelper.getConnection();
    		ps = c.prepareStatement("INSERT INTO timetable "
    				+ "(classCode, className, instructor, weekDay, classTime)"
    				+ "VALUES(?,?,?,?,?)");
    		ps.setInt(1, timetable.getClassCode());
    		ps.setString(2, timetable.getClassName());
    		ps.setString(3, timetable.getInstructor());
    		ps.setString(4, timetable.getWeekDay());
    		ps.setString(5, timetable.getClassTime());
    		ps.executeUpdate();
    		
    	} catch (Exception e){
    		e.printStackTrace();
    		throw new RuntimeException(e);
    	} finally {
    		ConnectionHelper.close(c);
    	}
    	
    	return timetable;
    }
	
	public Timetable update(Timetable timetable) {
    	Connection c = null;
    	try {
    		c = ConnectionHelper.getConnection();
    		PreparedStatement ps = (PreparedStatement) c.prepareStatement
    				("UPDATE timetable SET className=?, instructor=?,"
    				+ "weekDay=?, classtime=? WHERE classCode=?");
    		ps.setString(1, timetable.getClassName());
    		ps.setString(2, timetable.getInstructor());
    		ps.setString(3, timetable.getWeekDay());
    		ps.setString(4, timetable.getClassTime());
    		ps.setInt(5, timetable.getClassCode());
    		ps.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw new RuntimeException (e);
    	} finally {
    		ConnectionHelper.close(c);
    	}
    	return timetable;
    }
	
	public boolean remove(int classCode) {
    	Connection c= null;
    	try {
    		c = ConnectionHelper.getConnection();
    		PreparedStatement ps = (PreparedStatement) c.prepareStatement("DELETE FROM timetable WHERE classCode =?");
    		ps.setInt(1, classCode);
    		int count = ps.executeUpdate();
			return count == 1;
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new RuntimeException (e);
    	} finally {
    		ConnectionHelper.close(c);
    	}
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
