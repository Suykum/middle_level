package ru.job4j.storage;

import org.junit.Test;
import ru.job4j.entity.Car;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CarStoreTest {
    CarStore carStore = CarStore.getInstance();

    @Test
    public void whenUpdateCar() {
        Car car = new Car("Honda");
        car.setLocation("Bishkek");
        carStore.add(car);
        Car car1 = carStore.getByName("Honda");
        car1.setLocation("Istanbul");
        carStore.update(car1);
        assertThat("Istanbul", is(carStore.getByName("Honda").getLocation()));
    }

    @Test
    public void whenGetByName() {
        Car car = new Car("Mazda");
        carStore.add(car);
        assertThat("Mazda", is(carStore.getByName("Mazda").getName()));
    }

    @Test
    public void whenGetById() {
        Car car = new Car("Subaru");
        int id = carStore.add(car);
        assertThat(id, is(carStore.getById(id).getId()));
    }

    @Test
    public void whenConstructCar() {
        Car car = new Car("Audi");
        car = carStore.contructCar(car, "Van", "diesel", "Manual");
        carStore.add(car);
        assertThat(car.getEngine().getEngineType(), is(carStore.getByName("Audi").getEngine().getEngineType()));
    }

    @Test
    public void whenGetLocation() {
        Car car = new Car("Opel");
        car.setLocation("Moscow");
        carStore.add(car);
        assertThat("Moscow", is(carStore.getByName("Opel").getLocation()));
    }

    @Test
    public void whenStatusChange() {
        Car car = new Car("Skoda");
        car.setSold(false);
        carStore.add(car);
        Car car1 = carStore.getByName("Skoda");
        carStore.statusChange(car1.getId(), true);
        assertThat(true, is(carStore.getByName("Skoda").isSold()));
    }

    @Test
    public void whenDeleteCar() {
        Car car = new Car("BMW");
        int id = carStore.add(car);
        assertThat(id, is(carStore.delete(id)));
    }



}