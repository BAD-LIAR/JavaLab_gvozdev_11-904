package ru.itis.javalab.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UserUtils;
import ru.itis.javalab.services.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;


public class AuthFilter implements Filter {

    private UsersService usersService;
    private UserUtils userUtils = new UserUtils();
    private static final Logger logger = LoggerFactory.getLogger(
            AuthFilter.class);



    @Override
    public void init(FilterConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        usersService = (UsersService) servletContext.getAttribute("usersService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String servletPath = req.getServletPath();

//        System.out.println("#INFO " + new Date() + " - ServletPath :" + servletPath //
//                + ", URL =" + req.getRequestURL());

        String[] strings = servletPath.split("/");

        logger.info("message");


//        Cookie[] cookies = req.getCookies();
//        String token = null;
//        if (cookies != null) {
//            for (Cookie c : cookies) {
//                if ("Auth".equals(c.getName())) {
//                    token = c.getValue();
//                    break;
//                }
//            }
//        }
//
//        if (token == null) {
//            if (servletPath.equals("/login") || servletPath.equals("/register")) {
//                chain.doFilter(servletRequest, servletResponse);
//            } else {
//                ((HttpServletResponse) servletResponse).sendRedirect("/login");
//            }
//        } else {
//
//
//            User user = usersService.getUser(token);
//            if (token.equals(user.getId().toString())) {
//                if (servletPath.equals("/login")) {
//                    ((HttpServletResponse) servletResponse).sendRedirect("/home");
//                } else {
//                    chain.doFilter(servletRequest, servletResponse);
//                }
//            } else {
//                if (servletPath.equals("/login")) {
//                    chain.doFilter(servletRequest, servletResponse);
//                } else {
//                    ((HttpServletResponse) servletResponse).sendRedirect("/login");
//                }
//            }
//        }

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");
        if(user == null){
            if(servletPath.equals("/login") || servletPath.equals("/register")){
                chain.doFilter(servletRequest, servletResponse);
            } else {
                res.sendRedirect("/login");
            }
        } else {
            if(servletPath.equals("/login") || servletPath.equals("/register")){
                res.sendRedirect("/home");
            } else {
                chain.doFilter(servletRequest, servletResponse);
            }
        }
    }


    @Override
    public void destroy() {

    }

}
