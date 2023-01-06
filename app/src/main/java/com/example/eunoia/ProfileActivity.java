package com.example.eunoia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    EditText email, name, password, dateOfBirth, gender;
    Button btnEdit;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");

        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        gender = findViewById(R.id.gender);

        String emailUser = email.getText().toString();
        String emailDisplay = dbHelper.returnEmail(emailUser);

        email.setText(emailDisplay);
    }

}