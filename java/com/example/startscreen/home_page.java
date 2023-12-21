package com.example.startscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class home_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Button newGame=findViewById(R.id.newgame);
        Button tutorial=findViewById(R.id.tutorial);
        ImageView exit=findViewById(R.id.exit);
        ImageView settings=findViewById(R.id.settings);

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(home_page.this, game_selection.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(home_page.this);
                startActivity(i, options.toBundle());
            }
        });
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {Intent i=new Intent(home_page.this,com.example.startscreen.tutorial.class);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(home_page.this);
            startActivity(i, options.toBundle());
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog exitDialog = new Dialog(home_page.this);
                exitDialog.setContentView(R.layout.game_exit_dialog);
                Button yesButton = exitDialog.findViewById(R.id.yesbutton);
                Button noButton = exitDialog.findViewById(R.id.nobutton);
                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finishAffinity();
                        System.exit(0);
                    }
                });
                noButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        exitDialog.dismiss();
                    }
                });
                exitDialog.show();

            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(home_page.this,com.example.startscreen.settings.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(home_page.this);
                startActivity(i, options.toBundle());
            }
        });
    }
}