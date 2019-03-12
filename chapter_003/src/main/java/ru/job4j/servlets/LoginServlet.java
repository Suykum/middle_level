package ru.job4j.servlets;

import ru.job4j.entity.User;
import ru.job4j.services.car.ValidateUser;
import ru.job4j.storage.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                req.getRequestDispatcher("WEB-INF/views/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserStore userStore = UserStore.getInstance();
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userStore.loginPermit(login, password);
        if (user != null) {
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("login", login);
            }
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Invalid user");
            doGet(req, resp);
        }
    }
}
