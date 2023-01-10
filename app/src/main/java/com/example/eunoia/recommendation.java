package com.example.eunoia;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class recommendation extends Fragment {
@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
    Button BtnMusic = view.findViewById(R.id.BtnMusic);
    View.OnClickListener OCLMusic = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Navigation.findNavController(view).navigate(R.id.desBreathing_exercise);
        }
    };
    BtnMusic.setOnClickListener(OCLMusic);

    Button BtnYoga = view.findViewById(R.id.BtnYoga);
    View.OnClickListener OCLYoga = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Navigation.findNavController(view).navigate(R.id.action_recommendation_to_yoga);
        }
    };
    BtnYoga.setOnClickListener(OCLYoga);

    Button BtnBreathing = view.findViewById(R.id.BtnBreathing);
    View.OnClickListener OCLBreathing = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            Navigation.findNavController(view).navigate(R.id.action_recommendation_to_breathing_exercise2);
        }
    };
    BtnYoga.setOnClickListener(OCLBreathing);

}

}