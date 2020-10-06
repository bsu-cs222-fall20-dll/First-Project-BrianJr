package edu.bsu.cs222.wiki;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class ConnectToWiki
{
    public InputStream Query(String articleName) throws Exception
    {
        try
        {
            URL url = generateQueryURL(articleName);
            java.net.URLConnection connection = url.openConnection();

            if (connection == null)
            { System.out.println("Connection has not been established."); }

            assert connection != null;
            connection.setRequestProperty("User-Agent", "Project UNO/0.2 (bkwalker@bsu.edu)");
            connection.connect();

            InputStream in;
            in = connection.getInputStream();
            assert false;

            return in;
        }

        catch ( UnknownHostException e )
        { System.out.println("A connection to wikipedia could not be established."); }

        return null;
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
