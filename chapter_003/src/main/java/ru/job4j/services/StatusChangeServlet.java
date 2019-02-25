package ru.job4j.services;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class StatusChangeServlet extends HttpServlet {
    private ItemService itemService = new ItemService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("taskId"));
        boolean done = Boolean.valueOf(req.getParameter("taskDone"));
        itemService.changeStatus(id, done);
    }
}
