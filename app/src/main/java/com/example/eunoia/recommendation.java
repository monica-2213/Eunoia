package com.example.eunoia;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.view.View;
import android.widget.Button;


public class recommendation extends AppCompatActivity {
    Button BtnMusic,BtnBreathing,BtnYoga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recommendation);


        BtnMusic = findViewById(R.id.BtnAssesment);
        BtnBreathing = findViewById(R.id.BtnBreathing);
        BtnYoga = findViewById(R.id.Btn911);

        BtnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), music.class);
                startActivity(intent);
            }
        });

        BtnBreathing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), breathing_exercise.class);
                startActivity(intent);
            }
        });

        BtnYoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Yoga.class);
                startActivity(intent);
            }
        });
    }

}


