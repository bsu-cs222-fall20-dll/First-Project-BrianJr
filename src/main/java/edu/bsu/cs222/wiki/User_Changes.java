package edu.bsu.cs222.wiki;

import java.util.Date;

public class User_Changes
{
    public String userName;
    public int numberOFChanges;
    public Date mostRecentChangeTimeSTamp;

    public User_Changes(String name, int count, Date mostRecentTimeStamp)
    {
        this.userName = name;
        this.numberOFChanges = count;
        this.mostRecentChangeTimeSTamp = mostRecentTimeStamp;
    }

    public String getName()
    {
        return userName;
    }

    public void setName(String name)
    {
        this.userName = name;
    }

    public int getCount()
    {
        return numberOFChanges;
    }

    public void setCount(int count)
    {
        this.numberOFChanges = count;
    }

    public Date getMostRecentTimeStamp()
    {
        return mostRecentChangeTimeSTamp;
    }

    public void setMostRecentTimeStamp(Date mostRecentTimeStamp)
    {
        this.mostRecentChangeTimeSTamp = mostRecentTimeStamp;
    }
}
