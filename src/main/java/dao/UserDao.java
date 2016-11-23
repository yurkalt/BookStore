package dao;


import exceptions.InsufficientFundsException;
import exceptions.UserNotFoundException;
import model.User;

import java.util.List;

/**
 * Created by Iurii on 25.10.2016.
 */
public interface UserDao {
    User createUser(User user);
    User updateUser();
    User findUser(String name) throws UserNotFoundException;
    User chargeUser(long toPay, User user) throws InsufficientFundsException;
    List<User> getAllUsers();
}
