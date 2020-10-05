package edu.bsu.cs222.wiki;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.InputStream;

public class RecentChanges
{
    public ObservableList<String> mostRecentEditor(UserRevisionSorter revisionParser, InputStream connection) throws Exception
    {
        JsonArray array = revisionParser.getUserRevisionArray(connection);
        ObservableList<String> editorList = FXCollections.observableArrayList();
        try
        {
            int count = 0;

            for (int i = 0; i < array.size(); i++)
            {
                count++;
                JsonObject userElement = array.get(i).getAsJsonObject();
                JsonElement editor = userElement.get("user");

                String editorElement = String.valueOf(editor);

                editorList.add("Editor " +count + " - " + editorElement);
            }
        }

        catch ( NullPointerException e )
        {
//            System.out.println("Invalid Search Value or Page Not Found");
//            System.exit( 0 );
            editorList.add( "Invalid Search Value or Page Not Found" );
        }
        return editorList;
    }

    public ObservableList<String> timeStamp(UserRevisionSorter revisionParser, InputStream connection) throws Exception
    {
        JsonArray array = revisionParser.getUserRevisionArray(connection);
        ObservableList<String> timeStampList = FXCollections.observableArrayList();
        int count = 0;

        for (int i = 0; i < array.size(); i++)
        {
            count++;
            JsonObject userElement = array.get(i).getAsJsonObject();
            JsonElement timestamp = userElement.get("timestamp");

            String stampElement = String.valueOf(timestamp);

            timeStampList.add("Timestamp for Editor " +count + " - " + stampElement);
        }
        return timeStampList;
    }
}
