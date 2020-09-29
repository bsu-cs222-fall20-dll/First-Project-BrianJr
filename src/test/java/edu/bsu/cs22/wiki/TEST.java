package edu.bsu.cs22.wiki;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.bsu.cs222.wiki.Notify;
import edu.bsu.cs222.wiki.UserRevisionSorter;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
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
            Assert.assertNotNull(array);
        }

        assert array != null;
        Assert.assertEquals(4, array.size());
        System.out.println(array);

    }

    @Test
    public void NotifyTEST_ArticleThatDoesNotExist() throws Notify
    {
        UserRevisionSorter userRevisionSorter = new UserRevisionSorter();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("otherSample.json");
        assert inputStream != null;
        JsonArray json = userRevisionSorter.getUserRevisionArray(inputStream);
        assert json != null;

        InputStream errorPage = getClass().getClassLoader().getResourceAsStream("errorPage.json");
        JsonArray noPageExist = userRevisionSorter.getUserRevisionArray(errorPage);
        System.out.println(noPageExist);

        assert noPageExist == null;
        System.out.println(json);
    }
}
