package ru.job4j.car.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.car.entity.CarTransmission;

import java.util.List;

@Repository
public interface TransmissionRepository extends CrudRepository<CarTransmission, Integer> {

    CarTransmission getByTransmissionType(String name);

    @Query("select distinct T.transmissionType from CarTransmission T")
    List<String> getTransmissionTypes();
}
