package com.example.startscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
public class login extends AppCompatActivity {
    TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText username=findViewById(R.id.username);
        EditText password=findViewById(R.id.password);
        error=findViewById(R.id.errormsg);
        ImageView login=findViewById(R.id.login);
        username.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean hasFocus)
            {
                if (!hasFocus)
                {
                    if(isUsernameValid(username.getText().toString()))
                    {
                        error.setText("");
                       login.setClickable(true);
                    }
                    else
                    {
                        error.setText("All the fields are mandatory!");
                        login.setClickable(false);
                    }
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus)
                {
                    if(isPasswordValid( password.getText().toString()))
                    {
                        error.setText("");
                        login.setClickable(true);
                    }
                    else
                    {
                        error.setText("Password field is mandatory with length atleast 6!");
                        login.setClickable(false);
                    }
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHandler db=new DataBaseHandler(login.this);
                SQLiteDatabase writableDB = db.getReadableDatabase();
                String uname=db.getUsername(username.getText().toString());

                if(!isUsernameValid(username.getText().toString()) || !isPasswordValid(password.getText().toString()))
                {
                    error.setText("Fields cannot be empty!");
                }
                else {

                    if (uname.isEmpty()) {
                        error.setText("Username not registered!");
                    } else {
                        String pswd = db.getPassword(uname);
                        if (password.getText().toString().equals(pswd)) {
                            SharedPreferences sharedPref = getSharedPreferences("username", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("username", uname);
                            editor.apply();
                            Intent i = new Intent(com.example.startscreen.login.this, home_page.class);
                            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(login.this);
                            startActivity(i, options.toBundle());




                        } else {
                            error.setText("Invalid Password!");
                        }
                    }
                }
            }
        });
    }
    public boolean isUsernameValid(String username) {
        return !TextUtils.isEmpty(username);
    }
    public boolean isPasswordValid(String password) {
        return !TextUtils.isEmpty(password) && password.length() >= 6;
    }
}