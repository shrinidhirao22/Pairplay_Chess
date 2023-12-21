package com.example.startscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login_register_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register_page);
        Button login=findViewById(R.id.login);
        Button register=findViewById(R.id.register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(login_register_page.this,login.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(login_register_page.this);
                startActivity(i, options.toBundle());

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(login_register_page.this,register.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(login_register_page.this);
                startActivity(i, options.toBundle());
            }
        });
    }
}