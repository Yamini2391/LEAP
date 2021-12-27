package com.example.leap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        TextView about = findViewById(R.id.tvAbout);
       String str = "Local Entrepreneurship Augmentation Platform (LEAP) has been developed in order to enhance the reach of small local businesses owned by women. We aim to help these budding entrepreneurs to reach out to a greater number of customers.\n" +
               " \n" +
               "Are you a woman entrepreneur?\n" +
               "Register with us to expand your business.\n" +
               " \n" +
               "Are you a customer?\n" +
               "Explore and contact various local businesses and help them grow.\n" +
               "\n";
        about.setText(str);
        Button contact = findViewById(R.id.btnContactUs);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel","9075583349",null));
                startActivity(i);
            }
        });
    }
}