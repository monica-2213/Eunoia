package com.example.eunoiaupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.util.Patterns;

public class RegisterActivity extends AppCompatActivity {

    EditText email, name, password, dateOfBirth, repassword;
    RadioGroup gender;
    Button btnSignUp, btnLogin;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        gender = findViewById(R.id.gender);
        btnSignUp = findViewById(R.id.btnSignup);
        btnLogin = findViewById(R.id.btnLogin);
        dbHelper = new DBHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailUser = email.getText().toString();
                String nameUser = name.getText().toString();
                String passwordUser = password.getText().toString();
                String repasswordUser = repassword.getText().toString();
                String DOBUser = dateOfBirth.getText().toString();
                RadioButton checkedBtn = findViewById(gender.getCheckedRadioButtonId());
                String genderUser = checkedBtn.getText().toString();

                if(emailUser.equals("") || nameUser.equals("") || passwordUser.equals("") || repasswordUser.equals("") || DOBUser.equals("") || genderUser.equals("")){
                    Toast.makeText(RegisterActivity.this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
                }else{
                    if(!repasswordUser.equals(passwordUser)){
                        Toast.makeText(RegisterActivity.this, "Passwords do not match. Try again!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()){
                            Boolean checkuser = dbHelper.checkusername(emailUser);
                            if(checkuser == false){
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("email", emailUser);
                                contentValues.put("name", nameUser);
                                contentValues.put("password", passwordUser);
                                contentValues.put("dob", DOBUser);
                                contentValues.put("gender", genderUser);

                                dbHelper.insertUser(contentValues);
                                Toast.makeText(RegisterActivity.this, "Registration Successful! Login to Continue.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else{
                                Toast.makeText(RegisterActivity.this, "Email already exists! Try a different email or proceed to login.", Toast.LENGTH_SHORT).show();
                            }
                        } else{
                            Toast.makeText(RegisterActivity.this, "Invalid Email!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

