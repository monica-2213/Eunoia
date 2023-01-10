package com.example.eunoia;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class breathing_exercise extends AppCompatActivity {

        Button BtnBreathe1,BtnBreathe2,BtnBreathe3;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_breathing_exercise);


            BtnBreathe1 = findViewById(R.id.BtnBreathe1);
            BtnBreathe2 = findViewById(R.id.BtnBreathe2);
            BtnBreathe3 = findViewById(R.id.BtnBreathe3);
        }
    }