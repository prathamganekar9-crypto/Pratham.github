package Bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MainData {
    public static void main(String[] args)
    {
        try {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Database connection details (change as per your DB setup)
        String url = "jdbc:mysql://localhost:3306/"; // Replace with your DB name
        String database = "bank";
        String dbUsername = "root"; // Replace with your DB username
        String dbPassword = "9321874419"; // Replace with your DB password

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection connection = DriverManager.getConnection(url, dbUsername,dbPassword );
            System.out.println(" Database connected successfully!");

            // Pass this connection to BankService
            BankService bankService = new BankService(connection);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome to BOI Bank System");
            System.out.println("===========================");

            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Create Account");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transfer");
                System.out.println("5. All Transaction History ");
                System.out.println("6. Logout");
                System.out.print("Choose an option: ");

                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        if (username.isEmpty()) {
                            System.out.println("Username is not empty!");
                        }
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        if (password.isEmpty()) {
                            System.out.println("Password is not empty!");
                        }
                        bankService.createUser(username, password);
                        System.out.println("Account is created successfully!");
                        break;

                    case 2:
                        System.out.print("Enter username: ");
                        username = scanner.nextLine();

                        if (username.isEmpty()) {
                            System.out.println("Username is not empty!");
                            break;
                        }

                        System.out.print("Enter amount to deposit: ");
                        String amountInput = scanner.nextLine();  //  this was the missing line

                        if (amountInput.isEmpty()) {
                            System.out.println("Amount is not empty!");
                            break;
                        }

                        try {
                            double depositAmount = Double.parseDouble(amountInput);
                            bankService.deposit(username, depositAmount);
                            System.out.println("Account is deposited successfully!");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid amount. Please enter a number.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter username: ");
                        username = scanner.nextLine().trim();

                        if (username.isEmpty()) {
                            System.out.println(" Username is not empty!");
                            break;
                        }

                        System.out.print("Enter password: ");
                        password = scanner.nextLine().trim();

                        if (password.isEmpty()) {
                            System.out.println(" Password is not empty!");
                            break;
                        }

                        if (bankService.isPasswordCorrect(username, password)) {  // fixed method call
                            System.out.print("Enter amount to withdraw: ");
                            amountInput = scanner.nextLine().trim();

                            if (amountInput.isEmpty()) {
                                System.out.println(" Amount is not empty!");
                                break;
                            }

                            try {
                                double withdrawAmount = Double.parseDouble(amountInput);
                                bankService.withdraw(username, withdrawAmount);
                                System.out.println(" Withdrawn successfully!");
                            } catch (NumberFormatException e) {
                                System.out.println(" Invalid amount. Please enter a number.");
                            }
                        } else {
                            System.out.println(" Incorrect password. Withdrawal cancelled.");
                        }

                        break;

                    case 4:
                        System.out.print("Enter your username: ");
                        String fromUser = scanner.nextLine().trim();

                        if (fromUser.isEmpty()) {
                            System.out.println(" Sender username is not empty!");
                            break;
                        }

                        System.out.print("Enter recipient username: ");
                        String toUser = scanner.nextLine().trim();

                        if (toUser.isEmpty()) {
                            System.out.println(" Recipient username is not empty!");
                            break;
                        }

                        System.out.print("Enter amount to transfer: ");
                        amountInput = scanner.nextLine().trim();

                        if (amountInput.isEmpty()) {
                            System.out.println(" Amount is not empty!");
                            break;
                        }

                        try {
                            double transferAmount = Double.parseDouble(amountInput);
                            bankService.transfer(fromUser, toUser, transferAmount);
                            System.out.println(" Transfer successful!");
                        } catch (NumberFormatException e) {
                            System.out.println(" Invalid amount. Please enter a amount.");
                        }

                        break;

                    case 5:
                        System.out.print("Enter username: ");
                        username = scanner.nextLine();

                        // Check if input is empty
                        if (username == null || username.trim().isEmpty()) {
                            System.out.println(" Username is not empty. Please try again.");
                            break; // Exit this case and return to menu
                        }

                        bankService.printTransactionHistory(username);
                        break;

                    case 6:
                        System.out.println("Thank you for using Bank. Goodbye!");
                        scanner.close();
                        connection.close(); // Close DB connection before exit
                        System.exit(0);

                    default:
                        System.out.println("Invalid option. Please enter a number between 1 to 6.");
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println(" JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(" Database connection failed!");
            e.printStackTrace();
        }
    }
}


class Main{
    private String username;
    private String password;
    private BankAccount account;

    public void User(String username, String password, BankAccount account) {
        this.username = username;
        this.password = password;
        this.account = account;
    }

    public Main(String username, String password, BankAccount account) {
        this.username = username;
        this.password = password;
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public BankAccount getAccount() {
        return account;
    }

    public String getAccountNumber() {
        return account.getAccountNumber(); //  Return real account number
    }
}