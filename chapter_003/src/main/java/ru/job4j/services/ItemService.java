package ru.job4j.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.models.Item;

import java.util.List;

public class ItemService {
    private SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public List<Item> getItems() {
        Session session = factory.openSession();
        session.beginTransaction();
        List<Item> list = session.createQuery("from Item").list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public void insertItem(Item item) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    public void changeStatus(int id, boolean done) {
        Session session = factory.openSession();
        session.beginTransaction();
        Item item = session.get(Item.class, id);
        item.setDone(done);
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }
}
