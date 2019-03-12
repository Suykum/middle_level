package ru.job4j.storage;

import ru.job4j.entity.Car;

import java.util.List;

public class CarStore implements Store<Car> {
    private static CarStore ourInstance = new CarStore();

    public static CarStore getInstance() {
        return ourInstance;
    }

    private CarStore() {
    }

    @Override
    public int add(Car entity) {
        return Wrapper.tx(session ->  { session.saveOrUpdate(entity); return entity.getId(); });
    }

    @Override
    public int update(Car entity) {
        return Wrapper.tx(session -> { session.update(entity); return entity.getId(); });
    }

    @Override
    public int delete(int id) {
        return Wrapper.tx(session -> { Car car = session.get(Car.class, id); session.delete(car); return car.getId(); });
    }

    @Override
    public List<Car> getAll() {
        return Wrapper.tx(session -> session.createQuery("from Car ").list());
    }

    @Override
    public Car getById(int id) {
        return Wrapper.tx(session -> session.get(Car.class, id));
    }

    @Override
    public Car getByName(String name) {
        Car car;
        try {
            car = Wrapper.tx(session ->
                    session.createQuery("select C from Car C where C.name = : name", Car.class).setParameter("name", name).getSingleResult());
        } catch (Exception e) {
            return null;
        }
        return car;
    }

    public List<String> getLocation() {
        return Wrapper.tx(session -> session.createQuery("select distinct C.location from Car C").list());
    }

    public void statusChange(int id, boolean done) {
        Wrapper.tx(session -> { Car car = session.get(Car.class, id); car.setSold(done); return null; });
    }
}
