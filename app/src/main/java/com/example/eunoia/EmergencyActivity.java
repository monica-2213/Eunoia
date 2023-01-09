package com.example.eunoia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class EmergencyActivity extends AppCompatActivity {

    TextView textview,numview,textview2,numview2,textview3,numview3;
    Button btnManageEm;
    private static final String SHARED_PREF_NAME= "Eunoia";
    private static final String CONTACT_NAME= "EmergencyName";
    private static final String CONTACT_NUMBER= "EmergencyNumber";
    private static final String CONTACT_NAME2= "EmergencyName2";
    private static final String CONTACT_NUMBER2= "EmergencyNumber2";
    private static final String CONTACT_NAME3= "EmergencyName3";
    private static final String CONTACT_NUMBER3= "EmergencyNumber3";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);


        textview = findViewById(R.id.TVContact1);
        numview = findViewById(R.id.number1);
        textview2 = findViewById(R.id.TVContact2);
        numview2 = findViewById(R.id.number2);
        textview3 = findViewById(R.id.TVContact3);
        numview3 = findViewById(R.id.number3);

        SharedPreferences sp = getApplicationContext().getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);


        String Name1 = sp.getString(CONTACT_NAME,"");
        String Num1 = sp.getString(CONTACT_NUMBER,"");
        String Name2 = sp.getString(CONTACT_NAME2,"");
        String Num2 = sp.getString(CONTACT_NUMBER2,"");
        String Name3 = sp.getString(CONTACT_NAME3,"");
        String Num3 = sp.getString(CONTACT_NUMBER3,"");

        textview.setText(Name1);
        numview.setText(Num1);
        textview2.setText(Name2);
        numview2.setText(Num2);
        textview3.setText(Name3);
        numview3.setText(Num3);

        btnManageEm = findViewById(R.id.ButtonManageEmContacts);
        btnManageEm .setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmergencyActivity.this,ManageEmergencyContactsActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
