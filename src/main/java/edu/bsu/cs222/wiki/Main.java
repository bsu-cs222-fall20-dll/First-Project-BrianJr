package edu.bsu.cs222.wiki;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.*;

import java.awt.*;

public class Main extends Application
{
    Button search;

    public static void main(String[] args) throws Exception
    {
        //UI ui = new UI();
        //ui.startApplication();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Wiki Search Simulator");

        search = new Button();
        search.setText("Search!");

        StackPane layout = new StackPane();
        layout.getChildren().add(search);

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);

        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}