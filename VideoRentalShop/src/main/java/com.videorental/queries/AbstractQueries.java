package com.videorental.queries;

import com.videorental.entities.Entity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.util.Assert;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;


public class AbstractQueries<E extends Entity> {

    protected final SessionFactory sessionFactory;

    private final Class<E> entityClass;


    protected AbstractQueries(SessionFactory sessionFactory, Class<E> entityClass) {

        Assert.notNull(sessionFactory, "sessionFactory must not be null");
        Assert.notNull(entityClass, "entityClass must not be null");

        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    public E getById(Long id) {

        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClass);

            Root<E> root = criteriaQuery.from(entityClass);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));

            return (E) session.createQuery(criteriaQuery).uniqueResult();
        }
    }

    public Optional<E> findById(Long id) {

        try (Session session = sessionFactory.openSession()) {

            return Optional.ofNullable(getById(id));
        }
    }

    public List<E> getAll() {

        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClass);

            Root<E> root = criteriaQuery.from(entityClass);
            criteriaQuery.select(root);

            return session.createQuery(criteriaQuery).getResultList();
        }
    }
}

