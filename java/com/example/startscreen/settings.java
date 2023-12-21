package com.example.startscreen;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.startscreen.DataBaseHandler;

public class settings extends AppCompatActivity {
    private static final int GALLERY_REQUEST_CODE = 1;
    ImageView profile;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        profile = findViewById(R.id.profile);
        ImageView back=findViewById(R.id.back);
        ImageView changeuname=findViewById(R.id.changeusername);
        ImageView changepswd=findViewById(R.id.changepswd);
        ImageView viewstats=findViewById(R.id.viewstats);
        ImageView logout=findViewById(R.id.logut);

        SharedPreferences sharedPref = getSharedPreferences("username", Context.MODE_PRIVATE);
        String username=sharedPref.getString("username","");
        DataBaseHandler db=new DataBaseHandler(settings.this);
        SQLiteDatabase writableDB1 = db.getWritableDatabase();
        int profileid=db.getImage(username);
        int[] imageIds = {

                R.drawable.img1,R.drawable.img2, R.drawable.img3,R.drawable.img4,
                R.drawable.img5,R.drawable.img6, R.drawable.img7,R.drawable.img8,
                R.drawable.img9
        };
        if(profileid>=0)
        {
            profile.setImageResource(imageIds[profileid]);
            Log.d("ImageAdapterView",profileid+"\t");
        }
        else
        {
            profile.setImageResource(R.drawable.userprofile);
        }
        changeuname.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(settings.this,changeusername.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(settings.this);
                startActivity(i, options.toBundle());
            }
        });
        changepswd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(settings.this,changepassword.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(settings.this);
                startActivity(i, options.toBundle());
            }
        });
        viewstats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(settings.this,viewstats.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(settings.this);
                startActivity(i, options.toBundle());
            }
        });
        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(settings.this, login_register_page.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(settings.this);
                startActivity(intent, options.toBundle());
            }
        });
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                settings.this.finish();

            }
        });
        profile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(settings.this, gallery.class);
                startActivityForResult(i, GALLERY_REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK)
        {
            int selectedImageId = data.getIntExtra("id", -1);
            if (selectedImageId != -1)
            {
                SharedPreferences sharedPref = getSharedPreferences("username", Context.MODE_PRIVATE);
                String username=sharedPref.getString("username","");
                profile.setImageResource(ImageAdapter.mImageIds[selectedImageId]);
                DataBaseHandler db=new DataBaseHandler(settings.this);
                SQLiteDatabase writableDB = db.getWritableDatabase();
                db.updateImage(username,selectedImageId);
            }
        }
    }
}