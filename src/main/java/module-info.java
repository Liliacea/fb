module com.example.familybudgetpostgre {
    requires javafx.controls;
    requires javafx.fxml;
    requires  java.sql;
  //  requires javafx.web;

  //  requires org.controlsfx.controls;
    //requires com.dlsc.formsfx;
    //requires net.synedra.validatorfx;
   // requires org.kordamp.ikonli.javafx;
    //requires org.kordamp.bootstrapfx.core;

    //requires com.almasb.fxgl.all;

    //requires org.hibernate.validator;
    requires java.naming;
    //requires hibernate.entitymanager;
    //  requires hibernate.commons.annotations;

    requires hibernate.core;
    requires hibernate.jpa;
    //  requires jakarta.validation;


    requires javafx.graphics;


    requires java.logging;
    //requires java.persistence;
    //  requires java.persistence;


    opens com.example.budget to javafx.fxml, hibernate.core;
    exports com.example.budget;
    exports com.example.budget.service;
    opens com.example.budget.service to hibernate.core, javafx.fxml;
    exports com.example.budget.model;
    opens com.example.budget.model to hibernate.core, javafx.fxml;
    exports com.example.budget.controller;
    opens com.example.budget.controller to hibernate.core, javafx.fxml;
}


