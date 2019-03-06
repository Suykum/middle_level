package ru.job4j.services.car;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
@Ignore
public class CarStoreTest {
    CarStoreUnused store = CarStoreUnused.getInstance();

    @Test
    public void whenAddDeleteBody() {
        int id = store.addBody("body1");
        int expect = store.deleteBody(id);
        assertEquals(id, expect);
    }

    @Test
    public void whenAddDeleteEngine() {
        int id = store.addEngine("engine1");
        int expect = store.deleteEngine(id);
        assertEquals(id, expect);
    }

    @Test
    public void whenAddDeleteTransmission() {
        int id = store.addTransmission("transmission1");
        int expect = store.deleteTransmission(id);
        assertEquals(id, expect);
    }

    @Test
    public void whenAddDeleteCar() {
        int carId = store.addEmptyCar("Car1");
        int expect = store.deleteCar(carId);
        assertEquals(carId, expect);

    }

    @Test
    public void whenEditCarBody() {
        int bodyId = store.addBody("body1");
        int carId = store.addEmptyCar("Car1");
        int expectedBodyId = store.editCarBody(carId, bodyId);
        assertEquals(bodyId, expectedBodyId);
        store.deleteCar(carId);
        store.deleteBody(bodyId);
    }
}