package ru.job4j.servlets;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthentificateFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String path = ((HttpServletRequest) req).getServletPath();
        if (request.getRequestURI().contains("/login") || request.getRequestURI().contains("/addUser")) {
            chain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession();
            if (session.getAttribute("login") == null) {
                ((HttpServletResponse) resp).sendRedirect(String.format("%s/login.do", request.getContextPath()));
                return;
            }
            chain.doFilter(req, resp);
        }

    }


    @Override
    public void destroy() {

    }
}
