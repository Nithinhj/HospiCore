package Hospicore;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login { 
	public static void main(String[] args) throws Exception {
		
	
	Scanner scanner =new Scanner(System.in);
	System.out.println( "Enter user name :");

	
	String username= scanner.nextLine();
	System.out.println("Enter password :");
	String password=scanner.nextLine();
	try {
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospicore","root","root123");
	String query= "select * from user where User=? and password=?";
	PreparedStatement preparedStatement =con.prepareStatement(query);
	preparedStatement.setString(1, username);
	preparedStatement.setString(2, password);
	ResultSet rSet=preparedStatement.executeQuery();
	if (rSet.next()) {
		String role=rSet.getString("role");
			if (role.equalsIgnoreCase("Admin")) {
				System.out.println("Welcome to HospiCore Admin");
int choice;
				do {
					System.out.println("1.Manage Rooms");
					System.out.println("2.Booking");
					System.out.println("3.Exit");
					System.out.println("Enter your Choice");
					 choice=scanner.nextInt();
					switch (choice) {
					case 1:
						
						
						break;
			case 2:
						
						
						break;
			case 3:
				System.out.println("Exiting...");

				
				break;

					default:
						System.out.println("Invalid choice");
						break;
					}


					
				} while (choice!=3);
				
				
			}else {
				System.out.println("Welcome to HospiCore");
				int choice;
				do {
				
					System.out.println("1.Booking");
					System.out.println("2.Exit");
					System.out.println("Enter your Choice");
					 choice=scanner.nextInt();
					switch (choice) {
					
			case 1:
						
						
						break;
			case 2:
				System.out.println("Exiting...");

				
				break;

					default:
						System.out.println("Invalid choice");

						break;
					}


					
				} while (choice!=2);
				}
				
			
			
				

		
	
	}else {
		System.out.println("Invalid Login");

	}
		
		
	

	}
	catch (Exception e) {
		System.out.println(e.getMessage());

	}
	
	
	}
}
