package edu.bsu.cs222.wiki;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.InputStream;

public class Controller
{
    @FXML
    private TextField urlField;

    @FXML
    private ListView<String> editorOutputText;

    @FXML
    private ListView<String> timeStampOutputText;


    ConnectToWiki wiki = new ConnectToWiki();
    UserRevisionSorter sorter = new UserRevisionSorter();

    @FXML @SuppressWarnings("unused")
    public void showRecentChanges(ActionEvent actionEvent) throws Exception
    {
        InputStream wikiConnection = wiki.Query(urlField.getText());

        RecentChanges changes = new RecentChanges();
        editorOutputText.setItems(changes.mostRecentEditor(sorter,wikiConnection));
        timeStamp(actionEvent);
    }

    @FXML @SuppressWarnings("unused")
    public void timeStamp(ActionEvent actionEvent) throws Exception
        {
            InputStream wikiConnection = wiki.Query(urlField.getText());

            RecentChanges changes = new RecentChanges();
            timeStampOutputText.setItems(changes.timeStamp(sorter,wikiConnection));
        }


    @FXML @SuppressWarnings("unused")
    public void showActiveEditors(ActionEvent actionEvent) throws Exception
    {
        ActiveEditor activeUser = new ActiveEditor();
        InputStream wikiConnection = wiki.Query(urlField.getText());

        activeUser.mostActiveEditor(sorter, wikiConnection);
    }
}
