package UI;

import Service.CardService;
import Service.ClientService;

import java.util.Scanner;

public class MainMenu {
    private final ClientService clientService;
    private final Scanner scanner = new Scanner(System.in);
    private CardService cardService;

    public MainMenu(ClientService clientService, CardService cardService) {
        this.clientService = clientService;
        this.cardService = cardService;
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
        System.out.print("Enter client ID: ");
        Long clientId = Long.parseLong(scanner.nextLine().trim());

        System.out.print("Enter card type (1=Debit, 2=Credit, 3=Prepaid): ");
        String type = scanner.nextLine().trim();
        switch (type) {
            case "1" -> {
                System.out.println("Enter daily limit");
                Double dailyLimit = scanner.nextDouble();
                cardService.issueDebitCard(clientId,type,dailyLimit);
                break;
            }
            case "2" -> {
                System.out.println("Enter monthlyLimit");
                Double monthlyLimit = scanner.nextDouble();
                System.out.println("Enter interestRate");
                Double interestRate = scanner.nextDouble();
                cardService.issueCreditCard();
                break;
            }
            case "3" -> {
                System.out.println("Enter availableBalance");
                Double availableBalance = scanner.nextDouble();
                cardService.issuePrepaidCard();
            }
            default -> System.out.println("baaaaaad Choice");


        }
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