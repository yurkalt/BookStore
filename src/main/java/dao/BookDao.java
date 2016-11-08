package dao;

import exceptions.UnknownBookException;
import model.Book;

import java.util.List;

/**
 * Created by Iurii on 28.10.2016.
 */
public interface BookDao {
    Book addBook(Book book);
    Book removeBook(Book book) throws UnknownBookException;
    List<Book> getAllBooks();
}
