package ru.itis.javalab.servlets;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.repositories.UsersRepositoryJdbcImpl;
import ru.itis.javalab.services.UserUtils;
import ru.itis.javalab.services.UsersService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/sign_in.html");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = (String) req.getParameter("login");
        String password = passwordEncoder.encode((String) req.getParameter("password"));
        System.out.println(passwordEncoder.matches((String) req.getParameter("password"), usersService.getUser(login).getPassword()));


        if (usersService.findUser(login)) {
            if (passwordEncoder.matches((String) req.getParameter("password"), usersService.getUser(login).getPassword())) {
                User user = usersService.getUser(login);
//            Cookie cookie = new Cookie("Auth", user.getId().toString());
//            cookie.setMaxAge(60);
//            resp.addCookie(cookie);
                HttpSession session = req.getSession();
                session.setAttribute("User", user);
                resp.sendRedirect("/home");
            } else {
                doGet(req, resp);
            }
        } else {
            doGet(req, resp);
        }
    }
}
