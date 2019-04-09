package ru.job4j.car.storage;

import java.util.List;

public interface Store<T> {
    int add(T entity);
    int update(T entity);
    int delete(int id);
    List<T> getAll();
    T getById(int id);
    T getByName(String name);
}
