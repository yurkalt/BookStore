package dao;


import exceptions.InsufficientFundsException;
import exceptions.UserNotFoundException;
import model.User;
import utils.ConnectionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Iurii on 26.10.2016.
 */
public class UserDaoImpl implements UserDao {

    private EntityManager manager = ConnectionFactory.createEntityManager();

    public UserDaoImpl() {
    }

    @Override
    public User createUser() {
        return null;
    }

    @Override
    public User updateUser() {
        return null;
    }

    @Override
    public User findUser(String name) throws UserNotFoundException {

        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUserName().equals(name)) {
                return user;
            }
        }
        throw new UserNotFoundException("Wrong username!");
    }

    @Override
    public User chargeUser(long toPay, User user) throws InsufficientFundsException {
        if (user.getBalance() > toPay) {
            user.setBalance(user.getBalance() - toPay);
        } else throw new InsufficientFundsException("Not enough money! Please refill the account or change the order.");
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        manager.getTransaction().begin();
        Query query = manager.createNamedQuery("getAllUsers");
        List<User> users = query.getResultList();
        manager.getTransaction().commit();
        return users;
    }


}
