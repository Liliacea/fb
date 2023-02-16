package com.example.budget.model;

import com.example.budget.model.BalanceType;

import java.io.Serializable;
import java.time.LocalDate;

public class BalanceObject implements Serializable {
    private int id;
    private String name;
    private LocalDate date;
    private double amount;
    private BalanceType type;

    public BalanceObject(int id, String name, LocalDate date, double amount, BalanceType type) {
       this.id = id;
        this.name = name;
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BalanceType getType() {
        return type;
    }

    public void setType(BalanceType type) {
        this.type = type;
    }
}
