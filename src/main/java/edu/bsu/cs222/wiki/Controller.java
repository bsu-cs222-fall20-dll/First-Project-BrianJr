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

    @FXML @SuppressWarnings("unused")
    public void showRecentChanges(ActionEvent actionEvent) throws Exception
    {
        InputStream wikiConnection = wiki.Query(urlField.getText());

        RecentChanges changes = new RecentChanges();
        changes.mostRecentEditor(sorter, wikiConnection);
    }


    @FXML@SuppressWarnings("unused")
    public void showActiveEditors(ActionEvent actionEvent) throws Exception
    {

        InputStream wikiConnection = wiki.Query(urlField.getText());

        ActiveEditor activeUser = new ActiveEditor();
        activeUser.mostActiveEditor(sorter, wikiConnection);
    }
}
