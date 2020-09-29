package edu.bsu.cs222.wiki;

import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;


public class ConnectToWiki
{
    public InputStream Query(String articleName) throws Exception
    {

        URL url = generateQueryURL(articleName);
        java.net.URLConnection connection = url.openConnection();

        connection.setRequestProperty("User-Agent", "Project UNO/0.1 (bkwalker@bsu.edu)");
        connection.connect();

        InputStream in;

        try
        {
            in = connection.getInputStream();
            System.out.println("--------------------------");
            System.out.println("Connected!");
        }

        catch(SocketTimeoutException e)
        { throw new Notify("Page not found"); }

        return in;
    }

    public URL generateQueryURL(String articleName) throws Exception
    {
        String query = "https://en.wikipedia.org/w/api.php?action=query&prop=revisions&format=json&rvprop" +
                "=timestamp%7Cuser&rvlimit=20&titles=";

        return new URL(query + URLEncoder.encode(articleName, "UTF-8"));
    }
}
