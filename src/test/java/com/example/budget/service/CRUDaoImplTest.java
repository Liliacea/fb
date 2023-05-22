package com.example.budget.service;

import com.example.budget.model.BalanceObject;
import com.example.budget.model.BalanceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class CRUDaoImplTest {
    CRUDaoImpl cruDaoImpl = new CRUDaoImpl(HibernateRollBack.create(HibernateRunner.getSessionFactory()));

        BalanceObject salary = new BalanceObject.Builder()
                .name("salary")
                .date(Date.valueOf(LocalDate.now()))
                .amount(2.22)
                .type(BalanceType.EXPENSE)
                .build();




    @Test
    void add() {

        assertThat(cruDaoImpl.add(salary).getName(), is("salary"));
    }

    @Test
    void update() {


        cruDaoImpl.add(salary);
        salary.setName("yahz");

        assertThat(cruDaoImpl.update(salary).getName(),is("yahz"));
    }
    @Test
    void select() {


        cruDaoImpl.add(salary);
        assertThat(cruDaoImpl.select().contains(salary), is(true));
    }


    @Test
    void delete() {
        cruDaoImpl.add(salary);
        cruDaoImpl.delete(salary);
        assertThat(cruDaoImpl.select().contains(salary), is(false));
    }
}