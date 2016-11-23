package dao;

import exceptions.InsufficientFundsException;
import exceptions.UnknownBookException;
import model.Book;
import model.Order;
import model.OrderStatus;
import model.User;
import utils.ConnectionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    public Order newOrder(User user) {
        Order order = new Order();
        long totalPrice = 0;
        manager.getTransaction().begin();
        order.setStatus(OrderStatus.NEW);
        order.setBookList(new ArrayList<Book>());
        order.setUser(user);
        order.setTotal(totalPrice);

        manager.merge(order);
        manager.getTransaction().commit();
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        manager.getTransaction().begin();
        Query query = manager.createNamedQuery("getAllOrders");
        List<Order> orders = query.getResultList();
        manager.getTransaction().commit();
        return orders;
    }

    @Override
    public Order completeOrder(Order order) throws InsufficientFundsException {
           manager.getTransaction().begin();
           User user = order.getUser();
           userDao.chargeUser(order.getTotal(), user);
           order.setStatus(OrderStatus.DONE);
           manager.merge(order);
           manager.merge(user);
           manager.getTransaction().commit();
        return order;
    }

    @Override
    public Order addBook(Order order, Book book) {
        try {
            bookDao.removeBook(book);
            manager.getTransaction().begin();
            if(order.getStatus().equals(OrderStatus.NEW)){
                order.setStatus(OrderStatus.IN_PROGRESS);
            }
            order.getBookList().add(book);
            order.setTotal(order.getTotal()+book.getPrice());
            manager.merge(order);
        }   catch (UnknownBookException e) {
            e.printStackTrace();
        } finally {
            manager.getTransaction().commit();
        }
        return order;
    }

    @Override
    public Order removeBook(Order order, Book book) {
         bookDao.addBook(new Book(book.getTitle(), book.getAuthor(),book.getPrice(),1)); // preventing adding book count to book count in book dao.
         manager.getTransaction().begin();
         order.getBookList().remove(book);
         order.setTotal(order.getTotal()-book.getPrice());
         manager.merge(order);
         manager.getTransaction().commit();
         return order;
    }

    @Override
    public Order findById(long orderID) {
        manager.getTransaction().begin();
        Order found = manager.find(Order.class, orderID);
        manager.getTransaction().commit();
        return found;
    }

    @Override
    public Order cancelOrder(Order order) {
        List<Book> books =new ArrayList<>(order.getBookList());
        for(Book book:books){
            removeBook(order, book);
        }
        order.setStatus(OrderStatus.CANCELED);
        return null;
    }
}
