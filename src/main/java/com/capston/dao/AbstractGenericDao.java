package com.capston.dao;

import java.io.Serializable;
import java.util.List;
import java.lang.reflect.ParameterizedType;

import com.capston.entities.ParentEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractGenericDao<E> implements GenericDao<E> {

    private final Class<E> entityClass;

    public AbstractGenericDao() {
        this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        Session session = this.sessionFactory.getCurrentSession();
        if (!session.getTransaction().isActive()) {
            sessionFactory.getCurrentSession().beginTransaction();
        }
        return session;
    }

    @Override
    public E findById(final Serializable id) {
        return (E) getSession().get(this.entityClass, id);
    }

    @Override
    public Serializable save(E entity) {
        Serializable res = getSession().save(entity);
        getSession().getTransaction().commit();
        return res;
    }

    @Override
    public void saveOrUpdate(E entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(E entity) {
        getSession().delete(entity);
    }

    @Override
    public void deleteAll() {
        List<E> entities = findAll();
        for (E entity : entities) {
            getSession().delete(entity);
        }
    }

    @Override
    public List<E> findAll() {
        return getSession().createCriteria(this.entityClass).list();
    }


    @Override
    public void clear() {
        getSession().clear();

    }

    @Override
    public void flush() {
        getSession().flush();

    }

}