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
    public List<String> mostRecentEditor(UserRevisionSorter revisionParser, InputStream connection) throws Notify
    {
        JsonArray array = revisionParser.getUserRevisionArray(connection);
        ObservableList<String> oList = FXCollections.observableArrayList();

        List<String> editorList = new ArrayList<>();

        for (int i = 0; i < array.size(); i++)
        {

            JsonObject userElement = array.get(i).getAsJsonObject();
            JsonElement editor = userElement.get("user");

            String editorString = String.valueOf(editor);

            editorList.add(editorString);
        }

//        editorList = FXCollections.observableArrayList();
//        return editorList;

        return editorList;
    }


    public String timeStamp(UserRevisionSorter revisionParser, InputStream connection) throws Notify
    {
        JsonArray array = revisionParser.getUserRevisionArray(connection);
        ArrayList<JsonElement> timeStampList = new ArrayList<>();

        for (int i = 0; i < array.size(); i++)
        {

            JsonObject userElement = array.get(i).getAsJsonObject();
            JsonElement timestamp = userElement.get("timestamp");

            timeStampList.add(timestamp);

        }
        return String.valueOf(timeStampList);
    }
}
