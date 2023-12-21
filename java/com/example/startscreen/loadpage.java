package com.example.startscreen;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
public class loadpage extends AppCompatActivity
{
    private static final int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadpage);
        DataBaseHandler db=new DataBaseHandler(loadpage.this);
        SQLiteDatabase writableDB = db.getWritableDatabase();
        ImageView imageView = findViewById(R.id.image_view_1);
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        /*An instance of RotateAnimation is created to rotate the imageview
        360 degree with 50% relative width and 50% relative height
        First Argument is:start angle
        Second Argument is:end angle
        Third Argument  and Fourth Argument is responsible for the rotation along the center of the imageview*/
        
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        imageView.startAnimation(rotateAnimation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.clearAnimation();
                Intent intent = new Intent(loadpage.this, login_register_page.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(loadpage.this);
                startActivity(intent, options.toBundle());
            }
        }, 5000);//specifies that whatever code is inside the function is executed after 5 seconds of time period.

    }
}