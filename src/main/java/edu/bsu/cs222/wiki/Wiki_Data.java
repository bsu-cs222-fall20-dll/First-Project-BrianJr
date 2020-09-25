package edu.bsu.cs222.wiki;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Wiki_Data
{
    public static void main(String[] args) throws IOException
    {

        articleToSearchFor();
        connectToWiki();

    }

    public static String articleToSearchFor()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the article you want to search: ");
        return input.nextLine();
    }

    private static URLConnection connectToWiki() throws IOException
    {
        String encodedQuery = encodeValue( articleToSearchFor() );

        URL url = new URL(
                "\"https://en.wikipedia.org/w/api.php?action=query&origin=*&format=json&generator=search&gsrnamespace" +
                        "=0&gsrlimit=5&gsrsearch=" + encodedQuery);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "CS222FirstProject/0.1 (bkwalker@bsu.edu)");
        connection.connect();
        return connection;
    }

    private static void getUserRevisions() throws IOException
    {
        URLConnection json = connectToWiki();
        JsonObject wiki_Article = new JsonParser().parse(String.valueOf(json)).getAsJsonObject();

        JsonArray array = wiki_Article.getAsJsonArray("revisions");

        for ( int i = 0; i < array.size(); i++)
        {
            String userName = array.get(i).getAsJsonObject().get("user").getAsString();
            String timestamp = array.get(i).getAsJsonObject().get("user").getAsString();
            System.out.println(userName);
            System.out.println(timestamp);
        }
    }

    private static String encodeValue(String value)
    {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }
}
