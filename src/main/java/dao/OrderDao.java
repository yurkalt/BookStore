package dao;

import java.util.Collection;
import java.util.List;

import exceptions.InsufficientFundsException;
import model.Book;
import model.Order;
import model.User;

/**
 * Created by Iurii on 25.10.2016.
 */
public interface OrderDao {

    Order newOrder(User user);
    List<Order> getAllOrders();
    Order completeOrder(Order order) throws InsufficientFundsException;
    Order addBook(Order order, Book book);
    Order removeBook(Order order, Book book);

    Order findById(long orderID);

    Order cancelOrder(Order order);
}
