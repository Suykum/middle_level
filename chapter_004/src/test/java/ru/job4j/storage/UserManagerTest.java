package ru.job4j.storage;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserManagerTest {

    private ClassPathXmlApplicationContext context
            = new ClassPathXmlApplicationContext("storage-spring-context.xml");
    UserManager userManager = context.getBean("userManager", UserManager.class);

    @Test
    public void whenSaveNewUserThanGet() {
        User user = new User(1, "Anna", "Petrova");
        userManager.add(user);
        assertThat(1, is(userManager.get(1).getId()));
    }

    @Test
    public void whenGetAllUsers() {
        User user1 = new User(1, "Anna", "Petrova");
        User user2 = new User(2, "Marry", "Jons");
        userManager.add(user1);
        userManager.add(user2);
        List<User> users = userManager.getAll();
        assertThat(2, is(users.size()));
    }
}