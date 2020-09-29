package edu.bsu.cs222.wiki;

import com.google.gson.*; import java.io.InputStream;

public class ActiveEditor
{
    public void mostActiveEditor(UserRevisionSorter revisionParser, InputStream connection) throws Notify
    {
        JsonArray array = revisionParser.getUserRevisionArray(connection);

        int userCount = 0;
        for (int i = 0; i < array.size(); i++)
        {
            int changes = 0;
            userCount++;
            JsonObject submission = array.get(i).getAsJsonObject();
            JsonElement userToCheck = submission.get("user");

            for (int j = i + 1; j < array.size(); j++) {
                JsonObject submissionToCheckAgainst = array.get(j).getAsJsonObject();
                JsonElement userToCheckAgainst = submissionToCheckAgainst.get("user");

                if (userToCheck.equals(userToCheckAgainst)) {
                    changes = changes + 1;
                }
            }

            if (changes >= 0)
            {
                System.out.println("--------------------------");
                System.out.printf("Active Editor #%d: ", userCount);
                System.out.print(userToCheck);
                System.out.println("\nNumber of changes: " + (changes + 1)  );
            }
        }
        System.out.println("--------------------------");
        System.out.println("Done!");
    }
}
