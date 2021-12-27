package com.example.leap;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerActivity extends AppCompatActivity {

    Button view;
    DBHelper db;
    Spinner category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        view = findViewById(R.id.btnView);
        category = findViewById(R.id.spinnerCust);
        db = new DBHelper(this);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cat = category.getSelectedItem().toString();
                Cursor res = db.getData(cat);
                // int count = res.getCount();
                LinearLayout ll = (LinearLayout) findViewById(R.id.LL);
                ll.removeAllViews();
                if(res.getCount()==0){
                    Toast.makeText(CustomerActivity.this,"No entry exists",Toast.LENGTH_SHORT).show();
                    return;
                }
                /*StringBuffer sb = new StringBuffer();
                while(res.moveToNext()){
                    sb.append("Business name: "+res.getString(1)+"\n");
                    sb.append("Category: "+res.getString(2)+"\n");
                    sb.append("Business owner: "+res.getString(5)+"\n");
                    sb.append("_____________________________________"+"\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(CustomerActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Business entries");
                builder.setMessage(sb.toString());
                builder.show();*/
                Button[] btns = new Button[res.getCount()];
                int i=0;
                while(res.moveToNext()){
                    TextView tv = new TextView(CustomerActivity.this);
                    StringBuffer sb = new StringBuffer();
                    StringBuffer allDetails = new StringBuffer();
                    sb.append("\nBusiness name: "+res.getString(1)+"\n");
                    sb.append("Category: "+res.getString(2)+"\n");
                    sb.append("Products: "+res.getString(4)+"\n");
                    sb.append("Business owner: "+res.getString(6)+"\n");
                    tv.setText(sb.toString());
                    allDetails.append("Business Name: "+res.getString(1)+"\n\n");
                    allDetails.append("Category: "+res.getString(2)+"\n\n");
                    allDetails.append("Products: "+res.getString(4)+"\n\n");
                    allDetails.append("Description: "+res.getString(3)+"\n\n");
                    allDetails.append("Owner: "+res.getString(6)+"\n\n");
                    allDetails.append("Address: "+res.getString(8)+"\n\n");
                    String phnNumber = res.getString(7);
                    String website = res.getString(5);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    tv.setLayoutParams(params);
                    tv.setTextColor(Color.BLACK);
                    tv.setTextSize(20);
                    ll.addView(tv);
                    btns[i] = new Button(CustomerActivity.this);
                    btns[i].setText("View Details");
                    LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    btns[i].setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            bundle.putString("record",allDetails.toString());
                            bundle.putString("phone",phnNumber);
                            bundle.putString("site",website);

                            Intent i = new Intent(getApplicationContext(),BusinessDetails.class);
                            i.putExtras(bundle);
                            startActivity(i);
                        }
                    });
                    params1.gravity = Gravity.RIGHT;
                    btns[i].setBackgroundColor(Color.parseColor("cyan"));
                    btns[i].setLayoutParams(params1);
                    ll.addView(btns[i]);
                    i++;
                }
            }
        });
    }
}