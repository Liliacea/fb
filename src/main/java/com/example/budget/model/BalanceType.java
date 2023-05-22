package com.example.budget.model;

import java.io.Serializable;

/**
 * Тип финансовой операции
 *
 * @author Liliacea
 * @version 1.0
 */
public enum BalanceType implements Serializable {

    INCOME("доход"),
    EXPENSE("расход");

    BalanceType(String name) {
        this.name = name;
    }

    private String name;


    public String getName() {
        return name;
    }
}
