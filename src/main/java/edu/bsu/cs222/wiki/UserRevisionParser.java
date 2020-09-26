package edu.bsu.cs222.wiki;

import com.google.gson.*;
import java.io.*;
import java.util.Map;

public class UserRevisionParser
{
    public JsonArray revisionFinder(InputStream connection)
    {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(connection);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;

        for( Map.Entry<String,JsonElement> entry : pages.entrySet() )
        {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }
        return array;
    }
}

