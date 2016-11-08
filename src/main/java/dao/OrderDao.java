package dao;

import java.util.Collection;
import java.util.List;

import model.Book;
import model.Order;
import model.User;

/**
 * Created by Iurii on 25.10.2016.
 */
public interface OrderDao {

    Order createOrder(User user, Order order, List<Book> bookLis);
    List<Order> getAllOrders();
    Order completeOrder(Order order);
    Order addBook(Order order, Book book);
    Order removeBook(Order order, Book book);

}
