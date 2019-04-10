package ru.job4j.car.services;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.car.entity.*;
import ru.job4j.car.repository.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarServiceImp implements CarService {
    @Autowired
    private BodyRepository bodyRepository;

    @Autowired
    private EngineRepository engineRepository;

    @Autowired
    private TransmissionRepository transmissionRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public int addBody(CarBody body) {
        return  bodyRepository.save(body).getBodyId();
    }

    @Override
    public void deleteBody(CarBody body) {
        bodyRepository.delete(body);
    }

    @Override
    public List<String> getBodyTypes() {
       return bodyRepository.getBodyTypes();

    }

    @Override
    public int addEngine(CarEngine engine) {
        return engineRepository.save(engine).getEngineId();
    }

    @Override
    public void deleteEngine(CarEngine engine) {
        engineRepository.delete(engine);
    }

    @Override
    public List<String> getEngineTypes() {
        return engineRepository.getEngineTypes();

    }

    @Override
    public int addTransmission(CarTransmission transmission) {
        return transmissionRepository.save(transmission).getTransmissionId();
    }

    @Override
    public void deleteTransmission(CarTransmission transmission) {
        transmissionRepository.delete(transmission);
    }

    @Override
    public List<String> getTransmissionTypes() {
        return transmissionRepository.getTransmissionTypes();

    }

    @Override
    public int addImage(Image image) {
        return imageRepository.save(image).getId();
    }

    @Override
    public List<Image> getImagesByCar(Car car) {
        return imageRepository.findImagesByCar(car);
    }

    @Override
    public int addCar(Car car) {
        return carRepository.save(car).getId();
    }

    @Override
    public void deleteCar(Car car) {
        carRepository.delete(car);
    }

    @Override
    public List<Car> getAllCar() {
        return Lists.newArrayList(carRepository.findAll());
    }

    @Override
    public Car getCarById(int id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public Car contructCar(Car car, CarBody bodyType, CarEngine engineType, CarTransmission transmissionType) {
        CarBody carBody = bodyRepository.getByBodyType(bodyType.getBodyType());
        if (carBody == null) {
            carBody = new CarBody(bodyType.getBodyType());
            bodyRepository.save(carBody);
        }

        car.setBody(carBody);

        CarEngine carEngine = engineRepository.getByEngineType(engineType.getEngineType());
        if (carEngine == null) {
            carEngine = new CarEngine(engineType.getEngineType());
            engineRepository.save(carEngine);
        }

        car.setEngine(carEngine);

        CarTransmission carTransmission = transmissionRepository.getByTransmissionType(transmissionType.getTransmissionType());
        if (carTransmission == null) {
            carTransmission = new CarTransmission(transmissionType.getTransmissionType());
            transmissionRepository.save(carTransmission);
        }

        car.setTransmission(carTransmission);
        return car;
    }

    @Override
    public List<String> getLocation() {
        return carRepository.getLocations();

    }

    @Override
    public void statusChange(int id, boolean done) {
        Car car = this.getCarById(id);
        car.setSold(done);
        carRepository.save(car);
    }

    @Override
    public List<Car> filterCarsBySold(boolean sold) {
        return carRepository.getCarsBySold(sold);
    }

    @Override
    public List<Car> filterCarsBySoldAndName(boolean sold, String name) {
        return carRepository.getCarsBySoldAndName(sold, name);
    }

    @Override
    public List<Car> filterCarsByName(String name) {
        return carRepository.findCarsByName(name.toLowerCase());
    }
}
