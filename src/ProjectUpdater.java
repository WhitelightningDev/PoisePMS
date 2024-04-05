import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * This class handles updating project information in the PoisePMS database.
 */
public class ProjectUpdater {

    private static final DatabaseConnector dbConnector = DatabaseConnector.getInstance();

    /**
     * Allows the user to update project information.
     * @param scanner The scanner object to take user input.
     */
    public static void updateProject(Scanner scanner) {
        System.out.print("Enter the project number to update: ");
        int projectNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Check if the project exists
        if (!projectExists(projectNumber)) {
            System.out.println("Project with number " + projectNumber + " does not exist.");
            return;
        }

        // Display current project details
        displayProjectDetails(projectNumber);

        // Update project information
        boolean updateMore = true;
        while (updateMore) {
            System.out.println("Select which information to update:");
            System.out.println("1. Project Name");
            System.out.println("2. Building Type");
            System.out.println("3. Physical Address");
            System.out.println("4. ERF Number");
            System.out.println("5. Total Fee");
            System.out.println("6. Total Paid to Date");
            System.out.println("7. Deadline");
            System.out.println("8. Done updating");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    updateField("project_name", projectNumber, scanner);
                    break;
                case 2:
                    updateField("building_type", projectNumber, scanner);
                    break;
                case 3:
                    updateField("physical_address", projectNumber, scanner);
                    break;
                case 4:
                    updateField("erf_number", projectNumber, scanner);
                    break;
                case 5:
                    updateField("total_fee", projectNumber, scanner);
                    break;
                case 6:
                    updateField("total_paid_to_date", projectNumber, scanner);
                    break;
                case 7:
                    updateField("deadline", projectNumber, scanner);
                    break;
                case 8:
                    updateMore = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        System.out.println("Project information updated successfully.");
    }

    /**
     * Checks if a project exists in the database.
     * @param projectNumber The project number to check.
     * @return True if the project exists, false otherwise.
     */
    private static boolean projectExists(int projectNumber) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM projects WHERE project_number = ?")) {
            statement.setInt(1, projectNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Returns true if a project with the given number exists
            }
        } catch (SQLException e) {
            System.err.println("Error checking project existence: " + e.getMessage());
            return false; // Assume project doesn't exist in case of an error
        }
    }

    /**
     * Displays the current details of a project.
     * @param projectNumber The project number to display details for.
     */
    private static void displayProjectDetails(int projectNumber) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM projects WHERE project_number = ?")) {
            statement.setInt(1, projectNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Current Project Details:");
                    System.out.println("Project Name: " + resultSet.getString("project_name"));
                    System.out.println("Building Type: " + resultSet.getString("building_type"));
                    System.out.println("Physical Address: " + resultSet.getString("physical_address"));
                    System.out.println("ERF Number: " + resultSet.getInt("erf_number"));
                    System.out.println("Total Fee: " + resultSet.getDouble("total_fee"));
                    System.out.println("Total Paid to Date: " + resultSet.getDouble("total_paid_to_date"));
                    System.out.println("Deadline: " + resultSet.getString("deadline"));
                } else {
                    System.out.println("Project with number " + projectNumber + " not found.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving project details: " + e.getMessage());
        }
    }

    /**
     * Updates a specific field of a project in the database.
     * @param fieldName The name of the field to update.
     * @param projectNumber The project number to update.
     * @param scanner The scanner object to take user input.
     */
    private static void updateField(String fieldName, int projectNumber, Scanner scanner) {
        System.out.print("Enter new " + fieldName.replace("_", " ") + ": ");
        String newValue = scanner.nextLine();
        updateProjectInDatabase(projectNumber, fieldName, newValue);
        System.out.println("Update saved successfully.");
    }

    /**
     * Updates a project in the database with the new field value.
     * @param projectNumber The project number to update.
     * @param fieldName The name of the field to update.
     * @param newValue The new value of the field.
     */
    private static void updateProjectInDatabase(int projectNumber, String fieldName, String newValue) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE projects SET " + fieldName + " = ? WHERE project_number = ?")) {
            statement.setString(1, newValue);
            statement.setInt(2, projectNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating project information: " + e.getMessage());
        }
    }
}
