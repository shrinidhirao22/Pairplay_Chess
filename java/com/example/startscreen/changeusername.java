package com.example.startscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class changeusername extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeusername);

        EditText currentusername=findViewById(R.id.username);
        EditText newusername=findViewById(R.id.newusername);

        TextView error=findViewById(R.id.errormsg);

        ImageView check=findViewById(R.id.check);
        currentusername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b)
                {
                    if(isUsernameValid(currentusername.getText().toString()))
                    {
                        error.setText("");
                        check.setClickable(true);
                    }
                    else
                    {
                        error.setText("All the fields are mandatory!");
                        check.setClickable(false);
                    }
                }
            }
        });
        newusername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b)
                {
                    if(isUsernameValid(newusername.getText().toString()))
                    {
                        error.setText("");
                        check.setClickable(true);
                    }
                    else
                    {
                        error.setText("All the fields are mandatory!");
                        check.setClickable(false);
                    }
                }
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHandler db=new DataBaseHandler(changeusername.this);
                SQLiteDatabase writableDB = db.getWritableDatabase();

                String uname=db.getUsername(currentusername.getText().toString());
                SharedPreferences sharedPref = getSharedPreferences("username", Context.MODE_PRIVATE);
                String username1="";
                username1=sharedPref.getString("username","");
                if(!isUsernameValid(currentusername.getText().toString())||!isUsernameValid(newusername.getText().toString()))
                {
                    error.setText("Fields cannot be empty");

                }
                else {
                    if(!username1.equals(currentusername.getText().toString()))
                    {
                        error.setText("Invalid Username!");

                    }
                    else if (uname.isEmpty()) {
                        error.setText("Username not registered!");
                    } else {
                        db.updateUname(currentusername.getText().toString(), newusername.getText().toString());
                   //     Toast.makeText(changeusername.this, "Username Updated Succesfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(com.example.startscreen.changeusername.this, login_register_page.class);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(changeusername.this);
                        startActivity(i, options.toBundle());
                    }
                }
            }
        });
    }
    public boolean isUsernameValid(String username) {
        return !TextUtils.isEmpty(username);
    }
}