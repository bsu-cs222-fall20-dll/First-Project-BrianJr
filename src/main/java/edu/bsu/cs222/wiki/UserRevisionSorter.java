package edu.bsu.cs222.wiki;

import com.google.gson.*;
import java.io.*;
import java.util.Map;

public class UserRevisionSorter
{
    public JsonArray getUserRevisionArray(InputStream connection) throws Notify
    {
        return parse(getQueryObject(connection));
    }

    public JsonObject getQueryObject(InputStream stream)
    {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(stream);
        // GETS THE ENTIRE JSON OBJECT
        JsonElement rootElement = parser.parse(reader);
        // THE ROOT ELEMENT CONVERTS THAT ELEMENT INTO AN OBJECT WE CAN QUERY
        JsonObject rootObject = rootElement.getAsJsonObject();
        return rootObject.getAsJsonObject("query");
    }

    public JsonArray parse(JsonObject queryObject) throws Notify
    {
        JsonObject pages = queryObject.getAsJsonObject("pages");
        JsonArray array = null;

        // ITERATE OVER EVERY ENTRY IN PAGES
        for (Map.Entry <String, JsonElement> entry : pages.entrySet())
        {
            boolean pageNotFound = entry.getKey().equals("-1");
            if (pageNotFound)
            { throw new Notify("Page not found"); }
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }
        return array;
    }
}

