package edu.bsu.cs222.wiki;

import com.google.gson.*;
import javafx.collections.*;
import java.io.InputStream;

public class ActiveEditor
{
    public ObservableList<String> getMostActiveEditors( UserRevisionSorter revisionParser, InputStream connection ) throws Exception
    {
        JsonArray revisionArray = revisionParser.getUserRevisionArray(connection);
        ObservableList<String> mostActiveEditorList = FXCollections.observableArrayList();

        try
        {
            int editorCount = 0;
            for (int i = 0; i < revisionArray.size(); i++)
            {
                editorCount++;
                JsonObject userElement = revisionArray.get(i).getAsJsonObject();
                JsonElement editor = userElement.get("user");

                String editorElement = String.valueOf(editor);
                mostActiveEditorList.add("Editor " + editorCount + " - " + editorElement);
            }
        }

        catch ( Exception e )
        { mostActiveEditorList.add( "Search Bar Cannot Be Empty!" ); }

        return mostActiveEditorList;
    }

    public ObservableList<String> getEditorNumberOfChanges( UserRevisionSorter revisionParser, InputStream connection ) throws Exception
    {
        JsonArray revisionArray = revisionParser.getUserRevisionArray(connection);
        ObservableList<String> numberOfChangesList = FXCollections.observableArrayList();

        try
        {
            int editorCount = 0;
            for (int i = 0; i < revisionArray.size(); i++)
            {
                int userChanges = 0;
                editorCount++;
                JsonObject userElement = revisionArray.get( i ).getAsJsonObject();
                JsonElement currentUser = userElement.get( "user" );

                for ( int j = i + 1; j < revisionArray.size(); j++ )
                {
                    JsonObject theNextUserElement = revisionArray.get( j ).getAsJsonObject();
                    JsonElement theNextUser = theNextUserElement.get( "user" );

                    if ( currentUser.equals( theNextUser ) )
                    {
                        userChanges = userChanges + 1;
                    }
                }

                if ( userChanges >= 0 )
                {
                    numberOfChangesList.add( ( ( "Number of changes for Editor " + editorCount + " - "
                            + ( userChanges + 1 ) ) ) );
                }
            }
        }

        catch ( NullPointerException e )
        { numberOfChangesList.add( "Search Bar Cannot Be Empty!" ); }

        return numberOfChangesList;
    }
}