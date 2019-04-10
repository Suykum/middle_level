package ru.job4j.car.services;

import ru.job4j.car.entity.*;

import java.util.List;

public interface CarService {
    int addBody(CarBody body);
    void deleteBody(CarBody body);
    List<String> getBodyTypes();

    int addEngine(CarEngine engine);
    void deleteEngine(CarEngine engine);
    List<String> getEngineTypes();

    int addTransmission(CarTransmission transmission);
    void deleteTransmission(CarTransmission transmission);
    List<String> getTransmissionTypes();

    int addImage(Image image);
    List<Image> getImagesByCar(Car car);

    int addCar(Car car);
    void deleteCar(Car car);
    List<Car> getAllCar();
    Car getCarById(int id);
    Car contructCar(Car car, CarBody bodyType, CarEngine engineType, CarTransmission transmissionType);
    List<String> getLocation();
    void statusChange(int id, boolean done);
    List<Car> filterCarsBySold(boolean sold);
    List<Car> filterCarsBySoldAndName(boolean sold, String name);
    List<Car> filterCarsByName(String name);

}
