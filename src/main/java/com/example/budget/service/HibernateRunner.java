package com.example.budget.service;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.persister.entity.SingleTableEntityPersister;

public class HibernateRunner {
    private static SingleTableEntityPersister persister;
    private static SessionFactory sessionFactory;
    static {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }
    public static SessionFactory getSessionFactory () {
        return sessionFactory;
    }



}
