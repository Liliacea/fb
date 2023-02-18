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

/**
 * Форма отображения хранимых данных и расчета итоговой суммы
 * @author Liliacea
 * @version 1.0
 */
public class MainController {
    private ArrayList <BalanceObject> balanceObjectArrayList;
    private FXMLLoader fxmlLoader;
    private InputController controller;
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

    /**
     * Связь с графическим компонентом
     * java fx
     */
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


    /**
     * Инициализация формы
     * Оботбражение данных при первом запуске программы
     */

    @FXML
    private void initialize()  {

        toDatePicker.setValue(LocalDate.now());
        fromDatePicker.setValue(LocalDate.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");;
        typeComboBox.getItems().addAll("любой","доход","расход");
        typeComboBox.getSelectionModel().select(0);
        DataBase.select();

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

    /**
     * Обработчик кнопки редактировать с предупреждением о неверных действиях пользователя
     * после сохранения данных в форме ввода, объект balanceObject передается на
     * форму хранения данных по индексу
     */
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

            DataBase.update(controller.getBalanceObject());

            fillTableView();


        }

    /**
     * обработчик кнопки удалить с предупреждением о неверных действиях пользователя
     * удаление по индексу из базы данных и списка
     */
    
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


    /**
     * обработчик кнопки Добавить.
     * новый объект balanceObject со значениями полей, записанными в объект в форме ввода
     * (кнопка Сохранить) передается в форму хранения данных и записывается в
     * arrayList
     * */
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

    /**
     * связь с визуальным компонентом
     */
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


    }
    @FXML
    private void toDatePickerAction (){


    }
    @FXML
    private void typeComboBoxAction(){


    }

    /**
     * отрисовка визуального отображения формы хранения данных
     */
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

    /**
     * обработчик кнопки Применить
     * не дает пользователю при использовании фильтров изменять, добавлять и удалять данные
     * WARNING если эту возможность оставить, пользователь может затереть информацию.
     * актуально для хранения данных через файл как это было раньше
     *
     *
     */
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

    /**
     * обработчик кнопки сброса
     * Если фильтр больше не нужен, ползователь снова по нажатию кнопки сброса
     * может добавлять, редактировать и удалять данные
     */
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

    /**
     * Расчет итоговой суммы по установленным фильтрам
     * тип финансовой операции и даты.
     * 
     */
    @FXML
    private void calculate() {

        BigDecimal result = BigDecimal.ZERO;


            balanceObjectArrayList = DataBase.select();





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






