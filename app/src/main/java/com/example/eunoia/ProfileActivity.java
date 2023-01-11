package com.example.eunoia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    EditText email, name, password, dateOfBirth, gender;
    String username;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        gender = findViewById(R.id.gender);

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            username = extras.getString("username");
        }else {
            username = "decoy";
        }
        dbHelper = new DBHelper(this);
        Cursor res = dbHelper.getAllInfo(username);
        res.moveToNext();

        email.setText(res.getString(1));
        name.setText(res.getString(2));
        password.setText(res.getString(3));
        dateOfBirth.setText(res.getString(4));
        gender.setText(res.getString(5));

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_profile:
                Toast.makeText(this, "Already at home page", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_home:
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_log_out:
                finish();
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {

    }
}