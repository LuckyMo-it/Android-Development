package com.nitin.imagegallery;

import androidx.appcompat.app.AppCompatActivity;
import com.nitin.imagegallery.ImageAdapter;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int[] images = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6
    };
    ArrayList<Integer>im=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<10;i++){
            for(int n:images){
                im.add(n);
            }
        }
        GridView gridView = findViewById(R.id.gridView);


        gridView.setAdapter(new ImageAdapter(this, im));
        gridView.setAdapter(new ImageAdapter(this, im));

// Show image in popup on item click
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            // Create an ImageView to show in dialog
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(im.get(position));
            imageView.setAdjustViewBounds(true);
            imageView.setPadding(20, 20, 20, 20);

            // Show in AlertDialog
            new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this)
                    .setView(imageView)
                    .setPositiveButton("Close", null)
                    .show();
        });

    }
}