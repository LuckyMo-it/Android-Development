package com.nitin.registration;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Second2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second2);

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve intent and set data on TextViews
        Intent intent = getIntent();
        String firstName = intent.getStringExtra("Firstname");
        String lastName = intent.getStringExtra("Lastname");

        TextView t1 = findViewById(R.id.textView2);
        TextView t2 = findViewById(R.id.textView3);

        t1.setText(firstName != null ? firstName : "No First Name");
        t2.setText(lastName != null ? lastName : "No Last Name");
    }
}
