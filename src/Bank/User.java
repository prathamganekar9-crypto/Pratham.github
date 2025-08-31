package Bank;

public class User {
    private final String username;
    private final String password;
    private final BankAccount account;

    public User(String username, String password, BankAccount account) {
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