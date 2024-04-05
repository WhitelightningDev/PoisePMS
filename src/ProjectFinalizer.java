import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * This class handles the finalization of projects in the PoisePMS database.
 */
public class ProjectFinalizer {

    private static final DatabaseConnector dbConnector = DatabaseConnector.getInstance();

    /**
     * Finalizes a project based on user input.
     * @param scanner The scanner object to take user input.
     */
    public static void finalizeProject(Scanner scanner) {
        System.out.println("Existing Projects:");
        displayExistingProjects();

        System.out.print("Enter the project number to finalize: ");
        int projectNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        try (Connection connection = dbConnector.getConnection()) {
            if (projectExists(connection, projectNumber)) {
                updateProjectStatus(connection, projectNumber);
                System.out.println("Project with number " + projectNumber + " has been finalized.");
            } else {
                System.out.println("Project with number " + projectNumber + " does not exist.");
            }
        } catch (SQLException e) {
            System.err.println("Error accessing database: " + e.getMessage());
        }
    }

    /**
     * Displays existing projects that are not finalized.
     */
    private static void displayExistingProjects() {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT project_number, project_name FROM projects WHERE status != 'Finalized'");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int projectNumber = resultSet.getInt("project_number");
                String projectName = resultSet.getString("project_name");
                System.out.println("Project Number: " + projectNumber + ", Project Name: " + projectName);
            }
        } catch (SQLException e) {
            System.err.println("Error displaying existing projects: " + e.getMessage());
        }
    }

    /**
     * Checks if a project exists in the database.
     * @param connection The database connection.
     * @param projectNumber The project number to check.
     * @return True if the project exists, false otherwise.
     * @throws SQLException If a database access error occurs.
     */
    private static boolean projectExists(Connection connection, int projectNumber) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM projects WHERE project_number = ?")) {
            statement.setInt(1, projectNumber);
            return statement.executeQuery().next();
        }
    }

    /**
     * Updates the status of a project to 'Finalized' in the database.
     * @param connection The database connection.
     * @param projectNumber The project number to finalize.
     * @throws SQLException If a database access error occurs.
     */
    private static void updateProjectStatus(Connection connection, int projectNumber) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE projects SET status = 'Finalized' WHERE project_number = ?")) {
            statement.setInt(1, projectNumber);
            statement.executeUpdate();
        }
    }
}
