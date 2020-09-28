package edu.bsu.cs222.wiki;

import java.io.*; import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Scanner input = new Scanner(System.in);

        displayGreetingHeader();

        System.out.println("\nEnter the name of the article you are searching for: ");
        String articleName = input.nextLine();

        UserRevisionSorter revisionParser = new UserRevisionSorter();
        ConnectToWiki toWiki = new ConnectToWiki();
        InputStream wikiConnection = toWiki.connectToWikipedia(articleName);

        displayMenuChoices();

        String userResponse = input.nextLine();

        if ( userResponse.equalsIgnoreCase("Recent Changes") )
        {
            RecentChanges changes = new RecentChanges();
            changes.mostRecentAuthors(revisionParser,wikiConnection);
        }

        else if ( userResponse.equalsIgnoreCase("Active Editors") )
        {
            ActiveEditor activeUser = new ActiveEditor();
            activeUser.mostActiveEditor(revisionParser,wikiConnection);
        }

        else if ( userResponse.equalsIgnoreCase("bye") )
        { System.exit(0); }

        else
            {
            System.out.println("Please enter valid response.");
            System.exit(-7);
            }
    }

    public static void displayGreetingHeader()
    {
        System.out.println("*-------------------------------------------------------------*");
        System.out.println("|     Thank You for choosing to Search Wiki Articles with     |");
        System.out.println("|            Searching For Wiki Articles Simulator            |");
        System.out.println("*-------------------------------------------------------------*");
    }

    public static void displayMenuChoices()
    {
        System.out.println("--------------------------");
        System.out.println("Enter 'Recent Changes' to see a list of the 20 most recent editors");
        System.out.println("Enter 'Active Editors' to see the most active editors");
        System.out.println("Enter 'bye' to exit the program");
        System.out.println("--------------------------");
    }
}



