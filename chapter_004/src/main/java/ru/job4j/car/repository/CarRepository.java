package ru.job4j.car.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.car.entity.Car;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {
    @Query("select C from Car C where lower(C.name) = :carName")
    List<Car> findCarsByName(@Param("carName") String carName);

    @Query("select distinct C.location from Car C")
    List<String> getLocations();
    List<Car> getCarsBySold(boolean sold);
    List<Car> getCarsBySoldAndName(boolean sold, String name);
}
