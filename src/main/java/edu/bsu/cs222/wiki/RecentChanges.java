package edu.bsu.cs222.wiki;

import com.google.gson.*;
import java.io.InputStream;

public class RecentChanges
{
    public void mostRecentAuthors(UserRevisionSorter revisionParser, InputStream connection) throws Notify
    {
        JsonArray array = revisionParser.getUserRevisionArray(connection);

        for (int i = 0; i < array.size(); i++)
        {
            //prints each editor
            System.out.printf("Editor %d: ", (i + 1));
            JsonObject submission = array.get(i).getAsJsonObject();
            JsonElement user = submission.get("user");
            System.out.println(user);

            //prints each time stamp
            System.out.print("Time Stamp: ");
            JsonObject submissionTime = array.get(i).getAsJsonObject();
            JsonElement timeStamp = submissionTime.get("timestamp");
            System.out.println(timeStamp);
            System.out.println("--------------------------");
        }

        System.out.println("Done!");
    }
}
