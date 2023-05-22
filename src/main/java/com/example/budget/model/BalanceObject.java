package com.example.budget.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Класс финансовой операции для таблицы дохода
 *
 * @author Liliacea
 * @version 1.0
 */

@Entity
@Table(name = "balanceObject")


public class BalanceObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private Date date;

    @Column(name = "amount")
    private double amount;


    @Column(name = "type")

    private BalanceType type;

    public BalanceObject() {

    }

    public static class Builder {

        private String name;


        private Date date;
        private double amount;

        private BalanceType type;


        public Builder() {


        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder date(Date val) {
            date = val;
            return this;
        }

        public Builder amount(Double val) {
            amount = val;
            return this;
        }

        public Builder type(BalanceType val) {
            type = val;
            return this;
        }

        public BalanceObject build() {
            return new BalanceObject(this);
        }
    }

    private BalanceObject(Builder builder) {

        name = builder.name;
        date = builder.date;
        amount = builder.amount;
        type = builder.type;


    }

    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
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

    @Override
    public String toString() {
        return "fbpostgre.BalanceObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", type=" + type +
                '}';
    }

}
