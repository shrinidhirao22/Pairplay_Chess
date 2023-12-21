package com.example.startscreen;

import androidx.appcompat.app.AppCompatActivity;
import com.example.startscreen.ImageAdapter;
import android.os.Bundle;
import android.widget.GridView;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

public class gallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
                GridView gridView = findViewById(R.id.gridview);
                int[] imageIds = {
                        R.drawable.img1,R.drawable.img2, R.drawable.img3,R.drawable.img4,
                        R.drawable.img5,R.drawable.img6, R.drawable.img7,R.drawable.img8,
                        R.drawable.img9
                };
                ImageAdapter adapter = new ImageAdapter(this, imageIds);
                gridView.setAdapter(adapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Intent i = new Intent(gallery.this, settings.class);
                        i.putExtra("id", position);
                        setResult(RESULT_OK, i);
                        finish();
            }
        });

    }
}