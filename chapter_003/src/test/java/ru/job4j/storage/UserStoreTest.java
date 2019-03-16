package ru.job4j.storage;

import org.junit.Test;
import ru.job4j.entity.User;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {
    UserStore userStore = UserStore.getInstance();

    @Test
    public void whenUpdateUser() {
        User user = new User("Anna", "anna", "anna@gmail.com", "aaa");
        int id = userStore.add(user);
        user = userStore.getById(id);
        user.setPassword("bbb");
        userStore.update(user);
        assertThat("bbb", is(userStore.getById(id).getPassword()));
    }

    @Test
    public void whenGetByLoginName() {
        User user = new User("Masha", "masha", "masha@gmail.com", "mmm");
        userStore.add(user);
        assertThat("masha", is(userStore.getByName("masha").getLogin()));
    }

    @Test
    public void whenGetById() {
        User user = new User("Masha", "mashka", "mashka@gmail.com", "aaa");
        int id = userStore.add(user);
        assertThat("Masha", is(userStore.getById(id).getName()));
    }

    @Test
    public void whenLoginPermitChecked() {
        User user = new User("Bob", "bob", "bob@gmail.com", "bbb");
        userStore.add(user);
        assertThat("Bob", is(userStore.loginPermit("bob", "bbb").getName()));
    }

    @Test
    public void whenDeleteUser() {
        User user = new User("Sue", "sue", "sue@gmail.com", "sss");
        int id = userStore.add(user);
        assertThat(id, is(userStore.delete(id)));
    }

}