package edu.bsu.cs222.wiki;

import com.google.gson.*; import java.io.InputStream;

public class ActiveEditor
{
    public void mostActiveEditor(UserRevisionSorter revisionParser, InputStream connection)
    {

        JsonArray array = revisionParser.userRevision(connection);

        for (int i = 0; i < array.size(); i++)
        {
            int duplicate = 0;
            JsonObject submission = array.get(i).getAsJsonObject();
            JsonElement userToCheck = submission.get("user");

            for (int j = i + 1; j < array.size(); j++) {
                JsonObject submissionToCheckAgainst = array.get(j).getAsJsonObject();
                JsonElement userToCheckAgainst = submissionToCheckAgainst.get("user");
                if (userToCheck.equals(userToCheckAgainst)) {
                    duplicate = duplicate + 1;
                }
            }

            if (duplicate >= 0) {
                System.out.println("Active User:" + userToCheck);
                System.out.println("Number of changes: " + (duplicate + 1) + "\n" );
            }
        }
    }
}
