package ru.job4j.storage;

import ru.job4j.entity.CarEngine;

import java.util.List;

public class EngineStore implements Store<CarEngine> {
    private static EngineStore ourInstance = new EngineStore();

    public static EngineStore getInstance() {
        return ourInstance;
    }

    private EngineStore() {
    }

    @Override
    public int add(CarEngine entity) {
        return Wrapper.tx(session -> { session.saveOrUpdate(entity); return entity.getEngineId(); });
    }

    @Override
    public int update(CarEngine entity) {
        return Wrapper.tx(session -> { session.update(entity); return entity.getEngineId(); });
    }

    @Override
    public int delete(int id) {
        return Wrapper.tx(session ->
        { CarEngine engine = session.get(CarEngine.class, id); session.delete(engine); return engine.getEngineId(); });
    }

    @Override
    public List<CarEngine> getAll() {
        return Wrapper.tx(session -> session.createQuery("from CarEngine ").list());
    }

    @Override
    public CarEngine getById(int id) {
        return Wrapper.tx(session -> session.get(CarEngine.class, id));
    }

    @Override
    public CarEngine getByName(String name) {
        CarEngine engine;
        try {
            engine = Wrapper.tx(session ->
                    session.createQuery("select E from CarEngine E where E.engineType = : name", CarEngine.class)
                            .setParameter("name", name).getSingleResult());
        } catch (Exception e) {
            return null;
        }
        return engine;
    }

    public List<String> getEngineTypes() {
        return Wrapper.tx(session -> session.createQuery("select distinct E.engineType from CarEngine E").list());
    }



}
