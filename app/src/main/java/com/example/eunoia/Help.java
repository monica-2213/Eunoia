package com.example.eunoia;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Help extends AppCompatActivity {

    Button Assesment,Helpline;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);




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


}
