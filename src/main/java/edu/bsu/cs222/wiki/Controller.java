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
    private ImageView ASAP_logo;

    @FXML
    public ImageView coffee_time;

    @FXML
    private ImageView top_checkMark;

    @FXML
    private ImageView bottom_checkMark;

    ConnectToWiki wiki = new ConnectToWiki();
    UserRevisionSorter sorter = new UserRevisionSorter();

    File file = new File( "src/main/resources/png_photos/checkMark_green.png" );
    Image image = new Image(file.toURI().toString());

    public void initialize()
    {
        File flame_logo_file = new File( "src/main/resources/png_photos/coffee_time_157x157.png" );
        Image flame_logo = new Image(flame_logo_file.toURI().toString());
        this.coffee_time.setImage( flame_logo );

        File ASAP_logo_file = new File( "src/main/resources/png_photos/ASAP_Wiki_logo_157x87.png" );
        Image ASAP_Wiki_logo = new Image(ASAP_logo_file.toURI().toString());
        this.ASAP_logo.setImage( ASAP_Wiki_logo );
    }

    @FXML
    public void showRecentChanges(ActionEvent actionEvent) throws Exception
    {
        InputStream wikiConnection = wiki.Query(urlField.getText());
        RecentChanges changes = new RecentChanges();

        top_outputBox.setItems(changes.mostRecentEditor(sorter,wikiConnection));
        showUserTimeStamps(actionEvent);

        top_checkMark.setImage( image );
        bottom_checkMark.setImage( image );
    }

    @FXML @SuppressWarnings("unused")
    public void showUserTimeStamps(ActionEvent actionEvent) throws Exception
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

        top_checkMark.setImage( image );
        bottom_checkMark.setImage( image );
    }

    @FXML
    public void showEditorChanges(ActionEvent actionEvent) throws Exception
    {
        ActiveEditor activeUser = new ActiveEditor();
        InputStream wikiConnection = wiki.Query(urlField.getText());

        bottom_outputBox.setItems(activeUser.editorChanges(sorter, wikiConnection));
        showMostActiveEditors(actionEvent);
    }

    @FXML
    public void exitProgram()
    { System.exit( 0 ); }
}