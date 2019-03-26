package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserManager {

    private Storage storage;

    @Autowired
    public UserManager(@Qualifier("memoryStorage") Storage storage) {
        this.storage = storage;
    }

    public void add(User user) {
        storage.addUser(user);
    }

    public User get(int id) {
        return storage.getUser(id);
    }

    public List<User> getAll() {
        return storage.getAllUser();
    }

}
