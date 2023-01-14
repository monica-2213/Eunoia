package com.example.eunoia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManageEmergencyContactsActivity extends AppCompatActivity {

    EditText editName1, editNum1, editName2, editNum2, editName3, editNum3;
    Button Update,Back;
    private static final String SHARED_PREF_NAME= "Eunoia";
    private static final String CONTACT_NAME= "EmergencyName";
    private static final String CONTACT_NUMBER= "EmergencyNumber";
    private static final String CONTACT_NAME2= "EmergencyName2";
    private static final String CONTACT_NUMBER2= "EmergencyNumber2";
    private static final String CONTACT_NAME3= "EmergencyName3";
    private static final String CONTACT_NUMBER3= "EmergencyNumber3";
    String Name1,Name2,Name3,Num1,Num2,Num3;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_emergency_contact);



        Bundle extras = getIntent().getExtras();

        if(extras!=null){
            Name1 = extras.getString("Name1");
            Num1 = extras.getString("Num1");
            Name2 = extras.getString("Name2");
            Num2 = extras.getString("Num2");
            Name3 = extras.getString("Name3");
            Num3 = extras.getString("Num3");
        }else {
        }


        editName1 = findViewById(R.id.emergencyContactName);
        editName1.setText(Name1);

        editNum1 = findViewById(R.id.emergencyContactNumber);
        editNum1.setText(Num1);

        editName2 = findViewById(R.id.emergencyContactName2);
        editName2.setText(Name2);

        editNum2 = findViewById(R.id.emergencyContactNumber2);
        editNum2.setText(Num2);

        editName3 = findViewById(R.id.emergencyContactName3);
        editName3.setText(Name3);

        editNum3 = findViewById(R.id.emergencyContactNumber3);
        editNum3.setText(Num3);

        Update = findViewById(R.id.BTNUpdate);
        Back = findViewById(R.id.ToDisplay);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

        Update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String EmName1 = editName1.getText().toString();
                String EmNum1 = editNum1.getText().toString();
                String EmName2 = editName2.getText().toString();
                String EmNum2 = editNum2.getText().toString();
                String EmName3 = editName3.getText().toString();
                String EmNum3 = editNum3.getText().toString();


                SharedPreferences.Editor editor = sharedPreferences.edit();

                if(EmName1.isEmpty()){
                    editor.putString(CONTACT_NAME,Name1);
                    editor.putString(CONTACT_NUMBER,Num1);
                }else {
                    editor.putString(CONTACT_NAME, editName1.getText().toString());
                    editor.putString(CONTACT_NUMBER, editNum1.getText().toString());
                }

                if(EmName2.isEmpty()){
                    editor.putString(CONTACT_NAME2,Name2);
                    editor.putString(CONTACT_NUMBER2,Num2);
                }else {
                    editor.putString(CONTACT_NAME2, editName2.getText().toString());
                    editor.putString(CONTACT_NUMBER2, editNum2.getText().toString());
                }
                if(EmName3.isEmpty()){
                    editor.putString(CONTACT_NAME3,Name3);
                    editor.putString(CONTACT_NUMBER3,Num3);
                }else {
                    editor.putString(CONTACT_NAME3, editName3.getText().toString());
                    editor.putString(CONTACT_NUMBER3, editNum3.getText().toString());
                }
                editor.commit();
                Intent intent1 = new Intent(ManageEmergencyContactsActivity.this,EmergencyActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        Back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageEmergencyContactsActivity.this,EmergencyActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
    }
}
