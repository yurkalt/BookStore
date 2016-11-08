package services;

import dao.BookDao;
import dao.OrderDao;
import dao.UserDao;
import exceptions.PasswordIncorrectException;
import exceptions.UserNotFoundException;
import model.Book;
import model.Order;
import model.User;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Iurii on 07.11.2016.
 */
public class UserServiceImpl implements UserService {

    private final static Logger LOG = Logger.getLogger(UserServiceImpl.class);

    private UserDao userDao;
    private BookDao bookDao;
    private OrderDao orderDao;

    public UserServiceImpl(UserDao userDao, BookDao bookDao, OrderDao orderDao) {
        this.userDao = userDao;
        this.bookDao = bookDao;
        this.orderDao = orderDao;
    }

    @Override
    public User login(String login, String pwd) throws UserNotFoundException, PasswordIncorrectException {
        User user = userDao.findUser(login);
            if (user.getPassword().equals(pwd)){
                LOG.info("Logged in!");
            }
            else {
                LOG.error("Password incorrect!");
                throw new PasswordIncorrectException("Password incorrect!");
            }

        return user;
    }

    @Override
    public Order createOrder(List<Book> books) {
        return null;
    }

    @Override
    public Order cancelOrder(Order order) {
        return null;
    }

    @Override
    public List<Book> showAllBooks() {
        return null;
    }

    @Override
    public Order completeOrder(Order order) {
        return null;
    }
}
