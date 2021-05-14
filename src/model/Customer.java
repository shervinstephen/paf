package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf-db", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertCustomer(String CID, String name, String details) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			String query = " insert into Customer(`CID`,`name`,`details`)  values (?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, CID);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, details);

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readCustomer() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			output = "<table border='1'><tr><th>Customer Code</th>" + "<th>Customer name</th>" + "<th>Customer details</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from Customer";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String CID = rs.getString("CID");
				String name = rs.getString("name");
				String details = rs.getString("details");

				output += "<tr><td>" + CID + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + details + "</td>";
				
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary' data-rid='" + CID + "'></td>"
					//	+ "<td><form method='post' action='Project.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger' data-rid='" + CID + "'>";
					//	+ "<input name='PID' type='hidden' value='" + CID + "'>" + "</form></td></tr>";
				
			}
			con.close();

			output += "</table>";
			
			String newCustomer = readCustomer();
			 output = "{\"status\":\"success\", \"data\": \"" +
					 newCustomer + "\"}";
			
		} catch (Exception e) {
			//output = "Error while reading the Customer.";
			output = "{\"status\":\"error\", \"data\":\"Error while reading the Customer.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateCustomer(String CID, String name, String details) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			String query = "UPDATE Customer SET name=?,details=? WHERE CID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, name);
			preparedStmt.setString(2, details);
			preparedStmt.setString(3, CID);

			preparedStmt.execute();
			con.close();
			String newCustomer = readCustomer();
			 output = "{\"status\":\"success\", \"data\": \"" +
					 newCustomer + "\"}";
		} catch (Exception e) {
			//output = "Error while updating the Customer.";
			output = "{\"status\":\"error\", \"data\":\"Error while updating the Customer.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteCustomer(String CID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			String query = "delete from Customer where CID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, CID);

			preparedStmt.execute();
			con.close();
			String newCustomer = readCustomer();
			 output = "{\"status\":\"success\", \"data\": \"" +
					 newCustomer + "\"}";
		} catch (Exception e) {
		//	output = "Error while deleting the Customer.";
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the Customer.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
