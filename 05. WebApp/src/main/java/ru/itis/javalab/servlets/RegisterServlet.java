package ru.itis.javalab.servlets;

import com.sun.net.httpserver.HttpServer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UserUtils;
import ru.itis.javalab.services.UsersService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UsersService usersService;
    private UserUtils userUtils = new UserUtils();
    private PasswordEncoder passwordEncoder;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        usersService = (UsersService) servletContext.getAttribute("usersService");
        passwordEncoder = (BCryptPasswordEncoder) servletContext.getAttribute("passwordEncoder");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/register.html");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = (String) req.getParameter("login");
        String password = passwordEncoder.encode((String) req.getParameter("password"));
        if (!usersService.findUser(login, password)) {
            usersService.saveUser(login, password);
            User user = usersService.getUser(login, password);
            Cookie cookie = new Cookie("Auth", user.getId().toString());
            cookie.setMaxAge(60);
            resp.addCookie(cookie);
            resp.sendRedirect("/login");
        } else {
            doGet(req, resp);
        }
    }

}
