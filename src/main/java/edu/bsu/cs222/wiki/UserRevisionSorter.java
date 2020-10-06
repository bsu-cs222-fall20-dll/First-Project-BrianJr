package edu.bsu.cs222.wiki;

import com.google.gson.*;
import java.io.*;
import java.util.Map;

public class UserRevisionSorter
{
    public JsonArray getUserRevisionArray(InputStream connection) throws Exception
    { return parse(getQueryObject(connection)); }

    public JsonObject getQueryObject(InputStream stream)
    {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(stream);

        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();

        return rootObject.getAsJsonObject("query");
    }

    public void isRedirectedTo( JsonObject queryObject)
    {
        JsonArray redirectsArray = queryObject.getAsJsonArray("redirects");

        if (redirectsArray == null)
        { return; }

        for (int i = 0; i < redirectsArray.size(); i++)
        {
            String redirectedTo = redirectsArray.get(i).getAsJsonObject().get("to").getAsString();
            System.out.printf("You have been redirected to: %s\n",redirectedTo);
        }
    }

    public JsonArray parse(JsonObject queryObject) throws Exception
    {
        JsonArray revisionArray = null;

        try
        {
            JsonObject pages = queryObject.getAsJsonObject( "pages" );

            // ITERATE OVER EVERY ENTRY IN PAGES
            for ( Map.Entry < String, JsonElement > entry : pages.entrySet() )
            {
                boolean pageNotFound = entry.getKey().equals( "-1" );

                if ( pageNotFound )
                { throw new Exception( "Page Not Found!" ); }

                JsonObject entryObject = entry.getValue().getAsJsonObject();
                revisionArray = entryObject.getAsJsonArray( "revisions" );
            }
            return revisionArray;
        }

        catch ( NullPointerException e )
        { return null; }
    }
}

