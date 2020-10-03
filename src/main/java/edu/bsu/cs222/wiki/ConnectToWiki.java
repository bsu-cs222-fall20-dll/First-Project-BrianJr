package edu.bsu.cs222.wiki;

import javafx.scene.control.TextField;

import java.io.InputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ConnectToWiki
{
    public InputStream Query(TextField articleName) throws Exception
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

    public URL generateQueryURL(TextField articleName) throws Exception
    {
        String query = "https://en.wikipedia.org/w/api.php?action=query&prop=revisions&format=json&rvprop" +
                "=timestamp%7Cuser&rvlimit=20&titles=";

        return new URL(query + URLEncoder.encode(String.valueOf(articleName), StandardCharsets.UTF_8));
    }
}
