package edu.bsu.cs222.wiki;

import com.google.gson.*;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.io.InputStream;
import java.util.*;

public class RecentChanges
{
    public ObservableList<String> mostRecentEditor(UserRevisionSorter revisionParser, InputStream connection) throws Notify
    {
        JsonArray array = revisionParser.getUserRevisionArray(connection);
        ObservableList<String> editorList = FXCollections.observableArrayList();
        int count = 0;

        for (int i = 0; i < array.size(); i++)
        {
            count++;
            JsonObject userElement = array.get(i).getAsJsonObject();
            JsonElement editor = userElement.get("user");

            String editorElement = String.valueOf(editor);

            editorList.add("Editor " +count + " - " + editorElement);
        }
        return editorList;
    }


    public ObservableList<String> timeStamp(UserRevisionSorter revisionParser, InputStream connection) throws Notify
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

            timeStampList.add("Timestamp " +count + " - " + stampElement);
        }
        return timeStampList;
    }
}
