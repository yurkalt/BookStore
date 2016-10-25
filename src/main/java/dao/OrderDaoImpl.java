package dao;

import model.Book;
import model.Order;
import model.User;

import java.util.Collection;

/**
 * Created by Iurii on 25.10.2016.
 */
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order createOrder(User user, Order order) {
        return null;
    }

    @Override
    public Collection<Order> getAllOrders() {
        return null;
    }

    @Override
    public Order updateOrderStatus(Order order) {
        return null;
    }

    @Override
    public Order addBook(Order order, Book book) {
        return null;
    }

    @Override
    public Order removeBook(Order order, Book book) {
        return null;
    }
}
