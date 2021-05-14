package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Project {
			
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
	
	public String insertProject(String PID, String type,String name,String details) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			String query = " insert into project(`PID`,`type`,`name`,`details`)  values (?,?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, PID);
			preparedStmt.setString(2, type);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, details);

			preparedStmt.execute();
			con.close();
			String newProject = readProject();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newProject + "\"}";
		} catch (Exception e) {
			//output = "Error while inserting the Project.";
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Project.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readProject() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			output = "<table border='1'><tr><th>Project ID</th><th>Project Type</th><th>Project Name</th>"
					+ "<th>Project details</th>" + "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from project";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String PID = rs.getString("PID");
				String type = rs.getString("type");
				String name = rs.getString("name");
				String details = rs.getString("details");

				output += "<tr><td>" + PID + "</td>";
				output += "<td>" + type + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + details + "</td>";

				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='Project.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='PID' type='hidden' value='" + PID + "'>" + "</form></td></tr>";
			}
			con.close();

			output += "</table>";
			
			String newProject = readProject();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newProject + "\"}";
			 
		} catch (Exception e) {
			//output = "Error while reading the Project.";
			output = "{\"status\":\"error\", \"data\":\"Error while reading the Project.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateProject(String PID, String type,String name,String details) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			String query = "UPDATE Project SET type=?,name=?,details=? WHERE PID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, type);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, details);
			preparedStmt.setString(4, PID);

			preparedStmt.execute();
			con.close();
			String newProject = readProject();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newProject + "\"}";
		} catch (Exception e) {
			//output = "Error while updating the Project.";
			output = "{\"status\":\"error\", \"data\":\"Error while updating the Project.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteProject(String PID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			String query = "delete from Project where PID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, PID);

			preparedStmt.execute();
			con.close();
			String newProject = readProject();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newProject + "\"}";
		} catch (Exception e) {
			output = "Error while deleting the Project.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
