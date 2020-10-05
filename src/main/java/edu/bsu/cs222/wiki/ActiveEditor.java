package edu.bsu.cs222.wiki;

import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.InputStream;

public class ActiveEditor
{
    public ObservableList<String> editorChanges(UserRevisionSorter revisionParser, InputStream connection) throws Notify
    {
        JsonArray array = revisionParser.getUserRevisionArray(connection);
        ObservableList<String> numberOfChanges = FXCollections.observableArrayList();
        int userCount = 0;

        for (int i = 0; i < array.size(); i++)
        {
            int userChanges = 0;
            userCount++;
            JsonObject userElement = array.get(i).getAsJsonObject();
            JsonElement currentUser = userElement.get("user");

            for (int j = i + 1; j < array.size(); j++)
            {
                JsonObject theNextUserElement = array.get(j).getAsJsonObject();
                JsonElement theNextUser = theNextUserElement.get("user");

                if (currentUser.equals(theNextUser))
                { userChanges = userChanges + 1; }
            }

            if (userChanges >= 0)
            {
                numberOfChanges.add((("Number of changes for Editor "+ userCount+ " - "
                        + userChanges + 1)));
            }
        }
        return numberOfChanges;
    }

    public ObservableList<String> mostActiveEditors(UserRevisionSorter revisionParser, InputStream connection) throws Notify
    {
        JsonArray array = revisionParser.getUserRevisionArray(connection);
        ObservableList<String> mostActiveEditors = FXCollections.observableArrayList();

        int count = 0;
        for (int i = 0; i < array.size(); i++)
        {
            count++;
            JsonObject userElement = array.get(i).getAsJsonObject();
            JsonElement editor = userElement.get("user");

            String editorElement = String.valueOf(editor);
            mostActiveEditors.add("Editor " +count + " - " + editorElement);
        }
            return mostActiveEditors;
    }
}