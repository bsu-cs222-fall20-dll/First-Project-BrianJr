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
    public static void main(String[] args)
    { launch( args ); }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/wiki_UI.fxml"));

        primaryStage.setTitle("ASAP Wiki Search");
        primaryStage.setScene(new Scene(root,612,675));
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {}
}