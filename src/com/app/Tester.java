package com.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Tester {

	public static void main(String[] args) {
		try {
			// load JDBC driver : OPTIONAL
			// Class.forName("com.mysql.cj.jdbc.Driver");
			
			// need to import jdbc driver library
			// for this case mysql-connector-java-jar file
		     // right click on library -> build path -> libraries -> add external JARs -> brows downloaded path 
			
			// establish DB connection
			// API of java.sql.DriverManager : Connection getConnection(url,usernm,pwd)
			// throws SQLExc.
			String url = "jdbc:mysql://localhost:3306/advjava?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";

			// step 1 -> create a fixed connection
			try (Connection cn = DriverManager.getConnection(url, "root", "root")) {
				System.out.println("Connected to DB " + cn);

				// step 2 -> createstatement(DQL) / preparedStatement(DML,DDL) / CallableStatement(func/procedure)
				PreparedStatement pst1 = cn.prepareStatement("select * from student");

				// step 3-> create a result set -> executeQuery() / executeUpdate() / execute() 
				try (ResultSet rst = pst1.executeQuery()) {
					/*
					 * int userId, String firstName, String lastName, String email, String password,
					 * double regAmount, Date regDate, String role
					 */
					while (rst.next())

						System.out.println(
						// getting the info from DB , getType(column position);
						// column positions are as per resultSet 		
						" name " + rst.getInt(1) + " name -> " + rst.getString(2) + " " + rst.getString(3));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("main over...");

	}
}
