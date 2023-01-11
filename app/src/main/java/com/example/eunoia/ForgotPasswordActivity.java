package com.example.eunoia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText username_reset;
    Button btnReset;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        username_reset = findViewById(R.id.username_reset);
        btnReset = findViewById(R.id.btnReset);

        dbHelper = new DBHelper(this);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username_reset.getText().toString();
                Boolean checkuser = dbHelper.checkusername(user);

                if(checkuser == true){
                    Intent intent = new Intent(getApplicationContext(), ResetActivity.class);
                    intent.putExtra("username_reset", user);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ForgotPasswordActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
