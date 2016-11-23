package filters;


import model.User;
import model.UserIdentifier;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Iurii on 21.11.2016.
 */
public class AdminFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(AdminFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        User user = (User) req.getSession().getAttribute("user");
        if (!user.getIdentifier().equals(UserIdentifier.A)){
            req.setAttribute("errorTitle", "Permission denied!");
            req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
