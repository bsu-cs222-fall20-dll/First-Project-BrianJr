package edu.bsu.cs222.wiki;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent>
{
    public static void main(String[] brian)
    { launch(brian); }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/wiki_UI.fxml"));

        primaryStage.setTitle("Wiki Search Simulator");
        primaryStage.setScene(new Scene(root,650,650));
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {}
}