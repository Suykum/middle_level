package ru.job4j.car.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.car.entity.CarEngine;

import java.util.List;

@Repository
public interface EngineRepository extends CrudRepository<CarEngine, Integer> {

    CarEngine getByEngineType(String name);

    @Query("select distinct E.engineType from CarEngine E")
    List<String> getEngineTypes();
}
