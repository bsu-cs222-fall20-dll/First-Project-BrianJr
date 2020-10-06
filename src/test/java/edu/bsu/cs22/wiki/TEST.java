package edu.bsu.cs22.wiki;


import com.google.gson.*;
import edu.bsu.cs222.wiki.UserRevisionSorter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Map;

public class TEST
{
    @Test
    public void UserRevisionSorterTEST()
    {
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("otherSample.json");
        assert inputStream != null;
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;

        for (Map.Entry<String, JsonElement> entry : pages.entrySet())
        {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
            Assertions.assertNotNull(array);
        }

        assert array != null;
        Assertions.assertEquals(4, array.size());
        System.out.println(array);
    }

    @Test
    public void RedirectTEST()
    {
        UserRevisionSorter sorter = new UserRevisionSorter();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("otherSample.json");
        JsonObject rootObject = sorter.getQueryObject(inputStream);

        System.out.println("------------------------------------------");
        sorter.isRedirectedTo(rootObject);
        System.out.println("------------------------------------------");
    }
}
