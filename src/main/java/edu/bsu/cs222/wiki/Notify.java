package edu.bsu.cs222.wiki;

public class Notify extends Exception
{
    public Notify(String page_not_found)
    {
        System.out.println(page_not_found);
    }
}
