package Hospicore;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class FrontOfficeManagement {

    public static void frontOfficeMenu(Connection con, Scanner scanner) throws SQLException {

        int choice;

        do {
            System.out.println("\n===== FRONT OFFICE MANAGEMENT =====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. View Bookings");
            System.out.println("4. Generate Front Office Bill");
            System.out.println("5. View Front Office Bills");
            System.out.println("6. Check-Out Guest");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    BookingManagement.viewAvailableRoom(con);
                    break;

                case 2:
                    BookingManagement.booking(con, scanner);
                    break;

                case 3:
                     BookingManagement.viewBooking(con);
                    break;

                case 4:
                   BillingManagement.GenerateFrontOfficeBill(con, scanner);

                case 5:
                   BillingManagement.viewFrontOfficeBill(con);
                    break;

                case 6:
                    BookingManagement.checkOut(con, scanner);
                    break;

                case 7:
                    System.out.println("Exiting Front Office Management...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 7);
    }
}