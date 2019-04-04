package ru.job4j.car.storage;

import ru.job4j.car.entity.CarTransmission;


import java.util.List;

import static ru.job4j.car.storage.Wrapper.tx;

public class TransmissionStore implements Store<CarTransmission> {
    private static TransmissionStore ourInstance = new TransmissionStore();

    public static TransmissionStore getInstance() {
        return ourInstance;
    }

    private TransmissionStore() {
    }

    @Override
    public int add(CarTransmission entity) {
        return tx(session -> { session.saveOrUpdate(entity); return entity.getTransmissionId(); });
    }

    @Override
    public int update(CarTransmission entity) {
        return tx(session -> { session.update(entity); return entity.getTransmissionId(); });
    }

    @Override
    public int delete(int id) {
        return tx(session ->
        { CarTransmission transmission = session.get(CarTransmission.class, id); session.delete(transmission);
        return transmission.getTransmissionId(); });
    }

    @Override
    public List<CarTransmission> getAll() {
        return tx(session -> session.createQuery("from CarTransmission ").list());
    }

    @Override
    public CarTransmission getById(int id) {
        return tx(session -> session.get(CarTransmission.class, id));
    }

    @Override
    public CarTransmission getByName(String name) {
        CarTransmission transmission;
        try {
            transmission = tx(session ->
                    session.createQuery("select T from CarTransmission T where T.transmissionType = : name", CarTransmission.class)
                            .setParameter("name", name).getSingleResult());
        } catch (Exception e) {
            return null;
        }
        return transmission;
    }

    public List<String> getTransmissionTypes() {
        return tx(session -> session.createQuery("select distinct T.transmissionType from CarTransmission T").list());
    }
}
