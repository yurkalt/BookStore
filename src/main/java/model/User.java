package model;

/**
 * Created by Iurii on 25.10.2016.
 */
public class User {
    private int id;
    private String userName;
    private String password;
    private UserIdentifier identifier;
    private int balance;

    public User(String userName, String password, UserIdentifier identifier, int balance) {
        this.userName = userName;
        this.password = password;
        this.identifier = identifier;
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserIdentifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UserIdentifier identifier) {
        this.identifier = identifier;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
