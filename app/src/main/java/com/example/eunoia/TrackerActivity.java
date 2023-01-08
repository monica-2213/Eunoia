package com.example.eunoia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class TrackerActivity extends AppCompatActivity {

    public FragmentManager fragmentManager;
    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            username = extras.getString("username");
        }else {
            username = "decoy";
        }
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("username",username);
        TrackerMenu trackerMenu = new TrackerMenu();
        trackerMenu.setArguments(bundle);
        fragmentTransaction.replace(R.id.frameLayout,trackerMenu).commit();
    }
}