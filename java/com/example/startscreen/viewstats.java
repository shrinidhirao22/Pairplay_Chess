package com.example.startscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class viewstats extends AppCompatActivity {
    DataBaseHandler db;
    SQLiteDatabase readableDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstats);

        SharedPreferences sharedPref = getSharedPreferences("username", Context.MODE_PRIVATE);
        String username=sharedPref.getString("username","");

        db=new DataBaseHandler(viewstats.this);
        readableDB = db.getWritableDatabase();

        TextView matches_played=findViewById(R.id.matchesplayed);
        TextView matches_won=findViewById(R.id.matcheswon);
        TextView matches_lost=findViewById(R.id.matcheslost);

        int matchesPlayed=db.getMatchesPlayedForUser(username);
        int matchesWon=db.getMatchesWonForUser(username);
        int matchesLost=db.getMatchesLostForUser(username);


        matches_played.setText(String.valueOf(matchesPlayed));
       matches_won.setText(String.valueOf(matchesWon));
       matches_lost.setText(String.valueOf(matchesLost));
    }
}