package com.example.kpp7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainViewApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());



        stage.setTitle("Hello!");
        stage.setScene(scene);

        MainViewController controller = (MainViewController) fxmlLoader.getController();

        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}