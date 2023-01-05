package com.example.eunoia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ManageEmergencyContactsActivity extends Fragment {

    EditText editText;
    Button button;



    public ManageEmergencyContactsActivity() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ManageEmergencyContactsActivity newInstance(String param1, String param2) {
        ManageEmergencyContactsActivity fragment = new ManageEmergencyContactsActivity();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_manage_emergency_contacts_activity, container, false);
        button = v.findViewById(R.id.BTNUpdate);
        editText = v.findViewById(R.id.EmergencyContactName);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key",editText.getText().toString());
                EmergencyActivity fragment = new EmergencyActivity();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.TVContact1,fragment).commit();

            }
        });
        return v;
    }
}