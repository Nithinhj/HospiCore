package Hospicore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/Hospicore";
        String userName = "root";
        String password = "root123";

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(url, userName, password);
    }
}