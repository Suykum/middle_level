package ru.job4j.storage;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HibernateStorage implements Storage {

    @Override
    public void addUser(User user) {
        Wrapper.tx(session -> { session.save(user); return user.getId(); });

    }

    @Override
    public User getUser(int id) {
        return Wrapper.tx(session -> session.get(User.class, id));
    }

    @Override
    public List<User> getAllUser() {
        return Wrapper.tx(session -> session.createQuery("from User").list());
    }
}
