package com.example.eunoia.Habit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eunoia.R;


import java.text.DateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewHabit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewHabit extends Fragment {

    private CheckBox breakfastCB,lunchCB,dinnerCB;
    private EditText hoursET;
    private String username,hours,breakfast,lunch,dinner,currentDate;
    private Button save;
    private HabitDB tdb;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewHabit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewHabit.
     */
    // TODO: Rename and change types and number of parameters
    public static NewHabit newInstance(String param1, String param2) {
        NewHabit fragment = new NewHabit();
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
        return inflater.inflate(R.layout.fragment_new_habit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        breakfastCB = view.findViewById(R.id.breakfast);
        lunchCB = view.findViewById(R.id.lunch);
        dinnerCB = view.findViewById(R.id.dinner);
        hoursET = view.findViewById(R.id.hours);
        save = view.findViewById(R.id.save);


        tdb = new HabitDB(getActivity());
        username = this.getArguments().getString("username");

        Calendar calendar = Calendar.getInstance();
        currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hours = hoursET.getText().toString();
                if(breakfastCB.isChecked()){
                    breakfast = "Taken";
                }else{
                    breakfast = "Skip";
                }
                if(lunchCB.isChecked()){
                    lunch = "Taken";
                }else{
                    lunch = "Skip";
                }
                if(dinnerCB.isChecked()){
                    dinner = "Taken";
                }else{
                    dinner = "Skip";
                }
                if(hours.isEmpty()){
                    Toast.makeText(getActivity(), "Please enter the hour", Toast.LENGTH_SHORT).show();
                }
                else if(Integer.parseInt(hours)>=24){
                    Toast.makeText(getActivity(), "Please enter a suitable hour", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean insert = tdb.insertData(username, hours, breakfast, lunch, dinner, currentDate);
                    Toast.makeText(getActivity(), "Habit Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}