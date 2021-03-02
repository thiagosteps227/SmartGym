package smartgym.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import smartgym.model.Customer;



public class CustomerDao {
	public int create(Customer customer) {
		Connection c = null;
		PreparedStatement ps = null;
		int rows;
		try {
			c = ConnectionHelper.getConnection();
			ps = c.prepareStatement("INSERT INTO Customer ( userName,firstName, lastName, password, phoneNumber,"
					+ "gender,  dateOfBirth ,email, activity) VALUES (?, ?,?,?,?,?,?,?, ?)");
			ps.setString(1, customer.getUserName());
			ps.setString(2, customer.getFirstName());
			ps.setString(3, customer.getLastName());
			ps.setString(4, customer.getPassword());
			ps.setString(5, customer.getPhoneNumber());
			ps.setString(6, customer.getGender());
			ps.setString(7, customer.getDateOfBirth());
			ps.setString(8, customer.getEmail());
			ps.setString(9, customer.getActivity());
			rows = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
		return rows;
	}

	public List<Customer> findAllCustomers() {
		// this method should connect to DB and get all the customers from DB.
		List<Customer> list = new ArrayList<Customer>();
		Connection c = null;
		String sql = "select * from Customer";

		try {
			c = ConnectionHelper.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setUserName(rs.getString("userName"));
				customer.setFirstName(rs.getString("firstName"));
				customer.setLastName(rs.getString("lastName"));
				
				customer.setPassword(rs.getString("password"));
				customer.setPhoneNumber(rs.getString("phoneNumber"));
				customer.setEmail(rs.getString("email"));
				customer.setGender(rs.getString("gender"));
				customer.setDateOfBirth(rs.getString("dateOfBirth"));
				customer.setActivity("activity");
				list.add(customer);
			}

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}

		return list;
	}
	public String retrievePassword(String userName) {
		String pwd = null;
		Connection c = null;
		String sql = "SELECT password FROM Customer where userName=?";
		try {
			// c = ConnectionHelper.getConnection();
			c = ConnectionHelper.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1,  userName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pwd = rs.getString("password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
		return pwd;
	}

}
