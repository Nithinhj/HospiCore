package Hospicore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookingManagement {
	public static void viewAvailableRoom(Connection con) throws SQLException {
		String viewQueryString = "select * from room where is_available= true";
		PreparedStatement pStatement = con.prepareStatement(viewQueryString);
		ResultSet resultSet = pStatement.executeQuery();
		System.out.printf(" %-15s %-15s %-20s %-15s %-15s\n",

				"Room Number", "Price", "Room Type", "Is available", "Is clean");
		System.out.println("|......................................................................................|");
		while (resultSet.next()) {
			System.out.printf(" %-15d %-15.2f %-20s %-15b %-15b\n",

					resultSet.getInt("Room_Number"), resultSet.getDouble("Price"),

					resultSet.getString("Room_Type"), resultSet.getBoolean("is_available"),
					resultSet.getBoolean("is_clean"));
			System.out.println(
					"|......................................................................................|");

		}

	}

	public static void booking(Connection con, Scanner scanner ) throws SQLException {
		scanner.nextLine();
		System.out.println("Enter Guest Name");
		String guestNameString=scanner.nextLine();
		
				System.out.println("Enter phone Number");
				String phoneNumber=scanner.nextLine();
				System.out.println("Enter room Number");
				int roomNumber=scanner.nextInt();
				scanner.nextLine();
				System.out.println("Enter check in date");
				String checkInDateString= scanner.nextLine();
				System.out.println("Enter check out date ");
				String checkOutDate=scanner.nextLine();
				String checkQuery="select * from room where Room_Number=? and is_available=true and is_clean=true";
				
				PreparedStatement checkStmnt=con.prepareStatement(checkQuery);
				checkStmnt.setInt(1, roomNumber);
				ResultSet rSet=checkStmnt.executeQuery();
				
			
				if (rSet.next()) {
					
						
						


						String BookingQuery="insert into booking (Booking_name,Phone_number,Room_number,Check_in_date,Check_out_date,Status)values(?,?,?,?,?,?)";
						
						
						PreparedStatement pStatement=con.prepareStatement(BookingQuery);
						pStatement.setString(1, guestNameString);
						pStatement.setString(2, phoneNumber);
						pStatement.setInt(3,roomNumber);
						pStatement.setString(4, checkInDateString);
						pStatement.setString(5, checkOutDate);
						pStatement.setString(6,"Booked");
					
						
						int BookingRows=pStatement.executeUpdate();
						if (BookingRows>0) {
							String updateRoomAvailability="update room set is_available=false where Room_Number=?";
							
							PreparedStatement pStatement2=con.prepareStatement(updateRoomAvailability);
							pStatement2.setInt(1, roomNumber);
						
							int roomRows=pStatement2.executeUpdate();
							if (roomRows>0) {
								System.out.println("Booking Successfull");

								
							}
							else {
								System.out.println("Booking Updated but room availability is not updated");

							}
							

							
							
						}
						else {
							System.out.println("Booking Failed");

						}
											
					
				}
				
				
				else {
					System.out.println("Room is not Available or not cleaned");
				}
	}
}
