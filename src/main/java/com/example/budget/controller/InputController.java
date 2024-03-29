package com.example.budget.controller;

import com.example.budget.model.BalanceObject;
import com.example.budget.model.BalanceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Таблица ввода данных
 *
 * @author Liliacea
 * @version 1.0
 */
public class InputController {

    public BalanceObject balanceObject = null;


    /**
     * Связь с графическим компонентом Java fx
     */
    @FXML
    private AnchorPane inputAncorPane;
    @FXML
    private Button saveButton;
    @FXML
    private Button clearButton;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField amountTextField;
    @FXML
    private RadioButton incomeRadioButton;
    @FXML
    private RadioButton expenseRadioButton;

    /**
     * обработчик кнопки Сохранить
     * создается новый объект balanceObject со значениями полей, установленными
     * посредством таблицы ввода
     *
     * @param actionEvent
     */

    public void saveButtonAction(ActionEvent actionEvent) {
        String name = nameTextField.getText();
        LocalDate date = datePicker.getValue();
        BalanceType type = incomeRadioButton.isSelected()
                ? BalanceType.INCOME : BalanceType.EXPENSE;
        double amount = Double.parseDouble(amountTextField.getText());
        if (balanceObject == null) {

            balanceObject = new BalanceObject.Builder()
                    .name(name)
                    .date(Date.valueOf(date))
                    .amount(amount)
                    .type(type)
                    .build();
        } else {

            balanceObject.setName(name);
            balanceObject.setType(type);
            balanceObject.setDate(Date.valueOf(date));
            balanceObject.setAmount(amount);
        }
        ((Stage) inputAncorPane.getScene().getWindow()).close();


    }


    /**
     * Обработчик кнопки Очистить
     * устанавливает стартовые значения в форме ввода
     */


    public void clearButtonAction() {
        incomeRadioButton.setSelected(true);
        datePicker.setValue(LocalDate.now());
        amountTextField.setText("0");
        nameTextField.clear();
    }

    /**
     * визуализация формы ввода.
     */
    public void fillInput() {
        Stage stage = ((Stage) inputAncorPane.getScene().getWindow());
        if (balanceObject == null) {
            stage.setTitle("ввод новых данных");
            clearButtonAction();
            return;
        }
        stage.setTitle("редактирование данных");
        nameTextField.setText(balanceObject.getName());
        datePicker.setValue(balanceObject.getDate().toLocalDate());
        amountTextField.setText(String.valueOf(balanceObject.getAmount()));
        switch (balanceObject.getType()) {
            case INCOME -> incomeRadioButton.setSelected(true);
            case EXPENSE -> expenseRadioButton.setSelected(true);

        }
    }

    /**
     * Процедура получения доступа к объекту
     *
     * @return
     */
    public BalanceObject getBalanceObject() {
        return balanceObject;
    }

    public void setBalanceObject(BalanceObject balanceObject) {
        this.balanceObject = balanceObject;
        fillInput();
    }


}
