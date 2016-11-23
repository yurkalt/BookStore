package servlet;

import exceptions.PasswordIncorrectException;
import exceptions.UserNotFoundException;
import model.Book;
import model.Order;
import model.User;
import model.UserIdentifier;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Iurii on 07.11.2016.
 */
@WebServlet(urlPatterns = {"/shop"})
public class ShopServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ShopServlet.class);

    private UserService userService;
    private User user;
    private Order order;
    private HttpSession session;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        userService = context.getBean(UserService.class);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/shop.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession(false);
        user = (User)session.getAttribute("user");

        if (session.getAttribute("order") == null) {
            order = userService.createOrder(user);
        } else {
            order = (Order) session.getAttribute("order");
        }
        int bookID = (int) Integer.parseInt(req.getParameter("bookID"));
        Book book = userService.findBookByID(bookID);
        userService.addBookToOrder(order, book);
        session.setAttribute("order", order);

        System.out.println(order.getBookList().size());
        List<Book> books = userService.getAllBooks();
        req.setAttribute("orderBooks", order.getBookList());
        req.setAttribute("books", books);
        req.getRequestDispatcher("/WEB-INF/pages/shop.jsp").forward(req,resp);



    }
}