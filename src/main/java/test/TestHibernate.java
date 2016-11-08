package test;

import dao.BookDaoImpl;
import model.Book;
import model.Order;
import model.User;
import model.UserIdentifier;
import utils.ConnectionFactory;

import javax.persistence.EntityManager;

/**
 * Created by Iurii on 26.10.2016.
 */
public class TestHibernate {
    public static void main(String[] args) {

        BookDaoImpl bookDao = new BookDaoImpl();
        bookDao.addBook(new Book("Kobzar","Shevchenko", 35, 5));
        System.out.println(bookDao.getAllBooks());
    }
}
