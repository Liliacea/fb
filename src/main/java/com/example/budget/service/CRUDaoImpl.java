package com.example.budget.service;

import com.example.budget.model.BalanceObject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.function.Function;

public class CRUDaoImpl implements CRUDao<BalanceObject, Integer> {
    SessionFactory sessionFactory;

    public CRUDaoImpl(SessionFactory factory) {
        this.sessionFactory = factory;
    }

    @Override
    public <T> T tx(Function<Session, T> command) {
        final Session session = sessionFactory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public BalanceObject add(BalanceObject balanceObject) {
        return tx(session -> {
            session.save(balanceObject);
            return balanceObject;
        });
    }

    @Override
    public BalanceObject update(BalanceObject balanceObject) {
        return tx(session -> {
            balanceObject.setName(balanceObject.getName());
            balanceObject.setDate(balanceObject.getDate());
            balanceObject.setAmount(balanceObject.getAmount());
            balanceObject.setType(balanceObject.getType());
            session.update(balanceObject);
            return balanceObject;
        });
    }

    @Override
    public List<BalanceObject> select() {
        return tx(session -> {
            List<BalanceObject> balanceObjects = null;
            Criteria criteria = session.createCriteria(BalanceObject.class);
            balanceObjects = criteria.list();
            for (BalanceObject balanceObject : balanceObjects) {
                System.out.println(balanceObject.toString());
            }
            return balanceObjects;
        });
    }

    @Override
    public BalanceObject delete(BalanceObject balanceObject) {
        return tx(session -> {
            //balanceObject.setId(balanceObject.getId());//
            session.delete(balanceObject);
            return balanceObject;
        });
    }
}

