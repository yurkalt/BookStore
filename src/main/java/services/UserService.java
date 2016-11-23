package services;

import exceptions.InsufficientFundsException;
import exceptions.PasswordIncorrectException;
import exceptions.UserNotFoundException;
import model.Book;
import model.Order;
import model.User;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Created by Iurii on 03.11.2016.
 */

public interface UserService {

    User login(String login, String pwd) throws UserNotFoundException, PasswordIncorrectException;
    Order createOrder(User user);
    Order cancelOrder(Order order);
    List<Book> getAllBooks();
    Order completeOrder(Order order) throws InsufficientFundsException;
    Book findBookByID(int bookID);
    Order addBookToOrder(Order order, Book book);
    List<Order> getAllActiveOrders();

    Order findOrderById(long orderID);

}
