package edu.bsu.cs222.wiki;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.InputStream;

public class Controller
{
    @FXML
    private TextField urlField;

    @FXML
    private ListView<String> top_outputBox;

    @FXML
    private ListView<String> bottom_outputBox;

    @FXML
    private ImageView logo;

    @FXML
    private ImageView top_checkMark;

    @FXML
    private ImageView bottom_checkMark;

    ConnectToWiki wiki = new ConnectToWiki();
    UserRevisionSorter sorter = new UserRevisionSorter();

    File file = new File( "src/main/resources/checkMark_green.png" );
    Image image = new Image(file.toURI().toString());

    public void initialize()
    {
        File file = new File( "src/main/resources/flame_logo.png" );
        Image image = new Image(file.toURI().toString());
        logo.setImage( image );
    }

    @FXML
    public void showRecentChanges(ActionEvent actionEvent) throws Exception
    {
        InputStream wikiConnection = wiki.Query(urlField.getText());

        //Displays data
        RecentChanges changes = new RecentChanges();
        top_outputBox.setItems(changes.mostRecentEditor(sorter,wikiConnection));
        timeStamp(actionEvent);

        //Displays green checkmarks
        top_checkMark.setImage( image );
        bottom_checkMark.setImage( image );
    }

    @FXML @SuppressWarnings("unused")
    public void timeStamp(ActionEvent actionEvent) throws Exception
        {
            InputStream wikiConnection = wiki.Query(urlField.getText());

            //Displays data
            RecentChanges changes = new RecentChanges();
            bottom_outputBox.setItems(changes.timeStamp(sorter,wikiConnection));
        }

    @FXML @SuppressWarnings("unused")
    public void showMostActiveEditors(ActionEvent actionEvent) throws Exception
    {
        ActiveEditor activeUser = new ActiveEditor();
        InputStream wikiConnection = wiki.Query(urlField.getText());

        //Displays data
        top_outputBox.setItems(activeUser.mostActiveEditors(sorter, wikiConnection));

        //Displays green checkmarks
        top_checkMark.setImage( image );
        bottom_checkMark.setImage( image );
    }

    @FXML
    public void showEditorChanges(ActionEvent actionEvent) throws Exception
    {
        ActiveEditor activeUser = new ActiveEditor();
        InputStream wikiConnection = wiki.Query(urlField.getText());

        //Displays data
        bottom_outputBox.setItems(activeUser.editorChanges(sorter, wikiConnection));
        showMostActiveEditors(actionEvent);
    }

    @FXML
    public void exitProgram()
    { System.exit( 0 ); }
}