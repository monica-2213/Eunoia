package com.example.eunoia;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Help extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Button Assesment,Helpline;
    private String username;
    private DrawerLayout drawer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Bundle extras = getIntent().getExtras();

        if(extras!=null){
            username = extras.getString("username");
        }else {
            username = "decoy";
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Assesment= findViewById(R.id.BtnAssesment);
        Assesment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Help.this, AssesmentActivity.class);
                startActivity(intent);
            }
        });

        Helpline= findViewById(R.id.BtnHelpline);
        Helpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Help.this, HelplineActivity.class);
                startActivity(intent);
            }
        });

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
                Toast.makeText(this, "Already in the help page", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_recommendation:
                Intent intentRecommend = new Intent(getApplicationContext(), RecomendationActivity.class);
                intentRecommend.putExtra("username",username);
                startActivity(intentRecommend);
                finish();
                break;

            case R.id.nav_emergency:
                Intent intentEmergency = new Intent(getApplicationContext(), EmergencyActivity.class);
                intentEmergency.putExtra("username",username);
                startActivity(intentEmergency);
                finish();
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
