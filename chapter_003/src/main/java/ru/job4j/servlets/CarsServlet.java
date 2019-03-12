package ru.job4j.servlets;

import ru.job4j.entity.Car;
import ru.job4j.entity.Image;
import ru.job4j.storage.CarStore;
import ru.job4j.storage.ImageStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CarsServlet extends HttpServlet {
    CarStore carStore = CarStore.getInstance();
    ImageStore imageStore = ImageStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<Car> list = carStore.getAll();
        List<Image> images;
        for (Car c : list) {
           images  = imageStore.getImagesByCarId(c.getId());
           c.setImages(images);
        }

        req.setAttribute("cars", list);
        req.getRequestDispatcher("WEB-INF/views/Cars2.jsp").forward(req, resp);
    }
}
