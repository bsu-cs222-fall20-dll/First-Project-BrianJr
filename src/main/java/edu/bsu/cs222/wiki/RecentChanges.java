package edu.bsu.cs222.wiki;

import com.google.gson.*;
import java.io.InputStream;

public class RecentChanges
{
    public String mostRecentEditor(UserRevisionSorter revisionParser, InputStream connection) throws Notify
    {
        JsonArray array = revisionParser.getUserRevisionArray(connection);

        for (int i = 0; i < array.size(); i++)
        {
            //prints each editor
            System.out.printf("Editor %d: ", (i + 1));
            JsonObject userElement = array.get(i).getAsJsonObject();
            JsonElement editor = userElement.get("user");
            System.out.println(editor);
            
            //prints each time stamp
            System.out.print("Time Stamp: ");
            JsonObject time_stampElement = array.get(i).getAsJsonObject();
            JsonElement timeStamp = time_stampElement.get("timestamp");
            System.out.println(timeStamp);
            System.out.println("--------------------------");
        }

        System.out.println("Done!");
        return "Done!";
    }
}
