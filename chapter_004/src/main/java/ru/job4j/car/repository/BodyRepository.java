package ru.job4j.car.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.car.entity.CarBody;

import java.util.List;

@Repository
public interface BodyRepository extends CrudRepository<CarBody, Integer> {

    CarBody getByBodyType(String name);

    @Query("select distinct B.bodyType from CarBody B")
    List<String> getBodyTypes();

}
