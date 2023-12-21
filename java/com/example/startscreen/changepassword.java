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

public class changepassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        EditText currentpassword=findViewById(R.id.password);
        EditText newpassword=findViewById(R.id.newpassword);

        TextView error=findViewById(R.id.errormsg);

        ImageView check=findViewById(R.id.check);
        currentpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b)
                {
                    if(isPasswordValid(currentpassword.getText().toString()))
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
       newpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b)
                {
                    if(isPasswordValid(newpassword.getText().toString()))
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
                DataBaseHandler db=new DataBaseHandler(changepassword.this);
                SQLiteDatabase writableDB = db.getWritableDatabase();

                    SharedPreferences sharedPref = getSharedPreferences("username", Context.MODE_PRIVATE);
                    String username = sharedPref.getString("username", "");
                String pswd=db.getPassword(username);
                if(!isPasswordValid(currentpassword.getText().toString())||!isPasswordValid(newpassword.getText().toString()))
                {
                    error.setText("Fields cannot be empty");

                }
                else {

                    if (username.isEmpty()) {
                        error.setText("Username not registered!");
                    }
                    else if(!pswd.equals(currentpassword.getText().toString()))
                    {
                        error.setText("Incorrect Password!");
                    }
                    else {
                        db.updatePswd(username, newpassword.getText().toString());
                       // Toast.makeText(changepassword.this, "Password Updated Succesfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(com.example.startscreen.changepassword.this, login_register_page.class);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(changepassword.this);
                        startActivity(i, options.toBundle());
                    }
                }

            }
        });
    }
    public boolean isPasswordValid(String password) {
        return !TextUtils.isEmpty(password) && password.length() >= 6;
    }
}