package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Funder {

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

	public String insertFunder(String FID, String name, String amount, String phone, String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			String query = " insert into funder(`FID`,`name`,`amount`,`phone`,`email`)  values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, FID);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(amount));
			preparedStmt.setString(4, phone);
			preparedStmt.setString(5, email);
			preparedStmt.execute();
			con.close();
			//output = "Inserted successfully";
			
			String newFunders = readFunders();
			 output = "{\"status\":\"success\", \"data\": \"" +
					 newFunders + "\"}";
			
		} catch (Exception e) {
			//output = "Error while inserting the funder.";
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the funder.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readFunders() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			output = "<table border='1'><tr><th>FID </th><th>name</th><th>amount</th>" + "<th>phone</th>"
					+ "<th>email</th>" + "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from funder";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String FID = rs.getString("FID");
				String name = rs.getString("name");
				String amount = Double.toString(rs.getDouble("amount"));
				String phone = rs.getString("phone");
				String email = rs.getString("email");

				output += "<tr><td>" + FID + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + email + "</td>";
				
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary' data-rid='" + FID + "'></td>"
						
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger' data-rid='" + FID + "'>";
						
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Funder.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateFunder(String FID, String name, String amount, String phone, String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			String query = "UPDATE funder SET name=?,amount=?,phone=?,email=? WHERE FID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, name);
			preparedStmt.setDouble(2, Double.parseDouble(amount));
			preparedStmt.setString(3, phone);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, FID);
			preparedStmt.execute();
			con.close();
			//output = "Updated successfully";
			
			String newFunders = readFunders();
			 output = "{\"status\":\"success\", \"data\": \"" +
					 newFunders + "\"}";
			
		} catch (Exception e) {
			//output = "Error while updating the Funder.";
			output = "{\"status\":\"error\", \"data\":\"Error while updating the Funder.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteFunder(String FID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			String query = "delete from funder where FID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, FID);

			preparedStmt.execute();
			con.close();
			//output = "Deleted successfully";
			
			String newFunders = readFunders();
			 output = "{\"status\":\"success\", \"data\": \"" +
					 newFunders + "\"}";
			 
		} catch (Exception e) {
			//output = "Error while deleting the Funder.";
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the Funder.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
