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
import java.util.ArrayList;
import java.util.List;

public class ContentFilterServlet extends HttpServlet {
    private CarStore carStore = CarStore.getInstance();
    private ImageStore imageStore = ImageStore.getInstance();
    private List<Car> list = null;
    private List<Image> images;
    private List<Car> result = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String soldStatus = req.getParameter("filterSold");
        String withImage = req.getParameter("filterImage");
        String name = req.getParameter("filterName");

        if (soldStatus != null && !name.equals("")) {
            list = carStore.filterCarsBySoldAndName(false, name);
        } else if (soldStatus != null) {
            list = carStore.filterCarsBySold(false);
        } else if (!name.equals("")) {
            list = carStore.filterCarsByName(name);
        } else {
            list = carStore.getAll();
        }

        for (Car c : list) {
            images  = imageStore.getImagesByCarId(c.getId());
            c.setImages(images);
        }

        if (withImage != null) {
            for (Car c : list) {
                if (!c.getImages().isEmpty()) {
                    result.add(c);
                }
            }
            list = result;
        }
        req.setAttribute("cars", list);
        req.getRequestDispatcher("WEB-INF/views/CarFilter.jsp").forward(req, resp);
    }
}
