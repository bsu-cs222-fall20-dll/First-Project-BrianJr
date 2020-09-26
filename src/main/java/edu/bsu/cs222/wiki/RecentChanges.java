package edu.bsu.cs222.wiki;

import com.google.gson.*; import java.io.InputStream;

public class RecentChanges
{
    public void mostRecentAuthors(UserRevisionSorter revisionParser, InputStream connection)
    {
        JsonArray array = revisionParser.userRevision(connection);

        for (int count = 0; count < array.size(); count++)
        {
            System.out.println("--------------------------");
            System.out.printf("Editor %d: ", (count + 1));
            JsonObject submission = array.get(count).getAsJsonObject();
            JsonElement user = submission.get("user");
            System.out.println(user);

            System.out.print("Time Stamp: ");
            JsonObject submissionTime = array.get(count).getAsJsonObject();
            JsonElement timeStamp = submissionTime.get("timestamp");
            System.out.println(timeStamp);
        }
    }
}
