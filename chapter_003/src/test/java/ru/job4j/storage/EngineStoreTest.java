package ru.job4j.storage;

import org.junit.Test;
import ru.job4j.entity.CarEngine;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class EngineStoreTest {
    EngineStore engineStore = EngineStore.getInstance();

    @Test
    public void whenAddEngine() {
        CarEngine engine = new CarEngine("diesel");
        int id = engineStore.add(engine);
        assertThat(engine, is(engineStore.getById(id)));
    }

    @Test
    public void whenDeleteEngine() {
        CarEngine engine = new CarEngine("electric");
        int id = engineStore.add(engine);
        assertThat(id, is(engineStore.delete(id)));
    }

    @Test
    public void whenGetById() {
        CarEngine engine = new CarEngine("gas");
        int id = engineStore.add(engine);
        assertThat("gas", is(engineStore.getById(id).getEngineType()));
    }

    @Test
    public void whenGetAllEngineTypes() {
        CarEngine engine1 = new CarEngine("diesel");
        CarEngine engine2 = new CarEngine("gas");
        CarEngine engine3 = new CarEngine("electric");

        engineStore.add(engine1);
        engineStore.add(engine2);
        engineStore.add(engine3);
        assertThat(3, is(engineStore.getEngineTypes().size()));
    }

}