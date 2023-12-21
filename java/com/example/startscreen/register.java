package com.example.startscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class register extends AppCompatActivity {
TextView error;
String uname="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText username=findViewById(R.id.username);
        EditText password=findViewById(R.id.pswd);
        EditText cpassword=findViewById(R.id.confirmpswd);
        error=findViewById(R.id.errormsg);
        ImageView register=findViewById(R.id.reg);
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
                       register.setClickable(true);
                   }
                   else
                   {
                       error.setText("All the fields are mandatory!");
                       register.setClickable(false);
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
                        register.setClickable(true);
                    }
                    else
                    {
                        error.setText("Password field is mandatory with length atleast 6!");
                        register.setClickable(false);
                    }
                }
            }
        });
        cpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus)
                {
                    if(isPasswordValid(cpassword.getText().toString()))
                    {
                        error.setText("Fields cannot be empty!");
                        register.setClickable(false);
                    }
                    if(isPasswordMatching( password.getText().toString(),cpassword.getText().toString())  )
                    {
                        error.setText("");
                        register.setClickable(true);
                    }
                    else
                    {
                        error.setText("Password Mismatch!");
                        register.setClickable(false);
                    }
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHandler db=new DataBaseHandler(register.this);
                SQLiteDatabase writableDB = db.getWritableDatabase();
                String user_name=db.getUsername(username.getText().toString());
                if(!isUsernameValid(username.getText().toString()) || !isPasswordValid( password.getText().toString()) || !isPasswordValid(cpassword.getText().toString())  )
                {
                    error.setText("Fields cannot be empty!");
                }
                else if(!isPasswordMatching(password.getText().toString(),cpassword.getText().toString()))
                {
                    error.setText("Password Mismatch!");
                }
                else{
                    if (user_name.isEmpty()) {
                        UserInfo ui = new UserInfo(username.getText().toString(), password.getText().toString());
                        db.addUser(ui);
                       // Toast.makeText(register.this, "Registered Succesfully!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(register.this, login.class);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(register.this);
                        startActivity(i, options.toBundle());
                    } else {
                        error.setText("User already exists!");
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
    public boolean isPasswordMatching(String password,String confirmPassword)
    {
        return password.equals(confirmPassword);
    }

}