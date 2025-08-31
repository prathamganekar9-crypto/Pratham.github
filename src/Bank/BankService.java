package Bank;
import Bank.BankAccount;
import Bank.User;
import Bank.Transaction;

import java.sql.Connection;
import java.util.*;

public class BankService {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, List<Transaction>> transactionHistory = new HashMap<>();
    private final Connection connection; // DB connection

    public BankService(Connection connection) {
        this.connection = connection;
    }

    // Create user and account
    public void createUser(String username, String password) {
        String accountNumber = "ACC" + (users.size() + 1);
        BankAccount account = new BankAccount(accountNumber);
        User user = new User(username, password, account);
        users.put(username, user);
        transactionHistory.put(accountNumber, new ArrayList<>());
        System.out.println("Account created with number: " + accountNumber);
    }

    // Deposit money
    public void deposit(String username, double amount) {
        User user = users.get(username);
        if (user != null) {
            user.getAccount().deposit(amount);
            addTransaction(user.getAccount().getAccountNumber(), "Deposit", amount);
            System.out.println("Deposited " + amount);
        } else {
            System.out.println("User not found");
        }
    }

    // Withdraw money (with password check)
    public boolean withdraw(String username, double amount, String inputPassword) {
        User user = users.get(username);
        if (user != null) {
            if (isPasswordCorrect(username, inputPassword)) {
                boolean success = user.getAccount().withdraw(amount);
                if (success) {
                    addTransaction(user.getAccount().getAccountNumber(), "Withdraw", amount);
                    System.out.println("Withdrawn " + amount);
                    return true;
                } else {
                    System.out.println("Withdrawal failed due to insufficient funds");
                    return false;
                }
            } else {
                System.out.println("Incorrect password.");
                return false;
            }
        } else {
            System.out.println("User not found");
            return false;
        }
    }

    // Transfer money
    public void transfer(String fromUsername, String toUsername, double amount) {
        User fromUser = users.get(fromUsername);
        User toUser = users.get(toUsername);

        if (fromUser == null || toUser == null) {
            System.out.println("One or both users not found");
            return;
        }

        if (fromUser.getAccount().withdraw(amount)) {
            toUser.getAccount().deposit(amount);
            addTransaction(fromUser.getAccount().getAccountNumber(), "Transfer Out", amount);
            addTransaction(toUser.getAccount().getAccountNumber(), "Transfer In", amount);
            System.out.println("Transferred " + amount + " from " + fromUsername + " to " + toUsername);
        } else {
            System.out.println("Insufficient balance or account frozen");
        }
    }

    // View transaction history
    public void printTransactionHistory(String username) {
        User user = users.get(username);
        if (user != null) {
            List<Transaction> transactions = transactionHistory.get(user.getAccount().getAccountNumber());
            System.out.println("Transaction history for " + username + ":");
            for (Transaction t : transactions) {
                System.out.println(t.getTimestamp() + " - " + t.getType() + ": ₹" + t.getAmount());
            }
        } else {
            System.out.println("User not found");
        }
    }

    // Add transaction
    private void addTransaction(String accountNumber, String type, double amount) {
        Transaction transaction = new Transaction(type, amount);
        transactionHistory.get(accountNumber).add(transaction);
    }

    // Password checking
    public boolean isPasswordCorrect(String username, String password) {
        String storedPassword = getPasswordForUser(username);
        if (storedPassword == null) return false;
        return password.equals(storedPassword);
    }

    // Fetch stored password (dummy logic now — replace with DB call later)
    private String getPasswordForUser(String username) {
        User user = users.get(username);
        return (user != null) ? user.getPassword() : null;
    }

    public void withdraw(String username, double withdrawAmount) {
    }
}