import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ProjectDeleter {

    private static final DatabaseConnector connector = DatabaseConnector.getInstance();

    /**
     * Displays the delete options menu and handles user input for deletion.
     * @param scanner The scanner object to take user input.
     */
    public static void deleteOptionMenu(Scanner scanner) {
        boolean deleteMore = true;
        while (deleteMore) {
            System.out.println("Select what you want to delete:");
            System.out.println("1. Delete Project");
            System.out.println("2. Delete Architect");
            System.out.println("3. Delete Customer");
            System.out.println("4. Delete Contractor");
            System.out.println("5. Done deleting");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    deleteProject(scanner);
                    break;
                case 2:
                    deleteArchitect(scanner);
                    break;
                case 3:
                    deleteCustomer(scanner);
                    break;
                case 4:
                    deleteContractor(scanner);
                    break;
                case 5:
                    deleteMore = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    /**
     * Displays all projects stored in the database.
     */
    private static void displayAllProjects() {
        try {
            Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT project_number, project_name FROM projects");
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Available Projects:");
            while (resultSet.next()) {
                int projectNumber = resultSet.getInt("project_number");
                String projectName = resultSet.getString("project_name");
                System.out.println(projectNumber + ". " + projectName);
            }
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error retrieving projects: " + e.getMessage());
        }
    }

    /**
     * Displays all architects stored in the database.
     */
    private static void displayAllArchitects() {
        try {
            Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT architect_id, name FROM architects");
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Available Architects:");
            while (resultSet.next()) {
                int architectId = resultSet.getInt("architect_id");
                String architectName = resultSet.getString("name");
                System.out.println(architectId + ". " + architectName);
            }
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error retrieving architects: " + e.getMessage());
        }
    }

    /**
     * Displays all customers stored in the database.
     */
    private static void displayAllCustomers() {
        try {
            Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT customer_id, name FROM customers");
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Available Customers:");
            while (resultSet.next()) {
                int customerId = resultSet.getInt("customer_id");
                String customerName = resultSet.getString("name");
                System.out.println(customerId + ". " + customerName);
            }
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error retrieving customers: " + e.getMessage());
        }
    }

    /**
     * Displays all contractors stored in the database.
     */
    private static void displayAllContractors() {
        try {
            Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT contractor_id, name FROM contractors");
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Available Contractors:");
            while (resultSet.next()) {
                int contractorId = resultSet.getInt("contractor_id");
                String contractorName = resultSet.getString("name");
                System.out.println(contractorId + ". " + contractorName);
            }
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error retrieving contractors: " + e.getMessage());
        }
    }

    /**
     * Deletes a project from the database.
     * @param scanner The scanner object to take user input.
     */
    public static void deleteProject(Scanner scanner) {
        displayAllProjects();
        System.out.print("Enter the project number to delete: ");
        int projectNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (projectExists(projectNumber)) {
            deleteProjectFromDatabase(projectNumber);
            System.out.println("Project deleted successfully.");
        } else {
            System.out.println("Project with number " + projectNumber + " does not exist.");
        }
    }

    /**
     * Deletes an architect from the database.
     * @param scanner The scanner object to take user input.
     */
    public static void deleteArchitect(Scanner scanner) {
        displayAllArchitects();
        System.out.print("Enter the architect ID to delete: ");
        int architectId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (architectExists(architectId)) {
            deleteArchitectFromDatabase(architectId);
            System.out.println("Architect deleted successfully.");
        } else {
            System.out.println("Architect with ID " + architectId + " does not exist.");
        }
    }

    /**
     * Deletes a customer from the database.
     * @param scanner The scanner object to take user input.
     */
    public static void deleteCustomer(Scanner scanner) {
        displayAllCustomers();
        System.out.print("Enter the customer ID to delete: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (customerExists(customerId)) {
            deleteCustomerFromDatabase(customerId);
            System.out.println("Customer deleted successfully.");
        } else {
            System.out.println("Customer with ID " + customerId + " does not exist.");
        }
    }

    /**
     * Deletes a contractor from the database.
     * @param scanner The scanner object to take user input.
     */
    public static void deleteContractor(Scanner scanner) {
        displayAllContractors();
        System.out.print("Enter the contractor ID to delete: ");
        int contractorId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (contractorExists(contractorId)) {
            deleteContractorFromDatabase(contractorId);
            System.out.println("Contractor deleted successfully.");
        } else {
            System.out.println("Contractor with ID " + contractorId + " does not exist.");
        }
    }


    /**
     * Checks if a project exists in the database.
     * @param projectNumber The project number to check.
     * @return True if the project exists, false otherwise.
     */
    private static boolean projectExists(int projectNumber) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM projects WHERE project_number = ?")) {
            statement.setInt(1, projectNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.err.println("Error checking project existence: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a project from the database.
     * @param projectNumber The project number to delete.
     */
    private static void deleteProjectFromDatabase(int projectNumber) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM projects WHERE project_number = ?")) {
            statement.setInt(1, projectNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting project: " + e.getMessage());
        }
    }

    /**
     * Checks if an architect exists in the database.
     * @param architectId The architect ID to check.
     * @return True if the architect exists, false otherwise.
     */
    private static boolean architectExists(int architectId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM architects WHERE architect_id = ?")) {
            statement.setInt(1, architectId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.err.println("Error checking architect existence: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes an architect from the database.
     * @param architectId The architect ID to delete.
     */
    private static void deleteArchitectFromDatabase(int architectId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM architects WHERE architect_id = ?")) {
            statement.setInt(1, architectId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting architect: " + e.getMessage());
        }
    }

    /**
     * Checks if a customer exists in the database.
     * @param customerId The customer ID to check.
     * @return True if the customer exists, false otherwise.
     */
    private static boolean customerExists(int customerId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers WHERE customer_id = ?")) {
            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.err.println("Error checking customer existence: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a customer from the database.
     * @param customerId The customer ID to delete.
     */
    private static void deleteCustomerFromDatabase(int customerId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM customers WHERE customer_id = ?")) {
            statement.setInt(1, customerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting customer: " + e.getMessage());
        }
    }

    /**
     * Checks if a contractor exists in the database.
     * @param contractorId The contractor ID to check.
     * @return True if the contractor exists, false otherwise.
     */
    private static boolean contractorExists(int contractorId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM contractors WHERE contractor_id = ?")) {
            statement.setInt(1, contractorId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.err.println("Error checking contractor existence: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a contractor from the database.
     * @param contractorId The contractor ID to delete.
     */
    private static void deleteContractorFromDatabase(int contractorId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM contractors WHERE contractor_id = ?")) {
            statement.setInt(1, contractorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting contractor: " + e.getMessage());
        }
    }
}
