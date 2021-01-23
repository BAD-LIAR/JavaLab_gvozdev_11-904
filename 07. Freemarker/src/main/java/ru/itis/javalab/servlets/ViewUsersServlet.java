package ru.itis.javalab.servlets;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.SneakyThrows;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/view_users")
public class ViewUsersServlet extends HttpServlet {

    public final static String PATH = "D:/JavaLab/cource/JavaLab_gvozdev_11-904/07. Freemarker";
    private UsersService usersService;
    private UserUtils userUtils = new UserUtils();
    private PasswordEncoder passwordEncoder;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        usersService = (UsersService) servletContext.getAttribute("usersService");
        passwordEncoder = (BCryptPasswordEncoder) servletContext.getAttribute("passwordEncoder");
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(new FileTemplateLoader(new File(PATH + "/src/main/resources")));

        Template template = configuration.getTemplate("users.ftlh");


        List<User> users = usersService.getAllUsers();



        Map<String, Object> attributes = new HashMap<>();
        attributes.put("users", users);

//        FileWriter fileWriter = new FileWriter("output.txt");
        FileWriter fileWriter = new FileWriter(PATH + "/src/main/webapp/view_users.html");
        template.process(attributes, fileWriter);
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/view_users.html");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = (String) req.getParameter("first_name");
        String lastName = (String) req.getParameter("last_name");


        usersService.saveUser(firstName, lastName);
    }

}
