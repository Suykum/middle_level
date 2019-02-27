package ru.job4j.services.car.xml;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.models.Car;
import ru.job4j.models.CarBody;
import ru.job4j.models.CarEngine;
import ru.job4j.models.CarTransmission;

import java.util.List;
import java.util.function.Function;

public class CarStore {
    private static CarStore instance = new CarStore();

    public static CarStore getInstance() {
        return instance;
    }

    private CarStore() {
    }
    private SessionFactory factory = new Configuration().configure("hibernate.cfg.carXML.xml").buildSessionFactory();
    private <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            transaction.commit();
            session.close();
        }
    }

    public List<Car> getAllCars() {
        return tx(session -> session.createQuery("from Car").list());
    }

    public int addCar(String name, int bodyId, int engineId, int transmissionId) {
        Car car = new Car(name);
        int result =
                tx(session -> { CarBody body = session.get(CarBody.class, bodyId);
                    CarEngine engine = session.get(CarEngine.class, engineId);
                    CarTransmission transmission = session.get(CarTransmission.class, transmissionId);
                    car.setBody(body); car.setEngine(engine); car.setTransmission(transmission);
                    session.save(car);
                    return car.getId(); });
        return result;
    }

    public int addEmptyCar(String name) {
        Car car = new Car(name);
        int result =
                tx(session -> { session.save(car); return car.getId(); });
        return result;
    }

    public int editCarBody(int carId, int bodyId) {
       int result =
                tx(session -> { Car car = session.get(Car.class, carId);
                    CarBody body = session.get(CarBody.class, bodyId);
                    car.setBody(body);
                    session.update(car);
                    return car.getBody().getBodyId(); });
        return result;
    }

    public int editCarEngine(int carId, int engineId) {
        int result =
                tx(session -> { Car car = session.get(Car.class, carId);
                    CarEngine engine = session.get(CarEngine.class, engineId);
                    car.setEngine(engine);
                    session.update(car);
                    return car.getEngine().getEngineId(); });
        return result;
    }

    public int editCarTransmission(int carId, int transmissionId) {
        int result =
                tx(session -> { Car car = session.get(Car.class, carId);
                    CarTransmission transmission = session.get(CarTransmission.class, transmissionId);
                    car.setTransmission(transmission);
                    session.update(car);
                    return car.getTransmission().getTransmissionId(); });
        return result;
    }

    public int editCarName(int carId, String name) {
        int result =
                tx(session -> { Car car = session.get(Car.class, carId);
                    car.setName(name);
                    session.update(car);
                    return car.getId(); });
        return result;
    }

    public int deleteCar(int carId) {
       int result =
                tx(session -> { Car car = session.get(Car.class, carId);
                    session.delete(car);
                    return car.getId(); });
        return result;
    }

    public List<CarBody> getAllBody() {
        return tx(session -> session.createQuery("from CarBody").list());
    }

    public int addBody(String name) {
        CarBody body = new CarBody(name);
        return tx(session -> { session.save(body); return body.getBodyId(); });
    }

    public int deleteBody(int id) {

        return tx(session -> { CarBody body = session.get(CarBody.class, id);
                                session.delete(body); return body.getBodyId(); });
    }

    public List<CarEngine> getAllEngine() {
        return tx(session -> session.createQuery("from CarEngine").list());
    }

    public int addEngine(String name) {
        CarEngine engine = new CarEngine(name);
        return tx(session -> { session.save(engine); return engine.getEngineId(); });
    }

    public int deleteEngine(int id) {
        return tx(session -> { CarEngine engine = session.get(CarEngine.class, id);
                                session.delete(engine); return engine.getEngineId(); });
    }
    public List<CarTransmission> getAllTransmission() {
        return tx(session -> session.createQuery("from CarTransmission").list());
    }

    public int addTransmission(String name) {
        CarTransmission transmission = new CarTransmission(name);
        return tx(session -> { session.save(transmission); return transmission.getTransmissionId(); });
    }

    public int deleteTransmission(int id) {
        return tx(session -> { CarTransmission transmission = session.get(CarTransmission.class, id);
                                session.delete(transmission); return transmission.getTransmissionId(); });
    }

}
