package edu.bsu.cs222.wiki;

import java.io.InputStream;

public class Notify
{
    public void NotifyUser(InputStream connection)
    {
        if (connection == null)
        {
            System.out.println("The article you searched for does not exist.");
        }
    }
}
