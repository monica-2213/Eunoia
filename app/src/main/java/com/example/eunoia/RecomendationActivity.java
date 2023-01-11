package com.example.eunoia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.eunoia.Mode.Mood;
import com.google.android.material.navigation.NavigationView;

public class RecomendationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    public FragmentManager fragmentManager;
    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            username = extras.getString("username");
        }else {
            username = "decoy";
        }

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
                Intent intentMotivation = new Intent(getApplicationContext(), MotivationActivity.class);
                intentMotivation.putExtra("username",username);
                startActivity(intentMotivation);
                finish();
                break;
            case R.id.nav_tracker:
                Intent intentTracker = new Intent(getApplicationContext(), TrackerActivity.class);
                intentTracker.putExtra("username",username);
                startActivity(intentTracker);
                finish();
                break;

            case R.id.nav_help:
                Intent intentHelp = new Intent(getApplicationContext(), Help.class);
                intentHelp.putExtra("username",username);
                startActivity(intentHelp);
                finish();
                break;

            case R.id.nav_recommendation:
                Toast.makeText(this, "Already in the recommendation page", Toast.LENGTH_SHORT).show();
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