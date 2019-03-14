package ru.job4j.servlets;

import ru.job4j.entity.*;
import ru.job4j.storage.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCarServlet extends HttpServlet {

    private CarStore carStore = CarStore.getInstance();
    private UserStore userStore = UserStore.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("WEB-INF/views/AddCar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String body = req.getParameter("body");
        String engine = req.getParameter("engine");
        String transmission = req.getParameter("transmission");
        String location = req.getParameter("location");
        int price = Integer.parseInt(req.getParameter("price"));
        String owner = req.getParameter("owner");

        Car car = new Car(name);
        car = carStore.contructCar(car, body, engine, transmission);
        car.setLocation(location);
        car.setPrice(price);
        car.setUser(userStore.getByName(owner));

        int result = carStore.add(car);
        if (result > 0) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Cannot be added");
            doGet(req, resp);

        }

    }
}
