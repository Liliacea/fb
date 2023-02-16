module com.example.budget {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.budget to javafx.fxml;
    exports com.example.budget;
    exports com.example.budget.controller;
    opens com.example.budget.controller to javafx.fxml;
    exports com.example.budget.model;
    opens com.example.budget.model to javafx.fxml;
    exports com.example.budget.service;
    opens com.example.budget.service to javafx.fxml;
}