package ru.job4j.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import ru.job4j.entity.Car;
import ru.job4j.entity.Image;
import ru.job4j.storage.CarStore;
import ru.job4j.storage.ImageStore;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;

public class ImageUploadServlet extends HttpServlet {
    ImageStore imageStore = ImageStore.getInstance();
    CarStore carStore = CarStore.getInstance();
    int carId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       carId = Integer.valueOf(req.getParameter("carId"));
        resp.setContentType("text/html");
        req.getRequestDispatcher("WEB-INF/views/uploadImage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Car car = carStore.getById(carId);
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
                        image = new Image();
                        image.setImage(bytes);
                        image.setCar(car);
                        result = imageStore.add(image);
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
        if (result > 0) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
            //req.setAttribute("carId", carId);
        } else {
            req.setAttribute("error", "Cannot be uploaded");
            doGet(req, resp);

        }
}
}
