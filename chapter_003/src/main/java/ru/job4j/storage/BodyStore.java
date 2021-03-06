package ru.job4j.storage;

import ru.job4j.entity.CarBody;

import javax.persistence.Query;
import java.util.List;

import static ru.job4j.storage.Wrapper.tx;

public class BodyStore implements Store<CarBody> {

    private static BodyStore instance = new BodyStore();

    public static BodyStore getInstance() {
        return instance;
    }

    private BodyStore() {
    }
    @Override
    public int add(CarBody entity) {
        return tx(session -> { session.saveOrUpdate(entity); return entity.getBodyId(); });
    }

    @Override
    public int update(CarBody entity) {
        return tx(session -> { session.update(entity); return entity.getBodyId(); });
    }

    @Override
    public int delete(int id) {
        return tx(session ->
        { CarBody body = session.get(CarBody.class, id); session.delete(body); return body.getBodyId(); });
    }

    @Override
    public List<CarBody> getAll() {
        return tx(session -> session.createQuery("from CarBody ").list());
    }

    @Override
    public CarBody getById(int id) {
        return tx(session -> session.get(CarBody.class, id));
    }

    @Override
    public CarBody getByName(String bodyName) {
        CarBody body;
        try {
            body = tx(session ->
                    session.createQuery("select B from CarBody B where B.bodyType = : bodyName", CarBody.class)
                            .setParameter("bodyName", bodyName).getSingleResult());
        } catch (Exception e) {
            return null;
        }
        return body;
    }

    public List<String> getBodyTypes() {
        return tx(session -> session.createQuery("select distinct B.bodyType from CarBody B").list());
    }
}
