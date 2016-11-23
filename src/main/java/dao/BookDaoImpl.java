package dao;

import exceptions.UnknownBookException;
import model.Book;
import utils.ConnectionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * Created by Iurii on 28.10.2016.
 */
public class BookDaoImpl implements BookDao {

    private EntityManager manager = ConnectionFactory.createEntityManager();

    public BookDaoImpl() {
    }

    @Override
    public Book addBook(Book book) {
        manager.getTransaction().begin();
        Query query = manager.createNamedQuery("findBook");
        query.setParameter("author", book.getAuthor());
        query.setParameter("title", book.getTitle());
        List<Book> books = query.getResultList();
        Book found;

        if (books.isEmpty()) {
            found = book;
            manager.persist(found);
        } else {
            found = books.get(0);
            found.setCount(found.getCount() + book.getCount());
            manager.merge(found);
        }
        manager.getTransaction().commit();
        return found;
    }

    @Override
    public Book removeBook(Book book) throws UnknownBookException {
        manager.getTransaction().begin();
        Query query = manager.createNamedQuery("findBook");
        query.setParameter("author", book.getAuthor());
        query.setParameter("title", book.getTitle());
        List<Book> books = query.getResultList();
        Book found;
        if ( books.isEmpty() || (books.get(0).getCount() == 0)) {
            throw new UnknownBookException("Book is not available!");
        } else {
            found = books.get(0);
            found.setCount(found.getCount()-1);
            manager.merge(found);
        }
        manager.getTransaction().commit();
        return found;
    }

    @Override
    public List<Book> getAllBooks() {
        manager.getTransaction().begin();
        Query query = manager.createNamedQuery("getAllBooks");
        List<Book> books = query.getResultList();
        manager.getTransaction().commit();
        return books;
    }

    @Override
    public Book findByID(int bookID) {
        manager.getTransaction().begin();
        Book found = manager.find(Book.class, bookID);
        manager.getTransaction().commit();
        return found;
    }
}
