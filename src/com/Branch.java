package com;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Branch {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electricityproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertBranch(String BranchName, String Category, String Location, String PowerCapacity, String HeadEngineer) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into branch1(`BranchID`,`BranchName`,`Category`,`Location`,`PowerCapacity`,`HeadEngineer`)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, BranchName);
			preparedStmt.setString(3, Category);
			preparedStmt.setString(4, Location);
			preparedStmt.setString(5, PowerCapacity);
			preparedStmt.setString(6, HeadEngineer);
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			
			/**--------------------------------------**/
			//String newBranch = readBranch();
			 //output = "{\"status\":\"success\", \"data\": \"" + newBranch + "\"}";
			 /**--------------------------------------**/
			output = "Inserted successfully";
		} catch (Exception e) {
			/**--------------------------------------**/
			 //output = "{\"status\":\"error\", \"data\": \"Error while inserting the branch details.\"}";
			/**--------------------------------------**/
		output = "Error while inserting the branch.";
			System.err.println(e.getMessage());
			/**-------------------------------------------**/
//			System.out.println(e.getMessage());
//			System.out.println(e);
//			e.printStackTrace();
		}
		return output;
	}

	public String readBranch() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Branch ID</th><th>Branch Name</th><th>Category</th><th>Location</th><th>Power Capacity</th><th>Head Engineer</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from branch1";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String BranchID = Integer.toString(rs.getInt("BranchID"));
				String BranchName = rs.getString("BranchName");
				String Category = rs.getString("Category");
				String Location = rs.getString("Location");
				String PowerCapacity = rs.getString("PowerCapacity");
				String HeadEngineer = rs.getString("HeadEngineer");

				// Add into the html table
				output += "<tr><td>" + BranchID + "</td>";
				output += "<td>" + BranchName + "</td>";
				output += "<td>" + Category + "</td>";
				output += "<td>" + Location + "</td>";
				output += "<td>" + PowerCapacity + "</td>";
				output += "<td>" + HeadEngineer + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-info' data-BID='" + BranchID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-BID='" + BranchID + "'></td></tr>";
				
				 
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the branch.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateBranch(String BranchID, String BranchName, String Category, String Location, String PowerCapacity, String HeadEngineer) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a Prepared statement
			String query = "UPDATE branch1 SET BranchName=?,Category=?,Location=?,PowerCapacity=?,HeadEngineer=?" + "WHERE BranchID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, BranchName);
			preparedStmt.setString(2, Category);
			preparedStmt.setString(3, Location);
			preparedStmt.setString(4, PowerCapacity);
			preparedStmt.setString(5, HeadEngineer);
			preparedStmt.setInt(6, Integer.parseInt(BranchID));

			// execute the statement
			preparedStmt.execute();
			con.close();
		 /**-------------------------------------**/
			//output = "Updated successfully"; 
		 String newBranch = readBranch(); 
		 output = "{\"status\":\"success\", \"data\": \"" + newBranch + "\"}"; 
			  
		//	output = "Updated successfully";
		} catch (Exception e) {
			/**----------------------------------------------------------------------------------------------**/
			output = "{\"status\":\"error\", \"data\": \"Error while updating the branch details.\"}";
			  
//			output = "Error while updating the branch.";
			System.err.println(e.getMessage());
			/**----------------------------**/
			//System.out.println(e);
			
		}

		return output;
	}

	public String deleteBranch(String BranchID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from branch1 where BranchID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(BranchID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			/**--------------------------------------------------------------------**/
//		String newBranch = readBranch(); 
//			 output = "{\"status\":\"success\", \"data\": \"" + newBranch + "\"}"; 
//			 
			  

		output = "Deleted successfully";
		} catch (Exception e) {
			/**------------------------------------------------**/
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the branch details.\"}";
			 
			output = "Error while deleting the branch.";
			System.err.println(e.getMessage());
			/**--------------------------------------------------**/
//			System.out.println(e);
 
		}

		return output;
	}

}
 
