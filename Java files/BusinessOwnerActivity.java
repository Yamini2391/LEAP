package com.example.leap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class BusinessOwnerActivity extends AppCompatActivity {
    static int id=1;
    Button register;
    EditText b_name, description, website, owner, contact, address, products;
    Spinner category;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_owner);
        register = findViewById(R.id.btnRegister);
        b_name = findViewById(R.id.etBusiness);
        description = findViewById(R.id.etDesc);
        products = findViewById(R.id.etProducts);
        website = findViewById(R.id.etWebsite);
        owner = findViewById(R.id.etOwner);
        contact = findViewById(R.id.etContact);
        address = findViewById(R.id.etAddress);
        category = findViewById(R.id.spCategory);
        db = new DBHelper(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean bool = db.insertBusiness(id++,b_name.getText().toString(),category.getSelectedItem().toString(),
                        description.getText().toString(),products.getText().toString(), website.getText().toString(), owner.getText().toString(),
                        contact.getText().toString(), address.getText().toString());
                if(bool) {
                    Toast.makeText(BusinessOwnerActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }
                //else
                    //Toast.makeText(BusinessOwnerActivity.this,"Registration failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}