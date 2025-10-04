package UI;

import DAO.ClientDAO;
import DAO.OperationDAO;
import Entity.Card;
import Entity.CardOperation;
import Entity.Client;
import Service.CardService;
import Service.ClientService;
import Service.FraudService;
import Service.OperationService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MainMenu {
    private final ClientService clientService;
    private final Scanner scanner = new Scanner(System.in);
    private CardService cardService;
    private OperationService operationService;
    private FraudService fraudService;
    public MainMenu(ClientService clientService, CardService cardService,OperationService operationService ,FraudService fraudService) {
        this.clientService = clientService;
        this.cardService = cardService;
        this.operationService=operationService;
        this.fraudService=fraudService;
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
            System.out.println("7. List Clients (with cards)");
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
                case "7" -> listClients();
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
           System.out.println(" Client added successfully!");
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    private void issueCard() {
        System.out.print("Enter client ID: ");
        int clientId = Integer.parseInt(scanner.nextLine().trim());

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
                cardService.issueCreditCard(clientId,type,monthlyLimit,interestRate);
                break;
            }
            case "3" -> {
                System.out.println("Enter availableBalance");
                Double availableBalance = scanner.nextDouble();
                cardService.issuePrepaidCard(clientId,type,availableBalance);
            }
            default -> System.out.println("baaaaaad Choice");


        }
    }

    private void performOperation() {
        System.out.println("\n--- Perform a Card Operation ---");
        System.out.print("Enter card id: ");
        int cardID = scanner.nextInt();

            Card card = cardService.findCardByID(cardID);
        if (card==null) {
            System.out.println("Card not found.");
            return;
        }
        Card carte = card;
        scanner.nextLine();
        System.out.println("Select operation type: 1. Purchase  2. Withdrawal  3. Online Payment");
        String typeInput = scanner.nextLine().trim();

        CardOperation.OperationType type;
        switch (typeInput) {
            case "1" -> type = CardOperation.OperationType.PURCHASE;
            case "2" -> type = CardOperation.OperationType.WITHDRAWAL;
            case "3" -> type = CardOperation.OperationType.ONLINE_PAYMENT;
            default -> {
                System.out.println("Invalid operation type.");
                return;
            }
        }

        System.out.print("Enter amount: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
            return;
        }

        System.out.print("Enter location (leave empty if not applicable): ");
        String location = scanner.nextLine().trim();

        CardOperation cardOperation=new CardOperation(0L, LocalDateTime.now(), amount,type,location,carte.getId());

        operationService.addOperation(cardOperation);

    }

    private void viewCardHistory() {
        System.out.println("\n--- View Card History ---");
        System.out.print("Enter card id: ");
        int cardID = scanner.nextInt();

       List<CardOperation> cardOperations= operationService.getHistory(cardID);

        System.out.println("---- Transaction History ----");
        System.out.printf("%-5s %-12s %-10s %-10s %-30s\n", "ID", "Type", "Amount", "Date", "Location");
        for (CardOperation op : cardOperations) {
            System.out.printf("%-5d %-12s %-10.2f %-10s %-40s\n",
                    op.id(),
                    op.type(),
                    op.amount(),
                    op.date(),
                    op.location()
            );
        }
        System.out.println("-----------------------------");
    }

    private void launchFraudAnalysis() {
        System.out.println("\n--- Launch Fraud Analysis ---");
        fraudService.analyseFraud();
    }

    private void blockSuspendCard() {
        System.out.println("\n--- Block/Suspend Card ---");
        System.out.print("Enter card id: ");
        int cardID = scanner.nextInt();
        scanner.nextLine();

        String action = "";
        while (!(action.equalsIgnoreCase("BLOCKED")) && !(action.equalsIgnoreCase("SUSPENDED"))) {
            System.out.print("Please choose BLOCKED or SUSPENDED: ");
            action = scanner.nextLine().trim();
        }

        cardService.updateStatus(cardID, action);
    }








    private  void listClients(){
        ResultSet rs = clientService.listAllCLients();

        try {
            while (rs.next()) {
                int id = rs.getInt("client_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String cards = rs.getString("cards");

                System.out.println("========================================");
                System.out.println("👤 Client Profile");
                System.out.println("----------------------------------------");
                System.out.printf(" ID     : %d%n", id);
                System.out.printf(" Name   : %s%n", name);
                System.out.printf(" Email  : %s%n", email);
                System.out.println("----------------------------------------");
                System.out.println(" Cards  : " + (cards != null ? cards : " No cards"));
                System.out.println("========================================\n");

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }





}