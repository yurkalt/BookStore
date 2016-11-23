package services;

import dao.BookDao;
import dao.OrderDao;
import dao.UserDao;
import exceptions.InsufficientFundsException;
import exceptions.PasswordIncorrectException;
import exceptions.UserNotFoundException;
import model.Book;
import model.Order;
import model.OrderStatus;
import model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iurii on 07.11.2016.
 */
public class UserServiceImpl implements UserService {

    private final static Logger LOG = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private OrderDao orderDao;

    public UserServiceImpl(UserDao userDao, BookDao bookDao, OrderDao orderDao) {
        this.userDao = userDao;
        this.bookDao = bookDao;
        this.orderDao = orderDao;
    }

    public UserServiceImpl() {
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
    public Order createOrder(User user) {
        return orderDao.newOrder(user);
    }

    @Override
    public Order cancelOrder(Order order) {
        return orderDao.cancelOrder(order);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    public Order completeOrder(Order order) throws InsufficientFundsException {
        orderDao.completeOrder(order);
        return order;
    }

    @Override
    public Book findBookByID(int bookID) {
        return bookDao.findByID(bookID);
    }

    @Override
    public Order addBookToOrder(Order order, Book book) {
        return orderDao.addBook(order,book);
    }

    @Override
    public List<Order> getAllActiveOrders() {
        List<Order> result = new ArrayList<>();
        List<Order> orders =  orderDao.getAllOrders();
        for (Order order: orders){
            if (order.getStatus() == OrderStatus.IN_PROGRESS || order.getStatus() == OrderStatus.NEW) {
                result.add(order);
            }
        }
        return result;
    }

    @Override
    public Order findOrderById(long orderID) {
        return orderDao.findById(orderID);
    }
}
