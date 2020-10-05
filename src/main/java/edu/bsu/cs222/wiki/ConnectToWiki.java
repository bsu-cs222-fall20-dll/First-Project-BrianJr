package edu.bsu.cs222.wiki;

import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ConnectToWiki
{
    public InputStream Query(String articleName) throws Exception
    {
        URL url = generateQueryURL(articleName);
        java.net.URLConnection connection = url.openConnection();

        if (connection == null)
        { System.out.println("Connection has not been established."); }

        assert connection != null;
        connection.setRequestProperty("User-Agent", "Project UNO/0.2 (bkwalker@bsu.edu)");
        connection.connect();

        InputStream in;

        try
        {
            in = connection.getInputStream();
            assert false;
        }

        catch(SocketTimeoutException e)
        { return null; }

        return in;
    }

    public URL generateQueryURL(String articleName) throws Exception
    {
        try
        {
            String query = "https://en.wikipedia.org/w/api.php?action=query&prop=revisions&format=json&rvprop" +
                    "=timestamp%7Cuser&rvlimit=20&titles=";

            return new URL(query + URLEncoder.encode(String.valueOf(articleName), StandardCharsets.UTF_8));
        }

        catch ( NullPointerException e )
        { return null; }
    }
}
