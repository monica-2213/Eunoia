package com.example.eunoia;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class breathing_exercise extends Fragment {
    Button BtnBreathe1, BtnBreathe2,BtnBreathe3;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public breathing_exercise() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment music.
     */
    // TODO: Rename and change types and number of parameters
    public static music newInstance(String param1, String param2) {
        music fragment = new music();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_breathing_exercise, container, false);
    }


    @Override
    public void onViewCreated( View view, @Nullable Bundle savedInstanceState) {
        //Calling BreathingType1 Fragment from Breathing_exercise Fragment
        BtnBreathe1 = view.findViewById(R.id.BtnBreathe1);
        View.OnClickListener OCLBreathe1 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.DesbreathingType1);
            }
        };
        BtnBreathe1.setOnClickListener(OCLBreathe1);

        //Calling BreathingType2 Fragment from Breathing_exercise Fragment
        BtnBreathe2= view.findViewById(R.id.BtnBreathe2);
        View.OnClickListener OCLBreathe2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.DesbreathingType2);
            }
        };
        BtnBreathe2.setOnClickListener(OCLBreathe2);

        //Calling BreathingType3 Fragment from Breathing_exercise Fragment
        BtnBreathe3 = view.findViewById(R.id.BtnBreathe3);
        View.OnClickListener OCLBreathe3 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.DesbreathingType3);
            }
        };
        BtnBreathe3.setOnClickListener(OCLBreathe3);

    }
    }