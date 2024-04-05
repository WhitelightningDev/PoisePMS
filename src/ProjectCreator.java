import java.util.Scanner;

/**
 * This class is responsible for capturing information about new projects.
 */
public class ProjectCreator {

    /**
     * Captures information about a new project.
     * @param scanner The scanner object to take user input.
     */
    public static void captureNewProject(Scanner scanner) {
        // Get project details from the user
        System.out.println("Enter project details:");
        System.out.print("Project Name: ");
        String projectName = scanner.nextLine();

        System.out.print("Building Type: ");
        String buildingType = scanner.nextLine();

        System.out.print("Physical Address: ");
        String physicalAddress = scanner.nextLine();

        System.out.print("ERF Number: ");
        int erfNumber = scanner.nextInt();

        System.out.print("Total Fee: ");
        double totalFee = scanner.nextDouble();

        System.out.print("Total Paid to Date: ");
        double totalPaidToDate = scanner.nextDouble();

        System.out.print("Deadline (YYYY-MM-DD): ");
        String deadline = scanner.next();

        // Get architect details
        System.out.println("Enter architect details:");
        System.out.print("Name: ");
        String architectName = scanner.next();
        System.out.print("Phone Number: ");
        String architectPhoneNumber = scanner.next();
        System.out.print("Email: ");
        String architectEmail = scanner.next();
        System.out.print("Address: ");
        String architectAddress = scanner.next();

        // Get contractor details
        System.out.println("Enter contractor details:");
        scanner.nextLine(); // Consume newline character
        System.out.print("Name: ");
        String contractorName = scanner.nextLine();
        System.out.print("Phone Number: ");
        String contractorPhoneNumber = scanner.nextLine();
        System.out.print("Email: ");
        String contractorEmail = scanner.nextLine();
        System.out.print("Address: ");
        String contractorAddress = scanner.nextLine();

        // Get customer details
        System.out.println("Enter customer details:");
        System.out.print("Name: ");
        String customerName = scanner.next();
        System.out.print("Phone Number: ");
        String customerPhoneNumber = scanner.next();
        System.out.print("Email: ");
        String customerEmail = scanner.next();
        System.out.print("Address: ");
        String customerAddress = scanner.next();

        // Call DatabaseManager to save project details
        DatabaseManager.saveProject(projectName, buildingType, physicalAddress, erfNumber, totalFee, totalPaidToDate, deadline);
        // Call DatabaseManager to save architect details
        DatabaseManager.saveArchitect(architectName, architectPhoneNumber, architectEmail, architectAddress);
        // Call DatabaseManager to save contractor details
        DatabaseManager.saveContractor(contractorName, contractorPhoneNumber, contractorEmail, contractorAddress);
        // Call DatabaseManager to save customer details
        DatabaseManager.saveCustomer(customerName, customerPhoneNumber, customerEmail, customerAddress);
    }
}
