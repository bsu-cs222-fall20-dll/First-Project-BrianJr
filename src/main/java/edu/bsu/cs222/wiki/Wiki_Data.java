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
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Wiki_Data
{
    public static void main(String[] args) throws IOException
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the article you want to search: ");
        String wikiArticleName = input.nextLine();
        connectToWiki(wikiArticleName);

    }

    private static URLConnection connectToWiki(String wikiArticleName) throws IOException
    {
        String encodedQuery = encodeValue(wikiArticleName);

        URL url = new URL(
                "\"https://en.wikipedia.org/w/api.php?action=query&origin=*&format=json&generator=search&gsrnamespace" +
                        "=0&gsrlimit=5&gsrsearch=" + encodedQuery);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "CS222FirstProject/0.1 (bkwalker@bsu.edu)");
        connection.connect();
        return connection;
    }

    private static Document readJSonFileFrom()
    {
        return null;
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
