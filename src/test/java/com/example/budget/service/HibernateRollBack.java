package com.example.budget.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.lang.reflect.Proxy;


import static org.mockito.Mockito.mock;
public class HibernateRollBack {

    /**
     * Creates a connection that rolls the transaction back when closed.
     *
     * @return a connection that rolls the transaction back when closed.
     */
    public static SessionFactory create(SessionFactory factory) {
        Session session = factory.openSession();
        session.beginTransaction();
        return (SessionFactory) Proxy.newProxyInstance(
                HibernateRollBack.class.getClassLoader(),
                new Class[]{
                        SessionFactory.class},
                (proxy, method, args) -> {
                    Object rsl = null;
                    if (method.getName().equals("openSession")) {
                        rsl = create(session);
                    } else if (method.getName().equals("close")) {
                        session.getTransaction().rollback();
                        session.close();
                    } else {
                        rsl = method.invoke(factory, args);
                    }
                    return rsl;
                }
        );
    }



    public static Session create(Session session) {
        return (Session) Proxy.newProxyInstance(
                HibernateRollBack.class.getClassLoader(),
                new Class[]{
                        Session.class},
                (proxy, method, args) -> {
                    Object rsl;
                    if(method.getName().equals("beginTransaction")){
                        rsl = mock(Transaction.class);
                    }else if(method.getName().equals("close")){
                        rsl = null;
                    } else {
                        rsl = method.invoke(session,args);
                    }
                    return rsl;
                });
}
}
