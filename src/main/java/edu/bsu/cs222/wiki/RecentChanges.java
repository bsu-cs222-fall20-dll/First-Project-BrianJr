package edu.bsu.cs222.wiki;

import com.google.gson.*;
import javafx.collections.*;
import java.io.InputStream;

public class RecentChanges
{
    public ObservableList<String> getMostRecentEditors( UserRevisionSorter revisionParser, InputStream connection ) throws Exception
    {
        JsonArray revisionArray = revisionParser.getUserRevisionArray(connection);
        ObservableList<String> editorList = FXCollections.observableArrayList();

        try
        {
            int editorCount = 0;
            for ( int i = 0; i < revisionArray.size(); i++ )
            {
                editorCount++;
                JsonObject userElement = revisionArray.get(i).getAsJsonObject();
                JsonElement editor = userElement.get("user");

                String editorElement = String.valueOf(editor);

                editorList.add("Editor " +editorCount + " - " + editorElement);
            }
        }

        catch ( NullPointerException e )
        { editorList.add( "Search Bar Cannot Be Empty!" ); }

        return editorList;
    }

    public ObservableList<String> getEditorTimeStamps( UserRevisionSorter revisionParser, InputStream connection ) throws Exception
    {
        JsonArray revisionArray = revisionParser.getUserRevisionArray(connection);
        ObservableList<String> timeStampList = FXCollections.observableArrayList();

        try
        {
            int editorCount = 0;
            for (int i = 0; i < revisionArray.size(); i++)
            {
                editorCount++;
                JsonObject userElement = revisionArray.get(i).getAsJsonObject();
                JsonElement timestamp = userElement.get("timestamp");

                String stampElement = String.valueOf(timestamp);

                timeStampList.add("Timestamp for Editor " + editorCount + " - " + stampElement);
            }
        }

        catch ( NullPointerException e )
        { timeStampList.add( "Search Bar Cannot Be Empty!" ); }

        return timeStampList;
    }
}
