package ru.job4j.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.function.Function;

public class Wrapper {

    private static SessionFactory factory =  new Configuration()
            .configure()
            .buildSessionFactory();
    public static  <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final org.hibernate.Transaction transaction = session.beginTransaction();
        T result;
        try {
            result = command.apply(session);
            transaction.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }
}
