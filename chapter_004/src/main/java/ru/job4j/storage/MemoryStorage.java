package ru.job4j.storage;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MemoryStorage implements Storage {

    private Map<Integer, User> storage = new HashMap<>();
    @Override
    public void addUser(User user) {
        storage.putIfAbsent(user.getId(), user);
    }

    @Override
    public User getUser(int id) {
        return storage.get(id);
    }

    @Override
    public List<User> getAllUser() {
        return new ArrayList<>(storage.values());
    }
}
