package com.videorental.repositories;

import com.videorental.entities.Entity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.util.Assert;

public class AbstractRepository<E extends Entity> {

    protected final SessionFactory sessionFactory;

    protected final Class<E> entityClass;

    public AbstractRepository(SessionFactory sessionFactory, Class<E> entityClass) {

        Assert.notNull(sessionFactory, "sessionFactory must not be null");
        Assert.notNull(entityClass, "entityClass must not be null");

        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    public void saveOrUpdate(E entity) {

        Assert.notNull(entity, "entity must not be null");

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            session.saveOrUpdate(entity);

            transaction.commit();
        }
    }

    public void deleteById(Long id) {

        Assert.notNull(id, "id must not be null");

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            E entity = session.get(entityClass, id);

            if (entity != null) {

                session.delete(entity);

                transaction.commit();
            }
        }
    }
}
