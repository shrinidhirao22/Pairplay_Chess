package com.example.startscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class game_selection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);
        Button start=findViewById(R.id.start);
        ImageView back=findViewById(R.id.back);
       start.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

              Intent i=new Intent(game_selection.this, MainActivity.class);
               ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(game_selection.this);
               startActivity(i, options.toBundle());
         }
      });
       back.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                Intent i=new Intent(game_selection.this,home_page.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(game_selection.this);
                startActivity(i, options.toBundle());
            }
       });
    }
}