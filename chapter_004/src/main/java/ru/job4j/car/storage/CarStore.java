package ru.job4j.car.storage;

import ru.job4j.car.entity.Car;
import ru.job4j.car.entity.CarBody;
import ru.job4j.car.entity.CarEngine;
import ru.job4j.car.entity.CarTransmission;


import java.util.List;

import static ru.job4j.car.storage.Wrapper.tx;

public class CarStore implements Store<Car> {
    private static final CarStore INSTANCE = new CarStore();

    private final BodyStore bodyStore = BodyStore.getInstance();
    private final EngineStore engineStore = EngineStore.getInstance();
    private final TransmissionStore transmissionStore = TransmissionStore.getInstance();


    public static CarStore getInstance() {
        return INSTANCE;
    }

    private CarStore() {
    }

    @Override
    public int add(Car entity) {
        return tx(session ->  { session.saveOrUpdate(entity); return entity.getId(); });
    }

    @Override
    public int update(Car entity) {
        return tx(session -> { session.update(entity); return entity.getId(); });
    }

    @Override
    public int delete(int id) {
        return tx(session -> { Car car = session.get(Car.class, id); session.delete(car); return car.getId(); });
    }

    @Override
    public List<Car> getAll() {
        return tx(session -> session.createQuery("from Car ").list());
    }

    @Override
    public Car getById(int id) {
        return tx(session -> session.get(Car.class, id));
    }

    @Override
    public Car getByName(String name) {
        Car car;
        try {
            car = tx(session ->
                    session.createQuery("select C from Car C where C.name = : name", Car.class).setParameter("name", name).getSingleResult());
        } catch (Exception e) {
            return null;
        }
        return car;
    }

    public Car contructCar(Car car, CarBody bodyType, CarEngine engineType, CarTransmission transmissionType) {
        CarBody carBody = bodyStore.getByName(bodyType.getBodyType());
        if (carBody == null) {
            carBody = new CarBody(bodyType.getBodyType());
            bodyStore.add(carBody);
        }
        car.setBody(carBody);

        CarEngine carEngine = engineStore.getByName(engineType.getEngineType());
        if (carEngine == null) {
            carEngine = new CarEngine(engineType.getEngineType());
            engineStore.add(carEngine);
        }
        car.setEngine(carEngine);

        CarTransmission carTransmission = transmissionStore.getByName(transmissionType.getTransmissionType());
        if (carTransmission == null) {
            carTransmission = new CarTransmission(transmissionType.getTransmissionType());
            transmissionStore.add(carTransmission);
        }
        car.setTransmission(carTransmission);

        return car;
    }

    public List<String> getLocation() {
        return tx(session -> session.createQuery("select distinct C.location from Car C").list());
    }

    public void statusChange(int id, boolean done) {
        tx(session -> { Car car = session.get(Car.class, id); car.setSold(done); return null; });
    }

    public List<Car> filterCarsBySold(boolean sold) {
        return tx(session -> session.createQuery("select C from Car C where C.sold = : sold", Car.class)
                .setParameter("sold", sold).getResultList());
    }

    public List<Car> filterCarsBySoldAndName(boolean sold, String name) {
        final String nameF = name.toLowerCase();
        return tx(session ->
                session.createQuery("select C from Car C where C.sold = : sold and lower(C.name) = : name", Car.class)
                .setParameter("sold", sold)
                .setParameter("name", nameF)
                .getResultList());
    }

    public List<Car> filterCarsByName(String name) {
        final String nameF = name.toLowerCase();
        return tx(session -> session.createQuery("select C from Car C where lower(C.name) = : name", Car.class)
                .setParameter("name", nameF).getResultList());
    }
}
