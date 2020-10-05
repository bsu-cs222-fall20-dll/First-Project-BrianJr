package edu.bsu.cs222.wiki;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.InputStream;

public class Controller
{
    @FXML
    private TextField urlField;

    @FXML
    private ListView<String> top_outputBox;

    @FXML
    private ListView<String> bottom_outputBox;

    ConnectToWiki wiki = new ConnectToWiki();
    UserRevisionSorter sorter = new UserRevisionSorter();

    @FXML
    public void showRecentChanges(ActionEvent actionEvent) throws Exception
    {
        InputStream wikiConnection = wiki.Query(urlField.getText());

        RecentChanges changes = new RecentChanges();
        top_outputBox.setItems(changes.mostRecentEditor(sorter,wikiConnection));
        timeStamp(actionEvent);
    }

    @FXML @SuppressWarnings("unused")
    public void timeStamp(ActionEvent actionEvent) throws Exception
        {
            InputStream wikiConnection = wiki.Query(urlField.getText());

            RecentChanges changes = new RecentChanges();
            bottom_outputBox.setItems(changes.timeStamp(sorter,wikiConnection));
        }

    @FXML @SuppressWarnings("unused")
    public void showMostActiveEditors(ActionEvent actionEvent) throws Exception
    {
        ActiveEditor activeUser = new ActiveEditor();
        InputStream wikiConnection = wiki.Query(urlField.getText());

        top_outputBox.setItems(activeUser.mostActiveEditors(sorter, wikiConnection));
    }

    @FXML
    public void showEditorChanges(ActionEvent actionEvent) throws Exception
    {
        ActiveEditor activeUser = new ActiveEditor();
        InputStream wikiConnection = wiki.Query(urlField.getText());

        bottom_outputBox.setItems(activeUser.editorChanges(sorter, wikiConnection));
        showMostActiveEditors(actionEvent);
    }
}