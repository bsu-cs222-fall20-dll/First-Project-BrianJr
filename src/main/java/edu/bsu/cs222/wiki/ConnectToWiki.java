package edu.bsu.cs222.wiki;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class ConnectToWiki
{
    public InputStream connectToWikipedia(String articleName) throws IOException
    {
        Notify notify = new Notify();
        String wikiUrl = "https://en.wikipedia.org/w/api.php?action=query&prop=revisions&format=json&rvprop" +
                "=timestamp%7Cuser&rvlimit=20&titles=";

        URL url = new URL(wikiUrl + URLEncoder.encode(articleName, "UTF-8"));
        URLConnection connection = url.openConnection();

        connection.setRequestProperty("User-Agent",
                "CS222FirstProject/0.1 (bkwalker@bsu.edu)");
        connection.connect();


        InputStream in = connection.getInputStream();
        notify.NotifyUser(in);

        System.out.println("Connected!");


        return in;
    }
}
