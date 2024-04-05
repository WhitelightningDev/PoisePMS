import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class manages database operations for the Poised Project Management System.
 */
public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/PoisePMS"; // Adjust URL if necessary
    private static final String USERNAME = "root"; // Adjust username if necessary
    private static final String PASSWORD = "Grayham@95"; // Adjust password if necessary

    /**
     * Saves project information to the database.
     * @param projectName The name of the project.
     * @param buildingType The type of building.
     * @param physicalAddress The physical address of the project.
     * @param erfNumber The ERF number of the project.
     * @param totalFee The total fee of the project.
     * @param totalPaidToDate The total amount paid to date for the project.
     * @param deadline The deadline of the project.
     */
    public static void saveProject(String projectName, String buildingType, String physicalAddress,
                                   int erfNumber, double totalFee, double totalPaidToDate, String deadline) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO projects (project_name, building_type, physical_address, erf_number, "
                    + "total_fee, total_paid_to_date, deadline) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, projectName);
                statement.setString(2, buildingType);
                statement.setString(3, physicalAddress);
                statement.setInt(4, erfNumber);
                statement.setDouble(5, totalFee);
                statement.setDouble(6, totalPaidToDate);
                statement.setString(7, deadline);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Project information saved successfully.");
                } else {
                    System.out.println("Error: Project information not saved.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saving project: " + e.getMessage());
        }
    }

    /**
     * Saves architect information to the database.
     * @param name The name of the architect.
     * @param phoneNumber The phone number of the architect.
     * @param email The email address of the architect.
     * @param address The address of the architect.
     */
    public static void saveArchitect(String name, String phoneNumber, String email, String address) {
        // Method body
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO architects (name, phone_number, email, address) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, phoneNumber);
                statement.setString(3, email);
                statement.setString(4, address);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Architect information saved successfully.");
                } else {
                    System.out.println("Error: Architect information not saved.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saving architect: " + e.getMessage());
        }
    }

    /**
     * Saves contractor information to the database.
     * @param name The name of the contractor.
     * @param phoneNumber The phone number of the contractor.
     * @param email The email address of the contractor.
     * @param address The address of the contractor.
     */
    public static void saveContractor(String name, String phoneNumber, String email, String address) {
        // Method body
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO contractors (name, phone_number, email, address) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, phoneNumber);
                statement.setString(3, email);
                statement.setString(4, address);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Contractor information saved successfully.");
                } else {
                    System.out.println("Error: Contractor information not saved.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saving contractor: " + e.getMessage());
        }
    }

    /**
     * Saves customer information to the database.
     * @param name The name of the customer.
     * @param phoneNumber The phone number of the customer.
     * @param email The email address of the customer.
     * @param address The address of the customer.
     */
    public static void saveCustomer(String name, String phoneNumber, String email, String address) {
        // Method body
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO customers (name, phone_number, email, address) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, phoneNumber);
                statement.setString(3, email);
                statement.setString(4, address);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Customer information saved successfully.");
                } else {
                    System.out.println("Error: Customer information not saved.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saving customer: " + e.getMessage());
        }
    }
}
