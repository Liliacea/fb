package com.example.budget.service;

import org.hibernate.Session;

import java.util.List;
import java.util.function.Function;

public interface CRUDao <T,R>{

    T add(T t);
    List<T> select ();
    T update(T t);
    T delete(T t);
}
