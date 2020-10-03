package edu.bsu.cs222.wiki;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.InputStream;

public class Controller
{
    @FXML
    private TextField urlField;

    ConnectToWiki wiki = new ConnectToWiki();
    UserRevisionSorter sorter = new UserRevisionSorter();
    InputStream wikiConnection = wiki.Query(urlField);
   // InputStream wikiConnection = wiki.Query(urlField.getText());

    public Controller() throws Exception
    {
    }


    @FXML @SuppressWarnings("unused")
    public void showRecentChanges(ActionEvent actionEvent) throws Notify
    {
        RecentChanges changes = new RecentChanges();
        changes.mostRecentEditor(sorter, wikiConnection);
    }


    @FXML@SuppressWarnings("unused")
    public void showActiveEditors(ActionEvent actionEvent) throws Notify
    {
        ActiveEditor activeUser = new ActiveEditor();
        activeUser.mostActiveEditor(sorter, wikiConnection);
    }
}
