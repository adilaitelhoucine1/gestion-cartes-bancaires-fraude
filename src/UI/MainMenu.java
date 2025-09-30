package UI;

import Service.ClientService;
import java.util.Scanner;

public class MainMenu {
    private final ClientService clientService;
    private final Scanner scanner = new Scanner(System.in);

    public MainMenu(ClientService clientService) {
        this.clientService = clientService;
    }

    public void display() {
        while (true) {
            System.out.println("\n=== Banking Card Management System ===");
            System.out.println("1. Add Client");
            System.out.println("2. Issue Card");
            System.out.println("3. Perform Operation");
            System.out.println("4. View Card History");
            System.out.println("5. Launch Fraud Analysis");
            System.out.println("6. Block/Suspend Card");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> addClient();
                case "2" -> issueCard();
                case "3" -> performOperation();
                case "4" -> viewCardHistory();
                case "5" -> launchFraudAnalysis();
                case "6" -> blockSuspendCard();
                case "0" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addClient() {
        System.out.println("\n--- Add Client ---");
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Phone: ");
        String phone = scanner.nextLine().trim();

        try {
           clientService.addClient(name, email, phone);
           System.out.println("✅ Client added successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void issueCard() {
        System.out.println("\n--- Issue Card ---");
        // TODO: Implement card issuing logic (debit/credit/prepaid)
    }

    private void performOperation() {
        System.out.println("\n--- Perform Operation ---");
        // TODO: Implement operation logic (purchase, withdrawal, online payment)
    }

    private void viewCardHistory() {
        System.out.println("\n--- View Card History ---");
        // TODO: Implement card history viewing
    }

    private void launchFraudAnalysis() {
        System.out.println("\n--- Launch Fraud Analysis ---");
        // TODO: Implement fraud analysis logic
    }

    private void blockSuspendCard() {
        System.out.println("\n--- Block/Suspend Card ---");
        // TODO: Implement block/suspend card
    }
}