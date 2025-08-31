package BankAccount;

public class BankAccount {
    private final String accountNumber;
    private double balance;
    private boolean isFrozen;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0; // Start with zero balance
        this.isFrozen = false;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void freeze() {
        isFrozen = true;
    }

    public void unfreeze() {
        isFrozen = false;
    }

    //  Deposit should not allow negative/zero
    public void deposit(double amount) {
        if (amount > 0 && !isFrozen) {
            balance += amount;
        }
    }

    //  Withdraw checks balance, amount > 0, and frozen status
    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount && !isFrozen) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
