package com.example.eunoia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    Button btnMotivation, btnToDoList, btnHabit, btnMood, btnMusic;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
            //The key argument here must match that used in the other activity
        }
        else{
            username = "Wanderer";
        }

        btnMotivation = findViewById(R.id.btnMotivation);
        btnMotivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MotivationActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        btnToDoList = findViewById(R.id.btnTracker);
        btnToDoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TrackerActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        btnHabit = findViewById(R.id.btnRecommendation);
        btnHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RecomendationActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        btnMood = findViewById(R.id.btnHelp);
        btnMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Help.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

        btnMusic = findViewById(R.id.btnEmergency);
        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EmergencyActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_profile:
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_home:
                Toast.makeText(this, "Already at home page", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_log_out:
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
        }
    }
}
