package test;

import dao.BookDaoImpl;
import dao.UserDaoImpl;
import model.Book;
import model.Order;
import model.User;
import model.UserIdentifier;
import utils.ConnectionFactory;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;

/**
 * Created by Iurii on 26.10.2016.
 */
public class TestHibernate {
    public static void main(String[] args) {

         new UserDaoImpl().createUser(new User("user","user",UserIdentifier.U, 500));

    }
}
