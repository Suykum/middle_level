package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.entity.CarBody;
import ru.job4j.storage.BodyStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GetBodyTypesServlet extends HttpServlet {
    BodyStore bodyStore = BodyStore.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> bodyNames = bodyStore.getBodyTypes();

        ObjectMapper objectMapper = new ObjectMapper();
        String toJson = objectMapper.writeValueAsString(bodyNames);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        System.out.println(toJson);
        writer.append(toJson);
        writer.flush();
    }
}
