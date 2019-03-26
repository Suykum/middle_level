package ru.job4j.storage;

import java.util.List;

public interface Storage {
    void addUser(User user);
    User getUser(int id);
    List<User> getAllUser();
}
