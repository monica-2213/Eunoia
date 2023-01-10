package com.example.eunoia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button btnMotivation, btnToDoList, btnHabit, btnMood, btnQuiz, btnHelpline, btnBreathing, btnYoga, btnMusic, btnEmergency;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
                Intent intent = new Intent(getApplicationContext(), RecomendationActivity.class);
                startActivity(intent);
            }
        });

        btnToDoList = findViewById(R.id.btnToDoList);
        btnToDoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MotivationActivity.class);
                startActivity(intent);
            }
        });

        btnHabit = findViewById(R.id.btnHabit);
        btnHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TrackerActivity.class);
                startActivity(intent);
            }
        });

        btnMood = findViewById(R.id.btnMood);
        btnMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TrackerActivity.class);
                startActivity(intent);
            }
        });
    }
}
