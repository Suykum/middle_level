package ru.job4j.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.models.Item;

import java.util.List;

public class ItemService {

    public List<Item> getItems() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        List<Item> list = session.createQuery("from Item").list();
        session.getTransaction().commit();
        session.close();
        factory.close();
        return list;
    }

    public void insertItem(Item item) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        factory.close();
    }
}
