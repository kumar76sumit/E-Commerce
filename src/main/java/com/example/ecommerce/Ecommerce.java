package com.example.ecommerce;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;

public class Ecommerce extends Application {
    UserInterface userInterface=new UserInterface();
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(userInterface.createContent());
        stage.setTitle("E-Commerce");
        stage.setResizable(false);
//        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}