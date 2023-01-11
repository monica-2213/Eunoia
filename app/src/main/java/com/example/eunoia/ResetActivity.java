package com.example.eunoia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetActivity extends AppCompatActivity {

    TextView username_reset_text;
    EditText passwordReset;
    Button btnConfirm;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        username_reset_text = findViewById(R.id.username_reset_text);
        passwordReset = findViewById(R.id.passwordReset);
        btnConfirm = findViewById(R.id.btnConfirm);
        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        username_reset_text.setText(intent.getStringExtra("username_reset"));
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username_reset_text.getText().toString();
                String password = passwordReset.getText().toString();

                Boolean checkPasswordUpdate = dbHelper.updatePassword(user, password);

                if(checkPasswordUpdate == true){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(ResetActivity.this, "Password updated successfully! Login to continue", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ResetActivity.this, "Error! Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
