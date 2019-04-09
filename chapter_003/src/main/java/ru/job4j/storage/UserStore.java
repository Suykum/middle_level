package ru.job4j.storage;

import ru.job4j.entity.User;

import java.util.List;

import static ru.job4j.storage.Wrapper.tx;

public class UserStore implements Store<User> {
    private static UserStore ourInstance = new UserStore();

    public static UserStore getInstance() {
        return ourInstance;
    }

    private UserStore() {
    }

    @Override
    public int add(User entity) {
        return tx(session -> { session.save(entity); return entity.getId(); });
    }

    @Override
    public int update(User entity) {
        return tx(session -> { session.update(entity); return entity.getId(); });
    }

    @Override
    public int delete(int id) {
        return tx(session -> { User user = session.get(User.class, id); session.delete(user); return user.getId(); });
    }

    @Override
    public List<User> getAll() {
        return tx(session -> session.createQuery("from User ").list());
    }

    @Override
    public User getById(int id) {
        return tx(session -> session.get(User.class, id));
    }

    @Override
    public User getByName(String loginName) {
        User user;
        try {
            user = tx(session ->
                    session.createQuery("select U from User U where U.login = : name", User.class)
                            .setParameter("name", loginName).getSingleResult());
        } catch (Exception e) {
            return null;
        }
        return user;
    }

    public User loginPermit(String login, String password) {
        User user;
        try {
            user = tx(session ->
                    session.createQuery("select U from User U where U.login = ?1 and U.password = ?2", User.class)
                            .setParameter(1, login).setParameter(2, password).getSingleResult());
        } catch (Exception e) {
            return null;
        }
        return user;
    }

}
