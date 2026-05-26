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

	public static void booking(Connection con, Scanner scanner) throws SQLException {

	    scanner.nextLine();

	    System.out.println("Enter Guest Name");
	    String guestNameString = scanner.nextLine();

	    System.out.println("Enter Phone Number");
	    String phoneNumber = scanner.nextLine();

	    System.out.println("Enter Room Number");
	    int roomNumber = scanner.nextInt();

	    scanner.nextLine();

	    System.out.println("Enter Check In Date (yyyy-mm-dd)");
	    String checkInDateString = scanner.nextLine();

	    System.out.println("Enter Check Out Date (yyyy-mm-dd)");
	    String checkOutDate = scanner.nextLine();

	    String checkQuery =
	            "select * from room "
	            + "where Room_Number=? "
	            + "and is_available=true "
	            + "and is_clean=true";

	    PreparedStatement checkStmnt =
	            con.prepareStatement(checkQuery);

	    checkStmnt.setInt(1, roomNumber);

	    ResultSet rSet = checkStmnt.executeQuery();

	    if (rSet.next()) {

	        // INSERT GUEST

	        String guestQuery =
	                "insert into guest "
	                + "(guest_name, phone) "
	                + "values(?, ?)";

	        PreparedStatement guestStatement =
	                con.prepareStatement(
	                        guestQuery,
	                        PreparedStatement.RETURN_GENERATED_KEYS
	                );

	        guestStatement.setString(1, guestNameString);
	        guestStatement.setString(2, phoneNumber);

	        int guestRows = guestStatement.executeUpdate();

	        int guestId = 0;

	        if (guestRows > 0) {

	            ResultSet generatedKeys =
	                    guestStatement.getGeneratedKeys();

	            if (generatedKeys.next()) {

	                guestId = generatedKeys.getInt(1);

	            }

	        }

	        // INSERT BOOKING

	        String bookingQuery =
	                "insert into booking "
	                + "(guest_id, Room_number, "
	                + "Check_in_date, Check_out_date, Status) "
	                + "values(?,?,?,?,?)";

	        PreparedStatement pStatement =
	                con.prepareStatement(bookingQuery);

	        pStatement.setInt(1, guestId);
	        pStatement.setInt(2, roomNumber);

	        pStatement.setString(3, checkInDateString);
	        pStatement.setString(4, checkOutDate);

	        pStatement.setString(5, "Booked");

	        int bookingRows = pStatement.executeUpdate();

	        if (bookingRows > 0) {

	            // UPDATE ROOM AVAILABILITY

	            String updateRoomAvailability =
	                    "update room "
	                    + "set is_available=false "
	                    + "where Room_Number=?";

	            PreparedStatement pStatement2 =
	                    con.prepareStatement(updateRoomAvailability);

	            pStatement2.setInt(1, roomNumber);

	            int roomRows = pStatement2.executeUpdate();

	            if (roomRows > 0) {

	                System.out.println("Booking Successful");
	                System.out.println("Generated Guest ID: " + guestId);

	            } else {

	                System.out.println(
	                        "Booking updated but room availability not updated"
	                );

	            }

	        } else {

	            System.out.println("Booking Failed");

	        }

	    } else {

	        System.out.println(
	                "Room is not available or not cleaned"
	        );

	    }
	}
	
	public static void viewBooking( Connection con) throws SQLException {
		String viewQuery="select * from booking";
		PreparedStatement pStatement=con.prepareStatement(viewQuery);
		ResultSet rSet=pStatement.executeQuery();
	System.out.println(".........................................................................................................................................................................");

		System.out.printf("%-15s %-15s %-20s %-15s %-15s %-15s %-15s\n", "Booking Id","Booking Name","Phone Number","Room Number","Check in Date","Check Out Date","Status");
		System.out.println(".........................................................................................................................................................................");
		while(rSet.next()) {
		System.out.printf("%-15d %-15s %-20s %-15s %-15s %-15s %-15s\n" ,rSet.getInt("Booking_Id"),rSet.getString("Booking_name"),rSet.getString("Phone_number"),rSet.getInt("Room_number"),rSet.getString("Check_in_date"),rSet.getString("Check_out_date"),rSet.getString("Status"));

		System.out.println(".........................................................................................................................................................................");
		}

		
	}
	
	/*public static void DeleteBooking (Connection con, Scanner scanner) throws SQLException {
		System.out.println("Enter the booking id you need to Cancel");
		int bookingId=scanner.nextInt();
		System.out.println("Enter the reason to Cancel Booking");
		scanner.nextLine();
		String reason=scanner.nextLine();
		String updateCannccelReason="update booking set Status =?,Remarks =? where Booking_Id =?";
		PreparedStatement pStatement=con.prepareStatement(updateCannccelReason);
		pStatement.setInt(3, bookingId);
		pStatement.setString(1, "Cancelled");
		pStatement.setString(2, reason);
	
		int deleteRows=pStatement.executeUpdate();
		
		if (deleteRows>0) {
			System.out.println("Cancelled successfully");

			
		}else {
			System.out.println("Cancellation failed.");

		}

//cancellation working but not updating room availability
		
		
		
	}*/
	public static void cancelBooking(Connection con, Scanner scanner) throws SQLException {

	    System.out.println("Enter the booking id you need to Cancel");
	    int bookingId = scanner.nextInt();
	    scanner.nextLine();

	    System.out.println("Enter the reason to Cancel Booking");
	    String reason = scanner.nextLine();

	    String findQuery = "select Room_number from booking where Booking_Id=? and Status='Booked'";

	    PreparedStatement pStatement = con.prepareStatement(findQuery);
	    pStatement.setInt(1, bookingId);

	    ResultSet rSet = pStatement.executeQuery();

	    if (rSet.next()) {

	        int roomNumber = rSet.getInt("Room_number");

	        String updateQuery = "update booking set Status=?, Remarks=? where Booking_Id=?";

	        PreparedStatement pStatement2 = con.prepareStatement(updateQuery);
	        pStatement2.setString(1, "Cancelled");
	        pStatement2.setString(2, reason);
	        pStatement2.setInt(3, bookingId);

	        int cancelRows = pStatement2.executeUpdate();

	        if (cancelRows > 0) {

	            String roomAvailableSetQueryString = "update room set is_available=true where Room_Number=?";

	            PreparedStatement pStatement3 = con.prepareStatement(roomAvailableSetQueryString);
	            pStatement3.setInt(1, roomNumber);

	            int roomAvailable = pStatement3.executeUpdate();

	            if (roomAvailable > 0) {
	                System.out.println("Cancellation is successful. Room is available now");
	            } else {
	                System.out.println("Cancellation successful, but room availability not updated");
	            }

	        } else {
	            System.out.println("Cancellation failed");
	        }

	    } else {
	        System.out.println("Booking not found or already cancelled");
	    }
	}
	public static void checkOut (Connection con,Scanner scanner) throws SQLException {
		System.out.println(".............Guest Check Out.............");
		System.out.println("Enter Booking Id");

		int bookingId=scanner.nextInt();
		String fetchQuery="select Room_number from booking where Booking_Id=?";
		PreparedStatement fetchStatement =con.prepareStatement(fetchQuery);
		fetchStatement.setInt(1, bookingId);
		ResultSet rSet =fetchStatement.executeQuery();
		if (rSet.next()) {
			int roomNumber=rSet.getInt("Room_number");
			String UpdateBookingQuery="update booking set Status =? where Booking_Id=?";
			PreparedStatement bookingQueryUpdateStatement=con.prepareStatement(UpdateBookingQuery);
			bookingQueryUpdateStatement.setString(1, "Check Out");
			bookingQueryUpdateStatement.setInt(2, bookingId);
		
		int bookingStatus=bookingQueryUpdateStatement.executeUpdate();
		String updateRoom="update room set is_available=false,is_clean=false where Room_Number=?";
		PreparedStatement roomUpdateStatement=con.prepareStatement(updateRoom);
		roomUpdateStatement.setInt(1,roomNumber );
			
			int roomUpdateRows=roomUpdateStatement.executeUpdate();
			
			if (bookingStatus>0&&roomUpdateRows>0) {
				System.out.println("Check Out is successfull");
				System.out.println("Room marked for house keeping to clean");


				
			}
			else {
				System.out.println("Check out failed");

			}
			
		}
		else {
			System.out.println("Booking Id not found");

		}
		

	}
}

