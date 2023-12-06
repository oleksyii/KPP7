package com.example.kpp7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());



        stage.setTitle("Hello!");
        stage.setScene(scene);

        HelloController controller = (HelloController) fxmlLoader.getController();
//        controller.ChangeNameCell("New name written");

        stage.show();



        //        Group root = new Group();
//        Scene scene = new Scene(root, 500, 500, Color.rgb(245, 245, 245));
//
//        Image icon = new Image("pizza-box.png");
//        primaryStage.getIcons().add(icon);
//        primaryStage.setTitle("Whoa the effects!");
//
//        Text text  = new Text();
//        text.setText("WHOOOOA");
//        text.setX(50.0);
//        text.setY(50.0);
//        text.setFont(Font.font("Verdana", 50));
//        text.setFill(Color.gray(0.5));
//
//        root.getChildren().add(text);

//        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
//        primaryStage.setTitle("Hello world");
////        Must be at the end
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}