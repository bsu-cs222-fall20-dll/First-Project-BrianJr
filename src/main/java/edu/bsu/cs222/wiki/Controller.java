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
    private TextArea editorOutputText;

    @FXML
    private TextArea timeStampOutputText;

    @FXML
    private ListView<String> editorList;

    ConnectToWiki wiki = new ConnectToWiki();
    UserRevisionSorter sorter = new UserRevisionSorter();

    @FXML @SuppressWarnings("unused")
    public void showRecentChanges(ActionEvent actionEvent) throws Exception
    {
        InputStream wikiConnection = wiki.Query(urlField.getText());

        RecentChanges changes = new RecentChanges();

        editorOutputText.setText(String.valueOf(changes.mostRecentEditor(sorter,wikiConnection)));

        ObservableList<String> names = FXCollections.observableArrayList("Greg","jerry","dat boi BOB", "sum1 Save " +
                "me pls", "...", "...", "...", "...", "...", "...", "...", "rip");
        editorList.setItems(names);

        timeStamp(actionEvent);
    }

    @FXML @SuppressWarnings("unused")
    public void timeStamp(ActionEvent actionEvent) throws Exception
        {
            InputStream wikiConnection = wiki.Query(urlField.getText());

            RecentChanges changes = new RecentChanges();
            timeStampOutputText.setText(changes.timeStamp(sorter,wikiConnection));
        }


    @FXML @SuppressWarnings("unused")
    public void showActiveEditors(ActionEvent actionEvent) throws Exception
    {
        ActiveEditor activeUser = new ActiveEditor();
        InputStream wikiConnection = wiki.Query(urlField.getText());

        activeUser.mostActiveEditor(sorter, wikiConnection);
    }
}
