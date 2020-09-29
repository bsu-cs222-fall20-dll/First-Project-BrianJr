package edu.bsu.cs222.wiki;

import com.google.gson.*;
import java.io.InputStream;

public class ActiveEditor
{
    public void mostActiveEditor(UserRevisionSorter revisionParser, InputStream connection) throws Notify
    {
        JsonArray array = revisionParser.getUserRevisionArray(connection);
       // HashMap<Integer, JsonElement> orderedUserList = new HashMap<>();

        int userCount = 0;

        //Collects each user element in array, currentUser
        for (int i = 0; i < array.size(); i++)
        {
            int userChanges = 0;
            userCount++;
            JsonObject userElement = array.get(i).getAsJsonObject();
            JsonElement currentUser = userElement.get("user");

            //Collects the next currentUser element in array, theNextUser
            for (int j = i + 1; j < array.size(); j++)
            {
                JsonObject theNextUserElement = array.get(j).getAsJsonObject();
                JsonElement theNextUser = theNextUserElement.get("user");

                //Checks if the currentUser appears twice in the array
                //If so, update the  number of changes for currentUser
                if (currentUser.equals(theNextUser))
                { userChanges = userChanges + 1; }
            }

            if (userChanges >= 0)
            {
               // System.out.println(orderedUserList);
                System.out.println("--------------------------");
                System.out.printf("Active Editor #%d: ", userCount);
                System.out.print(currentUser);
                System.out.println("\nNumber of changes made to article: " + (userChanges + 1)  );
            }

            if (userChanges < 0)
            {
                System.out.println("There has been changes to this article.");
            }
        }

        System.out.println("--------------------------");
        System.out.println("Done!");
    }
}
