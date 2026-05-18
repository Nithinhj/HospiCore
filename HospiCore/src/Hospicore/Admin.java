package Hospicore;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin {
	public static void AdminMenu(Connection con, Scanner scanner) throws SQLException{
		System.out.println("Welcome to hospi core . Admin");
		
		int choice;

		do {
			System.out.println("1. Manage Rooms");
            System.out.println("2. Booking");
            System.out.println("3. Exit");
            System.out.println("Enter your Choice");

			 choice=scanner.nextInt();
			 switch (choice) {
			case 1:
				showRoomMenu(con, scanner);
				break;
				
				case 2:
					showbookingMenu(con,scanner);
break;
				case 3:
					System.out.println("Exitinig");


			default:
				System.out.println("Invalid choice");

				break;
			}

			
		} while (choice!=3);
		
	}
 public static void showRoomMenu(Connection con,Scanner scanner) throws SQLException {
	 int subChoice;
	 do { System.out.println("1. Add Room");
     System.out.println("2. View Room");
     System.out.println("3. Update Room");
     System.out.println("4. Delete Room");
     System.out.println("5. Go back");
     System.out.println("Enter Your Choice");
     subChoice=scanner.nextInt();

     switch (subChoice) {
	case 1:
		RoomManagement.addRoom(con, scanner);
		
		break;
		case 2:
			RoomManagement.viewRoom(con);
			break;
		case 3: 
			RoomManagement.updateRoom(con, scanner);
 break;
 case 4: 
	 RoomManagement.deleteRoom(con, scanner);
	 break;
	  case 5 :
		  System.out.println("Going back");
		  break;

	default:
		System.out.println("Invalid choice");

		break;
	}
		
	} while (subChoice!=5);
	 
 }
 public static void  showbookingMenu(Connection con, Scanner scanner) throws SQLException {
	 int choice;
	 do {
		 System.out.println("1. Show Available Room");
		 System.out.println("2. Book Room");
		 System.out.println("3.Delete Booking");
		 System.out.println("4.update Booking");
		 System.out.println("5.Exit");
		 choice=scanner.nextInt();

switch (choice) {
case 1:
	BookingManagement.viewAvailableRoom(con);
	
	break;
case 2:
	BookingManagement.booking(con, scanner);
	break;
case 3:
	System.out.println("Coming Soon");
	break;
case 4:
	System.out.println("Coming Soon");
	break;
	case 5:
		System.out.println("Exiting...");
		break;



default:
	break;
}



		
	} while (choice!=5);
 }
}
