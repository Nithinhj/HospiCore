package Hospicore;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class BillingManagement {
	
	public static void GenerateFrontOfficeBill(Connection con,Scanner scanner) throws SQLException {
		System.out.println("Enter Booking Id");
		int bookingId=scanner.nextInt();
		String fetchQuery="select b.Booking_Id ,b.guest_id,b.Booking_name,b.Room_number,b.Check_in_date,b.Check_out_date,g.guest_name,g.phone,r.Price from booking b join guest g on b.guest_id=g.guest_id join room r on b.Room_number=r.Room_Number where b.Booking_Id=?";
PreparedStatement pStatement =con.prepareStatement(fetchQuery);
pStatement.setInt(1, bookingId);
ResultSet rSet =pStatement.executeQuery();
if(rSet.next()) {
	int bookingId1=rSet.getInt("Booking_Id");
	
		int guestId=rSet.getInt("guest_id");
		int roomNumber=rSet.getInt("Room_number");
	 String guestName=rSet.getString("guest_name");
	 String phone=rSet.getString("phone");
	 double roomPrice = rSet.getDouble("Price");

     Date checkInDate = rSet.getDate("Check_in_date");
     Date checkOutDate = rSet.getDate("Check_out_date");
     LocalDate checkIn=checkInDate.toLocalDate();
     LocalDate checkOut=checkOutDate.toLocalDate();
     long numberOfDays=ChronoUnit.DAYS.between(checkIn, checkOut);
     if (numberOfDays==0) {
    	 numberOfDays=1;
     }
     double roomCharge=numberOfDays*roomPrice;
     System.out.println("\n===== Guest Details =====");
     System.out.println("Guest Name  : " + guestName);
     System.out.println("Phone       : " + phone);
     System.out.println("Room Number : " + roomNumber);
     System.out.println("Check In    : " + checkInDate);
     System.out.println("Check Out   : " + checkOutDate);
     System.out.println("Room Price  : " + roomPrice);
     System.out.println("Days Stayed : " + numberOfDays);
     System.out.println("Room Charge : " + roomCharge);
     System.out.println("Enter Extra charges");
     double extraCharges=scanner.nextDouble();
     System.out.println("Enter discounts");
     double discount=scanner.nextDouble();;
     double tax =roomCharge*0.12;
     double totalAmount=roomCharge+extraCharges+tax-discount;
     

     System.out.println("\n===== Bill Summary =====");
     System.out.println("Room Charge   : " + roomCharge);
     System.out.println("Extra Charges : " + extraCharges);
     System.out.println("Tax (12%)     : " + tax);
     System.out.println("Discount      : " + discount);
     System.out.println("Total Amount  : " + totalAmount);

     scanner.nextLine();
     System.out.println("\nEnter Payment Status:");
     String paymentStatus = scanner.nextLine();

     System.out.println("Enter Payment Mode:");
     String paymentMode = scanner.nextLine();
      String insertQuery="insert into front_office_bill  (Booking_Id,guest_id,Room_number,room_charge,number_of_days,extra_charges,discount,tax,total_amount,payment_status,payment_mode,bill_date) values(?,?,?,?,?,?,?,?,?,?,?,?)";
      
PreparedStatement insertStatement=con.prepareStatement(insertQuery);
insertStatement.setInt(1, bookingId1);
insertStatement.setInt(2,guestId);
insertStatement.setInt(3, roomNumber);
insertStatement.setDouble(4, roomCharge);
insertStatement.setLong(5, numberOfDays);
insertStatement.setDouble(6, extraCharges);
insertStatement.setDouble(7, discount);
insertStatement.setDouble(8, tax);
insertStatement.setDouble(9, totalAmount);
insertStatement.setString(10, paymentStatus);
insertStatement.setString(11, paymentMode);
insertStatement.setDate(12, new Date(System.currentTimeMillis()));
int rows=insertStatement.executeUpdate();
if(rows>0) {
	System.out.println("Front Office Bill Generated Successfully.");

}else {
	System.out.println("bill generation failed");

}
}
else {
	System.out.println("bill id not found");

}
}
	public static void viewFrontOfficeBill(Connection con) throws SQLException {
		String  viewBillQuery="select f.bill_id,f.Booking_Id,g.guest_name,g.phone,g.email,f.Room_number,r.Price,f.room_charge,f.number_of_days,f.extra_charges,f.discount,f.tax,f.total_amount,f.payment_status,f.payment_mode,f.bill_date from front_office_bill f join guest g on	f.guest_id=g.guest_id join room r on f.Room_number=r.Room_Number ";
	PreparedStatement viewBillPreparedStatement=con.prepareStatement(viewBillQuery);
	ResultSet resultSet=viewBillPreparedStatement.executeQuery();
	while (resultSet.next()) {
		int billId = resultSet.getInt("bill_id");
        int bookingId = resultSet.getInt("Booking_Id");

        String guestName = resultSet.getString("guest_name");
        String phone = resultSet.getString("phone");

        int roomNumber = resultSet.getInt("Room_number");

        double roomPrice = resultSet.getDouble("Price");

        long numberOfDays = resultSet.getLong("number_of_days");

        double roomCharge = resultSet.getDouble("room_charge");
        double extraCharges = resultSet.getDouble("extra_charges");

        double tax = resultSet.getDouble("tax");
        double discount = resultSet.getDouble("discount");

        double totalAmount = resultSet.getDouble("total_amount");

        String paymentStatus = resultSet.getString("payment_status");
        String paymentMode = resultSet.getString("payment_mode");

        Date billDate = resultSet.getDate("bill_date");

        System.out.println("\n====================================");
        System.out.println("Bill ID         : " + billId);
        System.out.println("Booking ID      : " + bookingId);

        System.out.println("Guest Name      : " + guestName);
        System.out.println("Phone           : " + phone);

        System.out.println("Room Number     : " + roomNumber);
        System.out.println("Room Price      : " + roomPrice);

        System.out.println("Days Stayed     : " + numberOfDays);

        System.out.println("Room Charge     : " + roomCharge);
        System.out.println("Extra Charges   : " + extraCharges);

        System.out.println("Tax             : " + tax);
        System.out.println("Discount        : " + discount);

        System.out.println("Total Amount    : " + totalAmount);

        System.out.println("Payment Status  : " + paymentStatus);
        System.out.println("Payment Mode    : " + paymentMode);

        System.out.println("Bill Date       : " + billDate);

        System.out.println("====================================");
		
		
	}
	}
	

	}


