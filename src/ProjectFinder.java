import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * This class facilitates finding and selecting projects in the Poise Project Management System.
 */
public class ProjectFinder {

    private final DatabaseConnector databaseConnector;

    /**
     * Constructs a ProjectFinder object with the specified DatabaseConnector.
     * @param databaseConnector The DatabaseConnector to be used for database operations.
     */
    public ProjectFinder(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    /**
     * Displays the find project menu and handles user input.
     * @param scanner The scanner object to take user input.
     */
    public void findProjectOption(Scanner scanner) {
        System.out.println("===== Find and Select a Project =====");
        System.out.println("1. Find project by project number.");
        System.out.println("2. Find project by project name.");
        System.out.println("3. Show all projects.");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                System.out.print("Enter project number: ");
                int projectNumber = scanner.nextInt();
                findProjectByNumber(projectNumber);
                break;
            case 2:
                System.out.print("Enter project name: ");
                String projectName = scanner.nextLine();
                findProjectByName(projectName);
                break;
            case 3:
                showAllProjects();
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    /**
     * Finds and selects a project by project number.
     * @param projectNumber The project number to find.
     */
    public void findProjectByNumber(int projectNumber) {
        try {
            String query = "SELECT * FROM projects WHERE project_number = ?";
            PreparedStatement statement = databaseConnector.getConnection().prepareStatement(query);
            statement.setInt(1, projectNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Project found by number:");
                // Print project details or handle as needed
            } else {
                System.out.println("Project with number " + projectNumber + " not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error finding project by number: " + e.getMessage());
        }
    }

    /**
     * Finds and selects a project by project name.
     * @param projectName The project name to find.
     */
    public void findProjectByName(String projectName) {
        try {
            String query = "SELECT * FROM projects WHERE project_name = ?";
            PreparedStatement statement = databaseConnector.getConnection().prepareStatement(query);
            statement.setString(1, projectName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Project found by name:");
                // Print project details or handle as needed
            } else {
                System.out.println("Project with name '" + projectName + "' not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error finding project by name: " + e.getMessage());
        }
    }

    /**
     * Displays all projects stored in the database.
     */
    public void showAllProjects() {
        try {
            String query = "SELECT * FROM projects";
            PreparedStatement statement = databaseConnector.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // Display project details
                int projectNumber = resultSet.getInt("project_number");
                String projectName = resultSet.getString("project_name");
                // Display other details as needed
                System.out.println("Project Number: " + projectNumber + ", Project Name: " + projectName);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all projects: " + e.getMessage());
        }
    }
}
