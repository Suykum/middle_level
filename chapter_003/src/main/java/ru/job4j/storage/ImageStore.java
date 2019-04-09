package ru.job4j.storage;

import ru.job4j.entity.Image;

import java.util.List;

import static ru.job4j.storage.Wrapper.tx;

public class ImageStore implements Store<Image> {
    private static ImageStore ourInstance = new ImageStore();

    public static ImageStore getInstance() {
        return ourInstance;
    }

    private ImageStore() {
    }

    @Override
    public int add(Image entity) {
        return tx(session -> { session.save(entity); return entity.getId(); });
    }

    @Override
    public int update(Image entity) {
        return tx(session -> { session.update(entity); return entity.getId(); });
    }

    @Override
    public int delete(int id) {
        return tx(session -> { Image image = session.get(Image.class, id); session.delete(image); return image.getId(); });
    }

    @Override
    public List<Image> getAll() {
        return tx(session -> session.createQuery("from Image ").list());
    }

    @Override
    public Image getById(int id) {
        return tx(session -> session.get(Image.class, id));
    }

    @Override
    public Image getByName(String name) {
        return null;
    }

    public List<Image> getImagesByCarId(int carId) {
         return tx(session ->
                 session.createQuery("select I from Image I where I.car.id = : carId", Image.class)
                         .setParameter("carId", carId).getResultList());
    }
}
