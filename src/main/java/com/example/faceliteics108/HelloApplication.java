package com.example.faceliteics108;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

//abd
public class HelloApplication extends Application  {
    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 780, 492);
        stage.setResizable(false);
        stage.setTitle("FaceLite");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();


        }
    }
