package ru.moysklad.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class GenericDAO<T, ID extends Serializable>{

    @Resource
    private SessionFactory sessionFactory;

    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public GenericDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @SuppressWarnings("unchecked")
    public T findById(ID id) {
        return (T) sessionFactory.getCurrentSession().get(getPersistentClass(), id);
    }

    public void makePersistent(T entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    public void makeTransient(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}