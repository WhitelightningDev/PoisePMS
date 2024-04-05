import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * This class is responsible for finding and finalizing incomplete projects in the Poised Project Management System.
 *
 */
public class IncompleteProjectFinder {
    private static final DatabaseConnector connector = DatabaseConnector.getInstance();

    public static void findAndFinalizeIncompleteProjects(Scanner scanner) {
        displayIncompleteProjects();

        System.out.print("\nSelect the project number to finalize (or enter 0 to go back): ");
        int projectNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (projectNumber == 0) {
            System.out.println("Going back...");
            return;
        }

        if (projectExists(projectNumber) && !isProjectFinalized(projectNumber)) {
            finalizeProject(projectNumber);
            System.out.println("Project with number " + projectNumber + " has been finalized.");
        } else {
            System.out.println("Invalid project selection or project already finalized.");
        }
    }

    private static void displayIncompleteProjects() {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM projects WHERE status != 'Finalized'");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int projectNumber = resultSet.getInt("project_number");
                String projectName = resultSet.getString("project_name");
                String status = resultSet.getString("status");
                System.out.println("Project Number: " + projectNumber + ", Project Name: " + projectName + ", Status: " + status);
            }
        } catch (SQLException e) {
            System.err.println("Error finding incomplete projects: " + e.getMessage());
        }
    }

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

    private static boolean isProjectFinalized(int projectNumber) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM projects WHERE project_number = ? AND status = 'Finalized'")) {
            statement.setInt(1, projectNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.err.println("Error checking project finalization: " + e.getMessage());
            return false;
        }
    }

    private static void finalizeProject(int projectNumber) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE projects SET status = 'Finalized' WHERE project_number = ?")) {
            statement.setInt(1, projectNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error finalizing project: " + e.getMessage());
        }
    }
}
