package com.example.leap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BusinessDetails extends AppCompatActivity {

    Button call,website;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_details);
        TextView details = findViewById(R.id.tvDetails);
        call = findViewById(R.id.btnCall);
        website=findViewById(R.id.btnSite);
        Bundle bundle = null;
        bundle = this.getIntent().getExtras();
        String s = bundle.getString("record");
        String phone = bundle.getString("phone");
        String siteString = bundle.getString("site");
        details.setText(s);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneCall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",phone,null));
                startActivity(phoneCall);
            }
        });
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent siteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+siteString));
                startActivity(siteIntent);
            }
        });

    }
}