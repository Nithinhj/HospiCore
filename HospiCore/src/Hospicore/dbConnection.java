package Hospicore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnection {
	public static void main(String[] args) throws Exception {
		String url="jdbc:mysql://localhost:3306/Hospicore";
		String userName="root";
		String password="root123";
		String Query= "select * from user";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded successfully");

		}catch (Exception e) {
			System.out.println(e.getMessage());
// TODO: handle exception
		}
		
		try {
			Connection con=DriverManager.getConnection(url,userName,password);
			System.out.println("Connection established successfully");

			Statement stmt=con.createStatement();
			ResultSet rSet= stmt .executeQuery(Query);
			
		} catch (SQLException e) {
			
			// TODO: handle exception
		}
		 
	}
	

}
