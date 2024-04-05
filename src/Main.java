import java.util.Scanner;

/**
 * Main class for Poised Project Management System.
 */
public class Main {

    /**
     * Default constructor for the Main class.
     * Main method to start the Poised Project Management System.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PoisedDatabaseCreator.createDatabaseTables(); // Create tables if it does not exist

        while (true) {
            System.out.println("===== Poised Project Management System =====");
            System.out.println("1. Capture information about new projects and add to the database.");
            System.out.println("2. Update information about existing projects.");
            System.out.println("3. Delete data about projects and associated people.");
            System.out.println("4. Finalize existing projects");
            System.out.println("5. Find all projects still needing completion from the database.");
            System.out.println("6. Find all projects past the due date from the database.");
            System.out.println("7. Find a project by project number or name.");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    ProjectCreator.captureNewProject(scanner); // Call ProjectCreator for new project capture
                    break;
                case 2:
                    ProjectUpdater.updateProject(scanner); // Call ProjectUpdater for project updates
                    break;
                case 3:
                    ProjectDeleter.deleteOptionMenu(scanner); // Call ProjectDeleter for project deletion
                    break;
                case 4:
                    ProjectFinalizer.finalizeProject(scanner); // Call ProjectFinalizer for project finalization
                    break;
                case 5:
                    IncompleteProjectFinder.findAndFinalizeIncompleteProjects(scanner); // Call IncompleteProjectFinder to find and finalize incomplete projects
                    break;
                case 6:
                    PastDueDateProjectFinder.findPastDueDateProjects(); // Call PastDueDateProjectFinder to find projects past due date
                    break;
                case 7:
                    findProjectOption(scanner); // Call the method to find a project
                    break;
                case 8:
                    System.out.println("Exiting Poised Project Management System. Goodbye!"); // Exit message
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option."); // Invalid option message
            }
        }
    }

    /**
     * Method to find a project by project number or name.
     * @param scanner The scanner object to take user input.
     */
    private static void findProjectOption(Scanner scanner) {
        DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
        ProjectFinder projectFinder = new ProjectFinder(databaseConnector);
        projectFinder.findProjectOption(scanner);
    }
}
