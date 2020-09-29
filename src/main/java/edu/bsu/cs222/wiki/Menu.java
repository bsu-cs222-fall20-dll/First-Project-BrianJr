package edu.bsu.cs222.wiki;

import java.io.*;
import java.util.Scanner;

public class Menu
{
    public void startApplication() throws Exception
    {
        int greetingHeader_DisplayCount = 0;
        while (true)
        {
            greetingHeader_DisplayCount++;
            Scanner input = new Scanner(System.in);

            if (greetingHeader_DisplayCount <= 1)
            { displayGreetingHeader(); }

            System.out.println("\nEnter the name of the article you are searching for: ");
            String articleName = input.nextLine();

            while (true)
            {
                UserRevisionSorter revisionParser = new UserRevisionSorter();
                ConnectToWiki toWiki = new ConnectToWiki();
                InputStream wikiConnection = toWiki.Query(articleName);

                displayMenuChoices();
                String userResponse = input.nextLine();
                System.out.println("--------------------------");

                if (userResponse.equalsIgnoreCase("Recent Changes") )
                {
                    RecentChanges changes = new RecentChanges();
                    changes.mostRecentAuthors(revisionParser, wikiConnection);
                }

                else if (userResponse.equalsIgnoreCase("Active Editors"))
                {
                    ActiveEditor activeUser = new ActiveEditor();
                    activeUser.mostActiveEditor(revisionParser, wikiConnection);
                }

                else if (userResponse.equalsIgnoreCase("Exit"))
                { System.exit(0); }

                else if (userResponse.equalsIgnoreCase("Back"))
                { break; }

                else
                { System.out.println("Please enter valid entry."); }
            }
        }
    }

    public static void displayGreetingHeader()
    {
        System.out.println();
        System.out.println("*-------------------------------------------------------------*");
        System.out.println("|     Thank You for choosing to Search Wiki Articles with     |");
        System.out.println("|            Searching For Wiki Articles Simulator            |");
        System.out.println("*-------------------------------------------------------------*");
    }

    public static void displayMenuChoices()
    {
        System.out.println("--------------------------");
        System.out.println("Enter 'Recent Changes' to see a list of the 20 most recent changes");
        System.out.println("Enter 'Active Editors' to see a list of the 20 most active editors");
        System.out.println("Enter 'Back' to search for another article");
        System.out.println("Enter 'Exit' to exit the program");
        System.out.println("--------------------------");
    }
}
