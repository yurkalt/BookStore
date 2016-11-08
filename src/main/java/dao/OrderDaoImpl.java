package dao;

import exceptions.InsufficientFundsException;
import exceptions.UnknownBookException;
import model.Book;
import model.Order;
import model.OrderStatus;
import model.User;
import utils.ConnectionFactory;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;

/**
 * Created by Iurii on 25.10.2016.
 */
public class OrderDaoImpl implements OrderDao {

    private EntityManager manager = ConnectionFactory.createEntityManager();

    private UserDao userDao;
    private BookDao bookDao;

    public OrderDaoImpl(UserDao userDao, BookDao bookDao) {
        this.userDao = userDao;
        this.bookDao = bookDao;
    }

    @Override
    public Order createOrder(User user, Order order, List<Book> bookList) {

        long totalPrice = 0;

        for (Book book : bookList) {
            totalPrice+=book.getPrice();
        }
        manager.getTransaction().begin();
        order.setBookList(bookList);
        order.setStatus(OrderStatus.NEW);
        order.setUser(user);
        order.setTotal(totalPrice);
        manager.persist(order);
        manager.getTransaction().commit();
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public Order completeOrder(Order order) {
       try {
           manager.getTransaction().begin();
           User user = order.getUser();
           userDao.chargeUser(order.getTotal(), user);
           manager.persist(order);
           manager.persist(user);
       } catch (InsufficientFundsException e) {
           e.printStackTrace();
       } finally {
           manager.getTransaction().commit();
       }
        return order;
    }

    @Override
    public Order addBook(Order order, Book book) {
        try {
            manager.getTransaction().begin();
            bookDao.removeBook(book);
            order.getBookList().add(book);
            manager.persist(order);
        }   catch (UnknownBookException e) {
            e.printStackTrace();
        } finally {
            manager.getTransaction().commit();
        }
        return order;
    }

    @Override
    public Order removeBook(Order order, Book book) {
         manager.getTransaction().begin();
         bookDao.addBook(book);
         order.getBookList().remove(book);
         manager.getTransaction().commit();
         return order;
    }
}
