package Hospicore;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RoomManagement  {
	public static void addRoom(Connection con , Scanner scanner) throws SQLException{
		System.out.println("Enter room Number");
		int RoomNumber=scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter Room type");
		String roomTypeString=scanner.nextLine();
		System.out.println("Enter Room Price");
		double Price =scanner.nextDouble();
		String insertQuery = "insert into room (Room_Number,Room_Type,Price,is_available,is_clean) values(?,?,?,?,?);";
		PreparedStatement ps=con.prepareStatement(insertQuery);
		ps.setInt(1, RoomNumber);
		ps.setString(2, roomTypeString);
		ps.setDouble(3, Price);
		ps.setBoolean(4, true);
		ps.setBoolean(5, true);
		int rows=ps.executeUpdate();
		if(rows>0) {
			System.out.println("Room added successfully");

		}else {
			System.out.println("Room adding failed");

		}
	



		
	}
	public static void viewRoom(Connection con  ) throws SQLException{
		String viewQuery = "select * from room;";
		PreparedStatement pStatement=con.prepareStatement(viewQuery);
		ResultSet resultSet=pStatement.executeQuery();
		System.out.println("|......................................................................................|");

		System.out.printf(" %-15s %-15s %-20s %-15s %-15s\n",
				
				"Room Number",
				"Price",
				"Room Type",
				"Is available",
				"Is clean");
		System.out.println("|......................................................................................|");


		while (resultSet.next()) {
			System.out.printf(" %-15d %-15.2f %-20s %-15b %-15b\n",

					resultSet.getInt("Room_Number") ,
					resultSet.getDouble("Price"),

							resultSet.getString("Room_Type") ,
							 resultSet.getBoolean("is_available"),
							 resultSet.getBoolean("is_clean"));
			System.out.println("|......................................................................................|");

		
		
	}

}
	public static void updateRoom(Connection connection , Scanner scanner) throws SQLException {
		System.out.println("Enter Room Number");
		int RoomNumber=scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter new room Type");
		String nRoomType=scanner.nextLine();
		System.out.println("Enter New price");
		double nPrice=scanner.nextDouble();



		
		String updateQuery="update room set Room_Type=?,Price=? where  Room_Number =?";
		PreparedStatement pStatement=connection.prepareStatement(updateQuery);
		pStatement.setString(1, nRoomType);
		pStatement.setDouble(2, nPrice);
		pStatement.setInt(3, RoomNumber);
		int rows=pStatement.executeUpdate();
		if (rows>0) {
			System.out.println("Room update successfull");

			
		}
		else {
			System.out.println("Romm update failed.");

		}
		
		
		
		
	}
	public static void deleteRoom(Connection con,Scanner scanner) throws SQLException{
		System.out.println("Enter room number ");
		int roomNumber=scanner.nextInt();
		String deleteQuery="delete from room where Room_Number=?";
		PreparedStatement pStatement =con.prepareStatement(deleteQuery);
		pStatement.setInt(1, roomNumber);
		int rows=pStatement.executeUpdate();
		if (rows>0) {
			System.out.println("Room deleted successfully.");

		}else {
				System.out.println("Failed to delete room");

			}
		}

		
	}
	

