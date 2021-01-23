package ru.itis.javalab.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CsrfInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (request.getMethod().equals("POST")) {
            String requestCsrf = request.getParameter("_csrf_token");
            Set<String> stringSet = (HashSet) request.getSession(false).getAttribute("_csrf_token" );
            if (stringSet.contains(requestCsrf)) {
                stringSet.remove(requestCsrf);
            } else {
                response.sendRedirect("/signIn");
                return false;
            }
        }
        if (request.getMethod().equals("GET")) {
            String csrf = UUID.randomUUID().toString();
            request.setAttribute("_csrf_token", csrf);
            Set<String> stringSet = (HashSet) request.getSession().getAttribute("_csrf_token" );
            if (stringSet == null){
                stringSet = new HashSet<>();
                request.getSession().setAttribute("_csrf_token", stringSet);
            }
            stringSet.add(csrf);
            request.getSession().setAttribute("_csrf_token", stringSet);
        }
        return true;
    }
}
