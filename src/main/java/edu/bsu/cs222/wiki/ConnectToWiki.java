package edu.bsu.cs222.wiki;

import java.io.InputStream;
import java.net.*;

public class ConnectToWiki
{
    public InputStream Query(String articleName) throws Exception
    {
        URL url = generateQueryURL(articleName);
        java.net.URLConnection connection = url.openConnection();

        if (connection == null)
        { System.out.println("Connection has not been established."); }

        assert connection != null;
        connection.setRequestProperty("User-Agent", "Project UNO/0.1 (bkwalker@bsu.edu)");
        connection.connect();

        InputStream in;

        try
        {
            in = connection.getInputStream();
            assert false;

            System.out.println("--------------------------");
            System.out.println("Connection Established!");
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
