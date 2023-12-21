package com.example.startscreen;

public class UserInfo
{
    String username="";
    String password="";
    int matchesPlayed,matchesWon,matchesLost;
    UserInfo(){}
    UserInfo(String n,String p)
    {
        username=n;
        password=p;
        matchesPlayed=matchesLost=matchesWon=0;
    }

    public String getName()
    {
        return this.username;
    }
    public String getPswd()
    {
        return  this.password;
    }
    public int getMatchesPlayed(){return this.matchesPlayed;}
    public int getMatchesWon(){return this.matchesWon;}
    public int getMatchesLost(){return this.matchesLost;}

}
