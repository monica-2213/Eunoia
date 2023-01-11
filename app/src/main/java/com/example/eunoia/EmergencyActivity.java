package com.example.eunoia;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class EmergencyActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private String username;
    private DrawerLayout drawer;
    TextView textview, numview, textview2, numview2, textview3, numview3;
    Button btnManageEm;
    Button call1,call2,call3;
    static int PERMISSION_CODE = 100;
    private static final String SHARED_PREF_NAME = "Eunoia";
    private static final String CONTACT_NAME = "EmergencyName";
    private static final String CONTACT_NUMBER = "EmergencyNumber";
    private static final String CONTACT_NAME2 = "EmergencyName2";
    private static final String CONTACT_NUMBER2 = "EmergencyNumber2";
    private static final String CONTACT_NAME3 = "EmergencyName3";
    private static final String CONTACT_NUMBER3 = "EmergencyNumber3";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        Bundle extras = getIntent().getExtras();

        if(extras!=null){
            username = extras.getString("username");
        }else {
            username = "decoy";
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(ContextCompat.checkSelfPermission(EmergencyActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(EmergencyActivity.this, new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CODE);
        }

        textview = findViewById(R.id.ContactName1);
        numview = findViewById(R.id.number1);
        textview2 = findViewById(R.id.ContactName2);
        numview2 = findViewById(R.id.number2);
        textview3 = findViewById(R.id.ContactName3);
        numview3 = findViewById(R.id.number3);

        call1 = findViewById(R.id.ButtonCall4);
        call2 = findViewById(R.id.ButtonCall5);
        call3 = findViewById(R.id.ButtonCall6);




        SharedPreferences sp = getApplicationContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        String Name1 = sp.getString(CONTACT_NAME, "");
        String Num1 = sp.getString(CONTACT_NUMBER, "");
        String Name2 = sp.getString(CONTACT_NAME2, "");
        String Num2 = sp.getString(CONTACT_NUMBER2, "");
        String Name3 = sp.getString(CONTACT_NAME3, "");
        String Num3 = sp.getString(CONTACT_NUMBER3, "");

        textview.setText(Name1);
        numview.setText(Num1);
        textview2.setText(Name2);
        numview2.setText(Num2);
        textview3.setText(Name3);
        numview3.setText(Num3);

        btnManageEm = findViewById(R.id.ButtonManageEmContacts);
        btnManageEm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmergencyActivity.this, ManageEmergencyContactsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        call1.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){

                String phoneNo = numview.getText().toString();
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+phoneNo));
                startActivity(i);
            }
        });
        call2.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){

                String phoneNo = numview2.getText().toString();
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+phoneNo));
                startActivity(i);
            }
        });
        call3.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){

                String phoneNo = numview3.getText().toString();
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+phoneNo));
                startActivity(i);
            }
        });

        drawer = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                finish();
                break;

            case R.id.nav_motivation:
                Intent intentMot = new Intent(getApplicationContext(), MotivationActivity.class);
                intentMot.putExtra("username",username);
                startActivity(intentMot);
                finish();
                break;
            case R.id.nav_tracker:
                Intent intentTrack = new Intent(getApplicationContext(), TrackerActivity.class);
                intentTrack.putExtra("username",username);
                startActivity(intentTrack);
                finish();
                break;

            case R.id.nav_help:

                break;

            case R.id.nav_recommendation:
                Intent intentRecommend = new Intent(getApplicationContext(), RecomendationActivity.class);
                intentRecommend.putExtra("username",username);
                startActivity(intentRecommend);
                finish();
                break;

            case R.id.nav_emergency:
                Toast.makeText(this, "Already in the emergency page", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}
