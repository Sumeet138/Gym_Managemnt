package gym_system.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    private static final String url = "jdbc:mysql://localhost:3306/gym_management_system";
    private static final String username = "root";
    private static final String password = "";

    public Connection connection;
    public Statement statement;

    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
        }

        try {
            if (url != null && username != null && password != null) {
                connection = DriverManager.getConnection(url, username, password);
                statement = connection.createStatement();
            } else {
                System.err.println("Database credentials not set. Please configure DB_URL, DB_USERNAME, DB_PASSWORD environment variables.");
            }
        } catch (Exception e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }
}
