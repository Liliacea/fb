package com.example.budget.controller;

import com.example.budget.Application;
import com.example.budget.service.DataBase;
//import com.example.budget.service.FileService;
import com.example.budget.model.BalanceObject;
import com.example.budget.model.BalanceType;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class MainController {
    private ArrayList <BalanceObject> balanceObjectArrayList;
    private FXMLLoader fxmlLoader;
    private inputController controller;
   // private ObservableList<BalanceObject> balanceObjectObservableList;

   private Stage stage = new Stage();
    public MainController() throws IOException {
    fxmlLoader = new FXMLLoader(Application.class.getResource("inputForm.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    controller = fxmlLoader.getController();
    //stage.setTitle("Ввод данных");
    stage.setScene(scene);


    //stage.show();


    }


    @FXML
    Button applyFilterButton;
    @FXML
    Button resetButton;

    @FXML
    Button addButton;
    @FXML
    Button editButton;
    @FXML
    Button deleteButton;
    @FXML
    ComboBox typeComboBox;
    @FXML
    CheckBox toCheckBox;
    @FXML
    CheckBox fromCheckBox;
    @FXML
    DatePicker toDatePicker;
    @FXML
    DatePicker fromDatePicker;
    @FXML
    TableView <BalanceObject> tableView;
    @FXML
    TableColumn <BalanceObject, String> nameColumn;
    @FXML
    TableColumn <BalanceObject, String> dateColumn;
    @FXML
    TableColumn <BalanceObject, String> amountColumn; //?????
    @FXML
    TableColumn <BalanceObject, String> typeColumn;
    @FXML
    Label resultLabel;







    @FXML
    private void initialize()  {

        toDatePicker.setValue(LocalDate.now());
        fromDatePicker.setValue(LocalDate.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");;
        typeComboBox.getItems().addAll("любой","доход","расход");
        typeComboBox.getSelectionModel().select(0);
        DataBase.select();
        /*try {
            balanceObjectArrayList = FileService.readBalanceObjects(balanceObjectArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

         */
        nameColumn.setCellValueFactory(new PropertyValueFactory<BalanceObject, String>("name"));
        dateColumn.setCellValueFactory(balanceObjectArrayList ->
            new SimpleStringProperty(balanceObjectArrayList.getValue().getDate().format(formatter)));
        typeColumn.setCellValueFactory(balanceObjectArrayList ->
                new SimpleStringProperty(balanceObjectArrayList.getValue().getType().getName()));
        amountColumn.setCellValueFactory(balanceObjectArrayList ->
                new SimpleStringProperty(String.format("%.2f",
        balanceObjectArrayList.getValue().getAmount())));

        calculate();
        fillTableView();


    }

    @FXML
    private void editButtonAction() {
       if(tableView.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Не выбрана строка");
            alert.setContentText("Попробуйте еще раз");
            alert.show();
            return;


        }




        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        controller.setBalanceObject(balanceObjectArrayList.get(selectedIndex));
        stage.showAndWait();
       /* if (controller.getBalanceObject() != null) {
            balanceObjectArrayList.set(selectedIndex, controller.getBalanceObject());

        */
            DataBase.update(controller.getBalanceObject());
           /* try {
                FileService.writeBalanceObjects(balanceObjectArrayList);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            */
            fillTableView();


        }


    
    @FXML
    private void deleteButtonAction(){
       if(tableView.getSelectionModel().isEmpty()){
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Не выбрана строка");
            alert1.setContentText("Попробуйте еще раз");
            alert1.show();
            return;
        }


           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Удаление позиции");
           alert.setContentText("Вы точно этого хотите");
           //alert.showAndWait();
           if (alert.showAndWait().get().equals(ButtonType.OK)) {
               int selectedIndex = tableView.getSelectionModel().getSelectedIndex();


               DataBase.delete(balanceObjectArrayList.get(selectedIndex));
               balanceObjectArrayList.remove(selectedIndex);
       calculate();
           }


        fillTableView();


    }



    @FXML
    private void addButtonAction() {
    controller = fxmlLoader.getController();
    controller.setBalanceObject(null);
        controller.fillInput();
    stage.showAndWait();


        if(controller.getBalanceObject()!=null) {
        balanceObjectArrayList.add(controller.getBalanceObject());
    }


        DataBase.insert(controller.getBalanceObject());
        calculate();
        fillTableView();


    }
    @FXML
    private void fromCheckBoxAction() {
        fromDatePicker.setDisable(!fromCheckBox.isSelected());

    }
    @FXML
    private void toCheckBoxAction(){
        toDatePicker.setDisable(!toCheckBox.isSelected());

    }
    @FXML
    private void fromDatePickerAction(){
       // calculate();

    }
    @FXML
    private void toDatePickerAction (){

        //calculate();
    }
    @FXML
    private void typeComboBoxAction(){

        //calculate();
    }


    @FXML
    private void fillTableView(){

        if (balanceObjectArrayList == null) {
            balanceObjectArrayList = new ArrayList<>();
        }
        ObservableList observableList = FXCollections.observableArrayList(balanceObjectArrayList);
        tableView.setItems(observableList);

        tableView.refresh();
        editButton.setDisable(balanceObjectArrayList.isEmpty());
        deleteButton.setDisable(balanceObjectArrayList.isEmpty());

    }
    @FXML
    private void applyFilterButtonAction(){
        calculate();
        fillTableView();
        addButton.setDisable(true);
        editButton.setDisable(true);
        deleteButton.setDisable(true);
        applyFilterButton.setDisable(true);
        resetButton.setDisable(false);

    }
    @FXML
    private void resetButtonAction(){

        toCheckBox.setSelected(false);
        fromCheckBox.setSelected(false);
        typeComboBox.getSelectionModel().select(0);
        calculate();
        fillTableView();
        addButton.setDisable(false);
        editButton.setDisable(false);
        deleteButton.setDisable(false);
        applyFilterButton.setDisable(false);
       resetButton.setDisable(true);


    }
    @FXML
    private void calculate() {

        BigDecimal result = BigDecimal.ZERO;


            balanceObjectArrayList = DataBase.select();




      //  DataBase.select();

        ArrayList<BalanceObject> filteredArrayList = new ArrayList<>();

        for (BalanceObject balanceObjects : balanceObjectArrayList) {
            boolean isValidType = true;
            switch (typeComboBox.getSelectionModel().getSelectedIndex()) {
                case 1:
                    isValidType = balanceObjects.getType().equals(BalanceType.INCOME);
                    break;
                case 2:
                    isValidType = balanceObjects.getType().equals(BalanceType.EXPENSE);
                    break;
            }
            boolean isValidDateFrom = !fromCheckBox.isSelected() || fromCheckBox.isSelected() &&
                    (fromDatePicker.getValue().compareTo(balanceObjects.getDate())) <= 0;
            boolean isValidDateTo = !toCheckBox.isSelected() || toCheckBox.isSelected() &&
                    (toDatePicker.getValue().compareTo(balanceObjects.getDate())) >= 0;
            if (isValidType && isValidDateFrom && isValidDateTo) {
                filteredArrayList.add(balanceObjects);
                BigDecimal amount = new BigDecimal(balanceObjects.getAmount());
                switch (balanceObjects.getType()) {
                    case INCOME -> result = result.add(amount);
                    case EXPENSE -> result = result.subtract(amount);

                }

            } balanceObjectArrayList = filteredArrayList;

        }


                    resultLabel.setText(String.valueOf(result));
        fillTableView();


    }

    }






