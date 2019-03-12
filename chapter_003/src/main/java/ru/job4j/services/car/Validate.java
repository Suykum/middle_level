package ru.job4j.services.car;

import ru.job4j.entity.User;

import java.util.List;


public interface Validate {
    boolean add(User user);
    boolean update(User user);
    boolean delete(int id);
    List findAll();
    User findById(int id);
}
