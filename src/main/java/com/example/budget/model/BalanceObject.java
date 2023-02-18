package com.example.budget.model;

import com.example.budget.model.BalanceType;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Класс финансовой операции для таблицы дохода
 * @author Liliacea
 * @version 1.0
 */
public class BalanceObject implements Serializable {
    /**
     * поле id (для базы данных)
     */
    private int id;
    /**
     * Поле наименования финансовой операции
     */
    private String name;
    /**
     * поле даты
     */
    private LocalDate date;
    /**
     * поле суммы
     */
    private double amount;
    /**
     * Тип финансовой операции
     * Enum BalanceType
     */
    private BalanceType type;

    /**
     * Конструктор - создание нового объекта класса с определенными значениями
     * @param id id
     * @param name Наименование финансовой операции
     * @param date Дата
     * @param amount Сумма
     * @param type Тип (Enum BalanceType)
     */
    public BalanceObject(int id, String name, LocalDate date, double amount, BalanceType type) {
       this.id = id;
        this.name = name;
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    /**
     * процедура получения значения поля
     * @return возвращает значение типа int, являющееся автоинкрементируемым id
     */
    public int getId() {
        return id;
    }

    /**
     * процедура получения значения поля
     * @return возвращает наименование финансовой операции
     */

    public String getName() {
        return name;
    }

    /**
     * процедура установки значения поля
     * @param name название финансовой операции
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * процедура получения доступа к значению поля
     * @return возвращает дату события
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * процедура установки даты
     * @param date Дата
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * процедура получения доступа к значению поля
     * @return возвращает сумму
     */
    public double getAmount() {
        return amount;
    }

    /**
     * процедура установки значения суммы
     * @param amount сумма
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * процедура получения доступа к значению поля
     * @return возвращает тип (enum BalanceType)
     */
    public BalanceType getType() {
        return type;
    }

    /**
     * процедура установки значения типа финансовой операции
     * @param type Тип финансовой операции
     */
    public void setType(BalanceType type) {
        this.type = type;
    }
}
