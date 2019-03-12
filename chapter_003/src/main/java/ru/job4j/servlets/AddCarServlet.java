package ru.job4j.servlets;

import ru.job4j.entity.*;
import ru.job4j.storage.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("WEB-INF/views/AddCar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarStore carStore = CarStore.getInstance();
        BodyStore bodyStore = BodyStore.getInstance();
        EngineStore engineStore = EngineStore.getInstance();
        TransmissionStore transmissionStore = TransmissionStore.getInstance();
        UserStore userStore = UserStore.getInstance();

        // ValidateUser

        String name = req.getParameter("name");
        String body = req.getParameter("body");
        String engine = req.getParameter("engine");
        String transmission = req.getParameter("transmission");
        String location = req.getParameter("location");
        int price = Integer.parseInt(req.getParameter("price"));
        String owner = req.getParameter("owner");

        Car car = new Car(name);

        CarBody carBody = bodyStore.getByName(body);
        if (carBody == null) {
            carBody = new CarBody(body);
            bodyStore.add(carBody);
        }
        car.setBody(carBody);

        CarEngine carEngine = engineStore.getByName(engine);
        if (carEngine == null) {
            carEngine = new CarEngine(engine);
            engineStore.add(carEngine);
        }
        car.setEngine(carEngine);

        CarTransmission carTransmission = transmissionStore.getByName(transmission);
        if (carTransmission == null) {
            carTransmission = new CarTransmission(transmission);
            transmissionStore.add(carTransmission);
        }
        car.setTransmission(carTransmission);

        User carOwner = userStore.getByName(owner);
        car.setUser(carOwner);
        car.setLocation(location);
        car.setPrice(price);
        int result = carStore.add(car);


        if (result > 0) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Cannot be added");
            doGet(req, resp);

        }

    }
}
