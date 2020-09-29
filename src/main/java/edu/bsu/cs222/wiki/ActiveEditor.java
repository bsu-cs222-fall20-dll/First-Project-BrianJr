package edu.bsu.cs222.wiki;

import com.google.gson.*; import java.io.InputStream;

public class ActiveEditor
{
    public void mostActiveEditor(UserRevisionSorter revisionParser, InputStream connection) throws Notify
    {

        JsonArray array = revisionParser.getUserRevisionArray(connection);

        for (int i = 0; i < array.size(); i++)
        {
            int changes = 0;
            JsonObject submission = array.get(i).getAsJsonObject();
            JsonElement userToCheck = submission.get("user");

            for (int j = i + 1; j < array.size(); j++) {
                JsonObject submissionToCheckAgainst = array.get(j).getAsJsonObject();
                JsonElement userToCheckAgainst = submissionToCheckAgainst.get("user");

                if (userToCheck.equals(userToCheckAgainst)) {
                    changes = changes + 1;
                }
            }

            if (changes >= 0) {
                System.out.println("Active User:" + userToCheck);
                System.out.println("Number of changes: " + (changes + 1) + "\n" );
            }
        }
    }
}
