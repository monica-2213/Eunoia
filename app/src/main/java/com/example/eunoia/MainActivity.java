package com.example.eunoia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText email1, password1;
    Button btnLogin1, btnSignUp1;
    DBHelper dbHelper;
    String username;
    TextView btnForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email1 = findViewById(R.id.email1);
        password1 = findViewById(R.id.password1);
        btnLogin1 = findViewById(R.id.btnLogin1);
        btnSignUp1 = findViewById(R.id.btnSignup1);
        dbHelper = new DBHelper(this);

        btnLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailUser = email1.getText().toString();
                String passwordUser = password1.getText().toString();

                Cursor cursor = dbHelper.getUsername(emailUser);
                cursor.moveToNext();
                username = cursor.getString(2);
                cursor.close();


                if(dbHelper.isLoginValid(emailUser, passwordUser)) {
                    Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Invalid Credentials! Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignUp1.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                                              startActivity(intent);
                                          }
                                      }
        );
        
        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

    }
}
