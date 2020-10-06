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
    private ImageView ASAP_logo_view;

    @FXML
    public ImageView coffee_time_logo_view;

    @FXML
    private ImageView top_checkMark_view;

    @FXML
    private ListView<String> top_outputBox;

    @FXML
    private ImageView bottom_checkMark_view;

    @FXML
    private ListView<String> bottom_outputBox;

    ConnectToWiki wiki = new ConnectToWiki();
    RecentChanges changes = new RecentChanges();
    ActiveEditor activeUser = new ActiveEditor();
    UserRevisionSorter sorter = new UserRevisionSorter();

    File checkMark_green_file = new File( "src/main/resources/png_photos/checkMark_green.png" );
    Image checkMark_green_icon = new Image( checkMark_green_file.toURI().toString());

    public void initialize()
    {
        File coffee_time_file = new File( "src/main/resources/png_photos/coffee_time_157x157.png" );
        Image coffee_time_logo = new Image(coffee_time_file.toURI().toString());
        this.coffee_time_logo_view.setImage( coffee_time_logo );

        File ASAP_logo_file = new File( "src/main/resources/png_photos/ASAP_Wiki_logo_157x87.png" );
        Image ASAP_Wiki_logo = new Image(ASAP_logo_file.toURI().toString());
        this.ASAP_logo_view.setImage( ASAP_Wiki_logo );
    }

    @FXML
    public void showMostRecentEditors(ActionEvent actionEvent) throws Exception
    {
        InputStream wikiConnection = wiki.Query(urlField.getText());
        top_outputBox.setItems(changes.getMostRecentEditors(sorter,wikiConnection));

        showEditorTimeStamps(actionEvent);
        showGreenCheckMarks();
    }

    @FXML @SuppressWarnings("unused")
    public void showEditorTimeStamps(ActionEvent actionEvent) throws Exception
        {
            InputStream wikiConnection = wiki.Query(urlField.getText());
            bottom_outputBox.setItems(changes.getEditorTimeStamps(sorter,wikiConnection));
        }

    @FXML @SuppressWarnings("unused")
    public void showMostActiveEditors(ActionEvent actionEvent) throws Exception
    {
        InputStream wikiConnection = wiki.Query(urlField.getText());
        top_outputBox.setItems(activeUser.getMostActiveEditors(sorter, wikiConnection));

        showGreenCheckMarks();
    }

    @FXML
    public void showEditorNumberOfChanges(ActionEvent actionEvent) throws Exception
    {
        InputStream wikiConnection = wiki.Query(urlField.getText());
        bottom_outputBox.setItems(activeUser.getEditorNumberOfChanges(sorter, wikiConnection));

        showMostActiveEditors(actionEvent);
    }

    @FXML
    public void showGreenCheckMarks()
    {
        top_checkMark_view.setImage( checkMark_green_icon );
        bottom_checkMark_view.setImage( checkMark_green_icon );
    }

    @FXML
    public void exitProgram()
    { System.exit( 0 ); }
}