package servlet;

import exceptions.PasswordIncorrectException;
import exceptions.UserNotFoundException;
import model.Book;
import model.User;
import model.UserIdentifier;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import services.UserService;
import services.UserServiceImpl;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
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


@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(LoginServlet.class);

    private UserService userService;


    @Override
    public void init() throws ServletException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        userService = context.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String pwd = req.getParameter("pass");

        try {
            User user = userService.login(name, pwd);
            HttpSession session = req.getSession(true);
            req.setAttribute("userService", userService);
            session.setAttribute("user", user);
            List<Book> books = userService.getAllBooks();
            if (user.getIdentifier() == UserIdentifier.U) { // Checking for ADMIN or USER rights.
                req.setAttribute("books", books);
                req.getRequestDispatcher("/WEB-INF/pages/shop.jsp").forward(req,resp);
            } else {
                req.setAttribute("orders", userService.getAllActiveOrders());
                req.getRequestDispatcher("/WEB-INF/pages/admin.jsp").forward(req,resp); }
        } catch (UserNotFoundException | PasswordIncorrectException e) {
            req.setAttribute("errorTitle", "Login Error");
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
        }
    }
}

