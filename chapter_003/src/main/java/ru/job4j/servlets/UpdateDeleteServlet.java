package ru.job4j.servlets;

import ru.job4j.storage.CarStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateDeleteServlet extends HttpServlet {
    private CarStore carStore = CarStore.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("carId"));
        boolean done = Boolean.valueOf(req.getParameter("sold"));
        carStore.statusChange(id, done);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("carId"));
        int result = carStore.delete(id);
        if (result > 0) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Cannot be deleted");
            doGet(req, resp);

        }
    }
}
