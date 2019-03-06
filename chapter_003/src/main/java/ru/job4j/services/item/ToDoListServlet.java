package ru.job4j.services.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.entity.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

public class ToDoListServlet extends HttpServlet {
private ItemService itemService = new ItemService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> list = itemService.getItems();
        ObjectMapper objectMapper = new ObjectMapper();
        String toJson = objectMapper.writeValueAsString(list);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(toJson);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String desc = req.getParameter("desc");
        Item item = new Item(desc, new Date(System.currentTimeMillis()), false);
        itemService.insertItem(item);
        resp.sendRedirect("/index.html");
    }
}
