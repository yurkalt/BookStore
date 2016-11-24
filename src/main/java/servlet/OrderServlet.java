package servlet;

import exceptions.InsufficientFundsException;
import model.Order;
import model.User;
import org.apache.log4j.Logger;
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

/**
 * Created by Iurii on 17.11.2016.
 */
@WebServlet(urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(LoginServlet.class);

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
        try {
            session = req.getSession(false);
            req.setAttribute("user", session.getAttribute("user"));
            req.setAttribute("orderBooks", ((Order) session.getAttribute("order")).getBookList()); // get book list from order
        }catch (NullPointerException e) {
            req.setAttribute("errorTitle", "Order is empty!");
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/WEB-INF/pages/order.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        order = (Order) req.getSession().getAttribute("order");
        try {
            userService.completeOrder(order);
        } catch (InsufficientFundsException e) {
            req.setAttribute("errorTitle", "Insufficient founds! Please refill the account.");
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(req,resp);

    }
}
