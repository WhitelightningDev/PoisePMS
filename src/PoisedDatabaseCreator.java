import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Constructs a new PoisedDatabaseCreator object for creating necessary tables in the database.
 */
public class PoisedDatabaseCreator {

    private static final DatabaseConnector connector = DatabaseConnector.getInstance();
    private static final String DATABASE_NAME = "PoisePMS";

    /**
     * Creates the necessary tables for the Poised Project Management System in the database.
     */
    public static void createDatabaseTables() {
        try (Connection connection = connector.getConnection();
             Statement statement = connection.createStatement()) {

            // Create the database if it doesn't exist/ has not been created yet.
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME);
            statement.executeUpdate("USE " + DATABASE_NAME);

            // Create the StructuralEngineer table
            String createStructuralEngineerTableSQL = "CREATE TABLE IF NOT EXISTS structural_engineers (" +
                    "engineer_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name TEXT, " +
                    "phone_number TEXT, " +
                    "email TEXT, " +
                    "address TEXT)";
            statement.execute(createStructuralEngineerTableSQL);

            // Create the ProjectManager table
            String createProjectManagerTableSQL = "CREATE TABLE IF NOT EXISTS project_managers (" +
                    "manager_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name TEXT, " +
                    "phone_number TEXT, " +
                    "email TEXT, " +
                    "address TEXT)";
            statement.execute(createProjectManagerTableSQL);

            // Create the Architects table
            String createArchitectsTableSQL = "CREATE TABLE IF NOT EXISTS architects (" +
                    "architect_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name TEXT, " +
                    "phone_number TEXT, " +
                    "email TEXT, " +
                    "address TEXT)";
            statement.execute(createArchitectsTableSQL);

            // Creating the Customers table
            String createCustomersTableSQL = "CREATE TABLE IF NOT EXISTS customers (" +
                    "customer_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name TEXT, " +
                    "phone_number TEXT, " +
                    "email TEXT, " +
                    "address TEXT)";
            statement.execute(createCustomersTableSQL);

            // Create the Contractors table
            String createContractorsTableSQL = "CREATE TABLE IF NOT EXISTS contractors (" +
                    "contractor_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name TEXT, " +
                    "phone_number TEXT, " +
                    "email TEXT, " +
                    "address TEXT)";
            statement.execute(createContractorsTableSQL);

            // Create the Projects table
            String createProjectsTableSQL = "CREATE TABLE IF NOT EXISTS projects (" +
                    "project_number INT AUTO_INCREMENT PRIMARY KEY, " +
                    "project_name TEXT, " +
                    "building_type TEXT, " +
                    "physical_address TEXT, " +
                    "erf_number INT, " +
                    "total_fee DECIMAL(10, 2), " +
                    "total_paid_to_date DECIMAL(10, 2), " +
                    "deadline DATE, " +
                    "completion_date DATE, " +
                    "status VARCHAR(20) DEFAULT 'ongoing', " +
                    "structural_engineer_id INT, " +
                    "project_manager_id INT, " +
                    "architect_id INT, " +
                    "customer_id INT, " +
                    "FOREIGN KEY (structural_engineer_id) REFERENCES structural_engineers(engineer_id), " +
                    "FOREIGN KEY (project_manager_id) REFERENCES project_managers(manager_id), " +
                    "FOREIGN KEY (architect_id) REFERENCES architects(architect_id), " +
                    "FOREIGN KEY (customer_id) REFERENCES customers(customer_id))";
            statement.execute(createProjectsTableSQL);

            System.out.println("Database and tables created successfully.");

        } catch (SQLException e) {
            System.err.println("Error creating database tables: " + e.getMessage());
        }
    }
}
