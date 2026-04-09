package banking_system.repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    private static final String url = "jdbc:mysql://localhost:3306/banking_system";
    private static final String username = "root";
    private static final String password = "";

    Connection connection;
    Statement statement;

    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}