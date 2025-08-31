package Bank;

import java.time.LocalDateTime;

public class Transaction {
    private final String type; // Deposit, Withdraw, Transfer
    private final double balance;
    private final LocalDateTime timestamp;

    public Transaction(String type, double balance) {
        this.type = type;
        this.balance = balance;
        this.timestamp = LocalDateTime.now();
    }

    public String getType() { return type; }
    public double getAmount() { return balance; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
