package ru.job4j.car.storage;

import ru.job4j.car.entity.CarEngine;


import java.util.List;

import static ru.job4j.car.storage.Wrapper.tx;

public class EngineStore implements Store<CarEngine> {
    private static EngineStore ourInstance = new EngineStore();

    public static EngineStore getInstance() {
        return ourInstance;
    }

    private EngineStore() {
    }

    @Override
    public int add(CarEngine entity) {
        return tx(session -> { session.saveOrUpdate(entity); return entity.getEngineId(); });
    }

    @Override
    public int update(CarEngine entity) {
        return tx(session -> { session.update(entity); return entity.getEngineId(); });
    }

    @Override
    public int delete(int id) {
        return tx(session ->
        { CarEngine engine = session.get(CarEngine.class, id); session.delete(engine); return engine.getEngineId(); });
    }

    @Override
    public List<CarEngine> getAll() {
        return tx(session -> session.createQuery("from CarEngine ").list());
    }

    @Override
    public CarEngine getById(int id) {
        return tx(session -> session.get(CarEngine.class, id));
    }

    @Override
    public CarEngine getByName(String name) {
        CarEngine engine;
        try {
            engine = tx(session ->
                    session.createQuery("select E from CarEngine E where E.engineType = : name", CarEngine.class)
                            .setParameter("name", name).getSingleResult());
        } catch (Exception e) {
            return null;
        }
        return engine;
    }

    public List<String> getEngineTypes() {
        return tx(session -> session.createQuery("select distinct E.engineType from CarEngine E").list());
    }



}
