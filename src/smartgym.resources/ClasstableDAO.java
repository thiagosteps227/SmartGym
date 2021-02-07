package com.ait.smartgym;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




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
