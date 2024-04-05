import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * This class is responsible for finding projects that are past their due date in the Poised Project Management System.
 */
public class PastDueDateProjectFinder {

    private static final DatabaseConnector connector = DatabaseConnector.getInstance();

    /**
     * Finds projects that are past their due date and prints them.
     */
    public static void findPastDueDateProjects() {
        System.out.println("Projects past due date:");
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM projects WHERE deadline < CURDATE()");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int projectNumber = resultSet.getInt("project_number");
                String projectName = resultSet.getString("project_name");
                Date deadline = resultSet.getDate("deadline");
                System.out.println("Project Number: " + projectNumber + ", Project Name: " + projectName + ", Deadline: " + deadline);
            }
        } catch (SQLException e) {
            System.err.println("Error finding projects past due date: " + e.getMessage());
        }
    }
}
