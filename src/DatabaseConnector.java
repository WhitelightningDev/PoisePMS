import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class handles the connection to the PoisePMS database.
 */
public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/PoisePMS"; // Database URL
    private static final String USERNAME = "root"; // Database username
    private static final String PASSWORD = "Grayham@95"; // Database password

    // Singleton instance for DatabaseConnector
    private static DatabaseConnector instance;

    // Private constructor to prevent instantiation from outside
    private DatabaseConnector() {}

    /**
     * Gets the singleton instance of DatabaseConnector.
     * @return The singleton instance of DatabaseConnector.
     */
    public static DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    /**
     * Establishes a connection to the PoisePMS database.
     * @return A Connection object representing the database connection.
     * @throws SQLException If a database access error occurs or the URL is null.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
