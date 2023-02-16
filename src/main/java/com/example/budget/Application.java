package com.example.budget;

import com.example.budget.service.DataBase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("mainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Семейный бюджет");
        stage.setScene(scene);
        stage.setMinWidth(600);
        stage.show();
    }



    public static void main(String[] args) {
        launch();


    }
}