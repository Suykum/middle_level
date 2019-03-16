package ru.job4j.storage;

import ru.job4j.entity.CarTransmission;

import java.util.List;

public class TransmissionStore implements Store<CarTransmission> {
    private static TransmissionStore ourInstance = new TransmissionStore();

    public static TransmissionStore getInstance() {
        return ourInstance;
    }

    private TransmissionStore() {
    }

    @Override
    public int add(CarTransmission entity) {
        return Wrapper.tx(session -> { session.saveOrUpdate(entity); return entity.getTransmissionId(); });
    }

    @Override
    public int update(CarTransmission entity) {
        return Wrapper.tx(session -> { session.update(entity); return entity.getTransmissionId(); });
    }

    @Override
    public int delete(int id) {
        return Wrapper.tx(session ->
        { CarTransmission transmission = session.get(CarTransmission.class, id); session.delete(transmission);
        return transmission.getTransmissionId(); });
    }

    @Override
    public List<CarTransmission> getAll() {
        return Wrapper.tx(session -> session.createQuery("from CarTransmission ").list());
    }

    @Override
    public CarTransmission getById(int id) {
        return Wrapper.tx(session -> session.get(CarTransmission.class, id));
    }

    @Override
    public CarTransmission getByName(String name) {
        CarTransmission transmission;
        try {
            transmission = Wrapper.tx(session ->
                    session.createQuery("select T from CarTransmission T where T.transmissionType = : name", CarTransmission.class)
                            .setParameter("name", name).getSingleResult());
        } catch (Exception e) {
            return null;
        }
        return transmission;
    }

    public List<String> getTransmissionTypes() {
        return Wrapper.tx(session -> session.createQuery("select distinct T.transmissionType from CarTransmission T").list());
    }
}
