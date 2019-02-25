package ru.job4j.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.models.Item;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ItemService {
    private SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public List<Item> getItems() {
        /*Session session = factory.openSession();
        session.beginTransaction();
        List<Item> list = session.createQuery("from Item").list();
        session.getTransaction().commit();
        session.close();
        return list;*/

        return this.tx(session -> session.createQuery("from Item").list());
    }

    public void insertItem(Item item) {
        /*Session session = factory.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();*/

        this.vd(session -> session.save(item));
    }

    public void changeStatus(int id, boolean done) {
       /* Session session = factory.openSession();
        session.beginTransaction();
        Item item = session.get(Item.class, id);
        item.setDone(done);
        session.update(item);
        session.getTransaction().commit();
        session.close();*/

        this.vd(session -> { Item item = session.get(Item.class, id); item.setDone(done); session.update(item); });
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            transaction.commit();
            session.close();
        }
    }

    private void vd(final Consumer<Session> command) {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            command.accept(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            transaction.commit();
            session.close();
        }
    }
}
