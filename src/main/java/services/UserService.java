package services;

import exceptions.PasswordIncorrectException;
import exceptions.UserNotFoundException;
import model.Book;
import model.Order;
import model.User;

import java.util.List;

/**
 * Created by Iurii on 03.11.2016.
 */
public interface UserService {

    User login(String login, String pwd) throws UserNotFoundException, PasswordIncorrectException;
    Order createOrder(List<Book> books);
    Order cancelOrder(Order order);
    List<Book> showAllBooks();
    Order completeOrder(Order order);


}
