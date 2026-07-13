package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/reservationdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Charitha@45";
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database Connected Successfully!");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}