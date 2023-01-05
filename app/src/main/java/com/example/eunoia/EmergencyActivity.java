package com.example.eunoia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmergencyActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmergencyActivity extends Fragment {
    TextView textview;
    public EmergencyActivity() {
        // Required empty public constructor
    }


    public static EmergencyActivity newInstance(String param1, String param2) {
        EmergencyActivity fragment = new EmergencyActivity();
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
        View v = inflater.inflate(R.layout.fragment_emergency_activity, container, false);
        textview = v.findViewById(R.id.TVContact1);
        Bundle bundle = this.getArguments();
        String data = bundle.getString("key");
        textview.setText(data);
        return v;
    }
}