package ru.itis.javalab.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        if(email != null){
            return true;
        } else {
            if(request.getServletPath().equals("/signIn")){
                return true;
            } else {
                try {
                    response.sendRedirect("/signIn");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }
    }
}
