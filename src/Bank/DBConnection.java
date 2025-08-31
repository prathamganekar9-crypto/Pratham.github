package Bank;

import java.sql.*;
import java.util.Scanner;

class BankViewer {

    // Database connection
    private static final String URL = "jdbc:mysql://localhost:3306/Bank?";
    private static final String USER = "root"; // change if needed
    private static final String PASSWORD = "9321874419"; // change if needed

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n====== Bank Database Viewer ======");
            System.out.println("1. Show Users");
            System.out.println("2. Show Accounts");
            System.out.println("3. Show Transactions");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> showUsers();
                case 2 -> showAccounts();
                case 3 -> showTransactions();
                case 0 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Show Users
    private static void showUsers() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

            System.out.println("\nID | Username | Password");
            System.out.println("--------------------------");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("username") + " | " +
                                rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Show Accounts
    private static void showAccounts() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM accounts")) {

            System.out.println("\nAccountNo | UserID | Balance | Frozen");
            System.out.println("-----------------------------------");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("account_number") + " | " +
                                rs.getInt("user_id") + " | " +
                                rs.getDouble("balance") + " | " +
                                rs.getBoolean("is_frozen"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Show Transactions
    private static void showTransactions() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM transactions")) {

            System.out.println("\nTxnID   | AccountNo | Type | Amount | Timestamp");
            System.out.println("---------------------------------------------------");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("transaction_id") + " | " +
                                rs.getInt("account_number") + " | " +
                                rs.getString("type") + " | " +
                                rs.getDouble("amount") + " | " +
                                rs.getTimestamp("timestamp"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
