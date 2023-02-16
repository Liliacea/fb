package com.example.budget.controller;

import com.example.budget.model.BalanceObject;
import com.example.budget.model.BalanceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class inputController {

    public BalanceObject balanceObject = null;



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



              public void saveButtonAction(ActionEvent actionEvent){
                String name = nameTextField.getText();
                LocalDate date = datePicker.getValue();
                BalanceType type = incomeRadioButton.isSelected()
                        ? BalanceType.INCOME : BalanceType.EXPENSE;
                double amount = Double.parseDouble(amountTextField.getText());
                if (balanceObject == null) {

                    balanceObject = new BalanceObject(0, name, date, amount, type);
                } else {

                    balanceObject.setName(name);
                    balanceObject.setType(type);
                    balanceObject.setDate(date);
                    balanceObject.setAmount(amount);
                }
                ((Stage) inputAncorPane.getScene().getWindow()).close();


            }







    public void clearButtonAction(){
        incomeRadioButton.setSelected(true);
        datePicker.setValue(LocalDate.now());
        amountTextField.setText("0");
        nameTextField.clear();
    }

    public void fillInput () {
        Stage stage = ((Stage) inputAncorPane.getScene().getWindow());
        if (balanceObject == null) {
            stage.setTitle("ввод новых данных");
            clearButtonAction();
            return;
        }
        stage.setTitle("редактирование данных");
        nameTextField.setText(balanceObject.getName());
        datePicker.setValue(balanceObject.getDate());
        amountTextField.setText(String.valueOf(balanceObject.getAmount()));
        switch (balanceObject.getType()) {
            case INCOME -> incomeRadioButton.setSelected(true);
            case EXPENSE -> expenseRadioButton.setSelected(true);

        }
    }

    public BalanceObject getBalanceObject() {
        return balanceObject;
    }

    public void setBalanceObject(BalanceObject balanceObject) {
        this.balanceObject = balanceObject;
        fillInput();
    }





}
