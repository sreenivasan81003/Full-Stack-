import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
        String jdbcURL = "jdbc:postgresql://localhost:5432/yourdatabase";
        String username = "yourusername";
        String password = "yourpassword";

        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(jdbcURL, username, password);
    }
}
