package ru.itdrive.web.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static ru.itdrive.web.filters.ResponseUtil.sendForbidden;

public class CsrfFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getMethod().equals("POST")) {
            String requestCsrf = request.getParameter("_csrf_token");
            Set<String> stringSet = (HashSet) request.getSession(false).getAttribute("_csrf_token" );
            if (stringSet.contains(requestCsrf)) {
                stringSet.remove(requestCsrf);
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                sendForbidden(request, response);
                return;
            }
        }
        if (request.getMethod().equals("GET")) {
            String csrf = UUID.randomUUID().toString();
            request.setAttribute("_csrf_token", csrf);
            Set<String> stringSet = (HashSet) request.getSession().getAttribute("_csrf_token" );
            if (stringSet == null){
                stringSet = new HashSet<>();
            }
            stringSet.add(csrf);
            request.getSession().setAttribute("_csrf_token", stringSet);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
