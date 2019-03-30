package ru.job4j.car.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.car.entity.Car;
import ru.job4j.car.entity.Image;
import ru.job4j.car.storage.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class MainController {
    private CarStore carStore = CarStore.getInstance();
    private ImageStore imageStore = ImageStore.getInstance();
    private BodyStore bodyStore = BodyStore.getInstance();
    private EngineStore engineStore = EngineStore.getInstance();
    private TransmissionStore transmissionStore = TransmissionStore.getInstance();
    private int carIdForImage;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listCars(Model model) {
        List<Car> list = carStore.getAll();
        List<Image> images;
        for (Car c : list) {
            images  = imageStore.getImagesByCarId(c.getId());
            c.setImages(images);
        }
        System.out.println(list);
        model.addAttribute("cars", list);
        return "Cars";
    }

    @RequestMapping(value = "/addCarForm", method = RequestMethod.GET)
    public String addCarForm(Model model) {
        Car car = new Car();
        model.addAttribute("newCar", car);
        return "AddCarForm";
    }

    @RequestMapping(value = "/addCar", method = RequestMethod.POST)
    public String addCar(HttpServletRequest req, Model model) {
        String name = req.getParameter("name");
        String body = req.getParameter("body");
        String engine = req.getParameter("engine");
        String transmission = req.getParameter("transmission");
        String location = req.getParameter("location");
        int price = Integer.parseInt(req.getParameter("price"));
        //String owner = req.getParameter("owner");

        Car car = new Car(name);
        car = carStore.contructCar(car, body, engine, transmission);
        car.setLocation(location);
        car.setPrice(price);
        //car.setUser(userStore.getByName(owner));

        int result = carStore.add(car);
        if (result > 0) {
            return "redirect:/";
        } else {
            model.addAttribute("error", "Cannot be added");
            return "addCarForm";
        }
    }

    @RequestMapping("/getBody")
    public void getBodyTypes(HttpServletResponse resp) throws IOException {
        List<String> bodyNames = bodyStore.getBodyTypes();
        ObjectMapper objectMapper = new ObjectMapper();
        String toJson = objectMapper.writeValueAsString(bodyNames);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        System.out.println(toJson);
        writer.append(toJson);
        writer.flush();
    }

    @RequestMapping("/getEngine")
    public void getEngineType(HttpServletResponse resp) throws IOException {
        List<String> engineNames = engineStore.getEngineTypes();
        ObjectMapper objectMapper = new ObjectMapper();
        String toJson = objectMapper.writeValueAsString(engineNames);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        System.out.println(toJson);
        writer.append(toJson);
        writer.flush();

    }

    @RequestMapping("/getTransmission")
    public void getTransmission(HttpServletResponse resp) throws IOException {
        List<String> transNames = transmissionStore.getTransmissionTypes();
        ObjectMapper objectMapper = new ObjectMapper();
        String toJson = objectMapper.writeValueAsString(transNames);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        System.out.println(toJson);
        writer.append(toJson);
        writer.flush();
    }

    @RequestMapping("/getLocation")
    public void getLocation(HttpServletResponse resp) throws IOException {
        List<String> locations = carStore.getLocation();
        ObjectMapper objectMapper = new ObjectMapper();
        String toJson = objectMapper.writeValueAsString(locations);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        System.out.println(toJson);
        writer.append(toJson);
        writer.flush();
    }

    @RequestMapping(value = "/updateDelete", method = RequestMethod.GET)
    public void updateSoldStatus(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("carId"));
        boolean done = Boolean.valueOf(req.getParameter("sold"));
        carStore.statusChange(id, done);
    }

    @RequestMapping(value = "/updateDelete", method = RequestMethod.POST)
    public String deleteCar(HttpServletRequest req, Model model) {
        int id = Integer.valueOf(req.getParameter("carId"));
        int result = carStore.delete(id);
        if (result == 0) {
            model.addAttribute("error", "Cannot be deleted");
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/contentFilter", method = RequestMethod.GET)
    public String contentFilter(HttpServletRequest req, Model model) {
        String soldStatus = req.getParameter("filterSold");
        String withImage = req.getParameter("filterImage");
        String name = req.getParameter("filterName");
        List<Car> list = null;
        List<Image> images;
        List<Car> result = new ArrayList<>();

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
       model.addAttribute("cars", list);
       return "CarFilter";
    }

    @RequestMapping(value = "/imageUpload", method = RequestMethod.GET)
    public String uploadImage(HttpServletRequest req) {
        carIdForImage = Integer.valueOf(req.getParameter("carId"));
        return "uploadImage";
    }

    @RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
    public String addImage(HttpServletRequest req, Model model) throws IOException {
        Car car = carStore.getById(carIdForImage);
        Image image;
        int result = 0;
        if (ServletFileUpload.isMultipartContent(req)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List<FileItem> items = upload.parseRequest(req);
                Iterator iterator = items.iterator();
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();
                    if (!item.isFormField()) {
                        byte[] bytes = IOUtils.toByteArray(item.getInputStream());
                        image = new Image(bytes);
                        image.setCar(car);
                        result = imageStore.add(image);
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
        if (result > 0) {
            return "redirect:/";
        } else {
           model.addAttribute("error", "Cannot be uploaded");
            return "uploadImage";

        }
    }

}
