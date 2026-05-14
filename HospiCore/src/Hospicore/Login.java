package Hospicore;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Login {
	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter user name :");

		String username = scanner.nextLine();
		System.out.println("Enter password :");
		String password = scanner.nextLine();
		try {
			Connection con = DbConnection.getConnection();
			String query = "select * from user where User=? and password=?";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet rSet = preparedStatement.executeQuery();
			if (rSet.next()) {
				String role = rSet.getString("role");
				if (role.equalsIgnoreCase("Admin")) {
					
					Admin.AdminMenu(con, scanner);
				} else if (role.equalsIgnoreCase("Front_Office")) {
					System.out.println("Welcome to HospiCore Front Office");
					int choice;
					do {

						System.out.println("1.Booking");
						System.out.println("2.Check out");

						System.out.println("3.Exit");
						System.out.println("Enter your Choice");
						choice = scanner.nextInt();
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

					} while (choice != 3);
				}

				else if (role.equalsIgnoreCase("House_Keepng")) {
					System.out.println("Welcome to HospiCore House Keeping");
					int choice;
					do {
						System.out.println("1.View All Room ");
						System.out.println("2. Mark Clean Rooms");
						System.out.println("3. Mark Dirty Rooms");
						System.out.println("4.Exiting..");
						System.out.println("Enter your choice");
						choice = scanner.nextInt();

						switch (choice) {
						case 1:

							break;
						case 2:
							break;
						case 3:
							break;
						case 4:
							System.out.println("Exiting...");
							break;

						default:
							break;
						}

					} while (choice != 4);

				} else if (role.equalsIgnoreCase("Restaurent")) {
					System.out.println("Welcome to HospiCore Restaurent ");
					int choice;

					do {
						System.out.println("1.K.O.T");
						System.out.println("2.Billing");
						System.out.println("3.Settlement");
						System.out.println("4.Exit");

						System.out.println("Enter Your Choice");
						choice = scanner.nextInt();

						switch (choice) {
						case 1:

							break;
						case 2:
							break;
						case 3:
							break;
						case 4:
							System.out.println("Exiting...");
							break;

						default:
							break;
						}

					} while (choice != 4);

				} else if (role.equalsIgnoreCase("Store")) {
					System.out.println("Welcome to HospiCore Store");

					int choice;
					do {
						System.out.println();
						System.out.println("1.Add Items");
						System.out.println("2.View Items");
						System.out.println("3.View Requests");
						System.out.println("4.Issue Items");
						System.out.println("5.Exit");
						System.out.println("Enter your choice");
						choice = scanner.nextInt();
						switch (choice) {
						case 1:

							break;
						case 2:
							break;
						case 3:

							break;
						case 4:
							break;
						case 5:
							System.out.println("Exiting....");

							break;

						default:
							break;
						}

					} while (choice != 5);

				}

			}

			else {
				System.out.println("Invalid Login");

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}
}
