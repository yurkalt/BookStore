package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iurii on 25.10.2016.
 */
@Entity
@Table(name = "user")
@NamedQueries({@NamedQuery(name = "getAllUsers", query = "SELECT c FROM User c")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated ( EnumType.STRING )
    private UserIdentifier identifier;

    @Column(name = "balance", nullable = true)
    private long balance;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    List<Order> orders = new ArrayList<>();

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

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
