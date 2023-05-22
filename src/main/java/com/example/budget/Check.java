package com.example.budget;

import com.example.budget.model.BalanceObject;
import com.example.budget.model.BalanceType;
import com.example.budget.service.CRUDaoImpl;
import com.example.budget.service.HibernateRunner;


import java.sql.Date;
import java.time.LocalDate;

public class Check {
    public static void main(String[] args) {
       CRUDaoImpl cruDao = new CRUDaoImpl(HibernateRunner.getSessionFactory());
        /*BalanceObject salary = new BalanceObject.Builder()
                .name("salary")
                .date(Date.valueOf(LocalDate.now()))
                .amount(2.22)
                .type(BalanceType.EXPENSE)
                .build();
       cruDao.add(salary);
       cruDao.select();

         */
    }
}
