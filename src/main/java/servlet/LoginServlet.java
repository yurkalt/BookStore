package servlet;

import exceptions.PasswordIncorrectException;
import exceptions.UserNotFoundException;
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
 * Created by Iurii on 07.11.2016.
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(LoginServlet.class);

    private UserService userService;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserService userService = (UserService) context.getBean("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //validation

        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String pwd = req.getParameter("pass");

        try {
            User user = userService.login(name, pwd);

        } catch (UserNotFoundException | PasswordIncorrectException e) {
            req.setAttribute("errorTitle", "Login Error");
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
        }


        // redirect
    }
}

