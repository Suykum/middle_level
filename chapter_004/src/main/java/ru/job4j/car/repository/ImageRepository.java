package ru.job4j.car.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.car.entity.Car;
import ru.job4j.car.entity.Image;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {

    List<Image> findImagesByCar(Car car);
}
