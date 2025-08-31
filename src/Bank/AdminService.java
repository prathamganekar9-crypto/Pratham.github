package Bank;

import java.util.Map;

public class AdminService {
    private final Map<String, User> users;

    public AdminService(Map<String, User> users) {
        this.users = users;
    }

    public void freezeAccount(String username) {
        User user = users.get(username);
        if (user != null) {
            // example: freeze account (you must define this in User class)
            System.out.println("Account frozen for user: " + username);
        } else {
            System.out.println("User not found: " + username);
        }
    }

    public void unfreezeAccount(String username) {
        User user = users.get(username);
        if (user != null) {
            // example: unfreeze account
            System.out.println("Account unfrozen for user: " + username);
        } else {
            System.out.println("User not found: " + username);
        }
    }
}