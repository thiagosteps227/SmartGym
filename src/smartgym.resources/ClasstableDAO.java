package com.ait.smartgym;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;

public class ClasstableDAO {
	
	   public List<Classtable> findAll() {
	        List<Classtable> list = new ArrayList<Classtable>();
	        Connection c = null;
	    	String sql = "SELECT * FROM classtable ORDER BY className";
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
	   
	   public Classtable findById(int id) {
	    	String sql = "SELECT * FROM classtable WHERE classID=?";
	    	Classtable classtable = null;
	    	Connection c = null;
	    	try {
	    		c = ConnectionHelper.getConnection();
	    		PreparedStatement ps = (PreparedStatement) c.prepareStatement(sql);
	    		ps.setInt(1, id);
	    		ResultSet rs = ps.executeQuery();
	    		if(rs.next()) {
	    			classtable = processRow(rs);
	    		}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		throw new RuntimeException(e);
	    	} finally {
	    		ConnectionHelper.close(c);
	    	}
	    	return classtable;
	    }
	   
	   public List<Classtable> findByName(String className) {
	    	List<Classtable> list = new ArrayList<Classtable>();
	    	Connection c= null;
	    	String sql = "SELECT * FROM classtable AS e WHERE UPPER(className) LIKE ? ORDER BY className";
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
	   
	   public Classtable create(Classtable classtable) {
	    	Connection c= null;
	    	java.sql.PreparedStatement ps = null;
	    	try {
	    		c = ConnectionHelper.getConnection();
	    		ps = c.prepareStatement("INSERT INTO classtable "
	    				+ "(className, personLimit, pricePerClass, priceTwelveWeeks)"
	    				+ "VALUES(?,?,?,?)", 
	    			new String[] { "classID"});
	    		ps.setString(1, classtable.getClassName());
	    		ps.setInt(2, classtable.getPersonLimit());
	    		ps.setInt(3, classtable.getPricePerClass());
	    		ps.setInt(4, classtable.getPriceTwelveWeeks());
	    		ps.executeUpdate();
	    		ResultSet rs = ps.getGeneratedKeys();
	    		rs.next();
	    		int classID = rs.getInt(1);
	    		classtable.setClassID(classID);
	    	} catch (Exception e){
	    		e.printStackTrace();
	    		throw new RuntimeException(e);
	    	} finally {
	    		ConnectionHelper.close(c);
	    	}
	    	
	    	return classtable;
	    }
	   
	   public Classtable update(Classtable classtable) {
	    	Connection c = null;
	    	try {
	    		c = ConnectionHelper.getConnection();
	    		PreparedStatement ps = (PreparedStatement) c.prepareStatement
	    				("UPDATE classtable SET className=?, personLimit=?, pricePerClass=?,"
	    				+ "priceTwelveWeeks=? WHERE classID=?");
	    		ps.setString(1, classtable.getClassName());
	    		ps.setInt(2, classtable.getPersonLimit());
	    		ps.setInt(3, classtable.getPricePerClass());
	    		ps.setInt(4, classtable.getPriceTwelveWeeks());
	    		ps.setInt(5, classtable.getClassID());
	    		ps.executeUpdate();
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    		throw new RuntimeException (e);
	    	} finally {
	    		ConnectionHelper.close(c);
	    	}
	    	return classtable;
	    }
	   
	   public boolean remove(int classID) {
	    	Connection c= null;
	    	try {
	    		c = ConnectionHelper.getConnection();
	    		PreparedStatement ps = (PreparedStatement) c.prepareStatement("DELETE FROM classtable WHERE classID =?");
	    		ps.setInt(1, classID);
	    		int count = ps.executeUpdate();
				return count == 1;
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		throw new RuntimeException (e);
	    	} finally {
	    		ConnectionHelper.close(c);
	    	}
	    }



	   protected Classtable processRow(ResultSet rs) throws SQLException {
	        Classtable classtable = new Classtable();
	        classtable.setClassID(rs.getInt("classID"));
	        classtable.setClassName(rs.getString("className"));
	        classtable.setPersonLimit(rs.getInt("personLimit"));
	        classtable.setPricePerClass(rs.getInt("pricePerClass"));
	        classtable.setPriceTwelveWeeks(rs.getInt("priceTwelveWeeks"));
	       
	        return classtable;
	    }
}
