package com.example.eunoia.Habit;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tracker.Mood.NewMood;
import com.example.tracker.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Habit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Habit extends Fragment {

    HabitDB tdb;
    ArrayList<String> listTitle;
    ArrayAdapter adapter;
    String username;

    ListView habits;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Habit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Habit.
     */
    // TODO: Rename and change types and number of parameters
    public static Habit newInstance(String param1, String param2) {
        Habit fragment = new Habit();
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
        return inflater.inflate(R.layout.fragment_habit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        username = this.getArguments().getString("username");
        Button addMood = view.findViewById(R.id.addEntry);
        View.OnClickListener OCLadd = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                Fragment newHabit = new NewHabit();
                newHabit.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, newHabit).addToBackStack(null).commit();
            }
        };
        addMood.setOnClickListener(OCLadd);

        tdb = new HabitDB(getActivity());

        listTitle = new ArrayList<>();
        habits = view.findViewById(R.id.ListView);

        viewData(username);

        habits.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = habits.getItemAtPosition(i).toString();
            }
        });
    }
    private void viewData(String username) {
        Cursor cursor = tdb.viewData(username);
        if(cursor.getCount()==0){
            Toast.makeText(getActivity(),"No data to show", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                listTitle.add(String.format("     Hours of sleep today: %s\n" +
                        "     Breakfast : %s\n     Lunch : %s\n     Dinner : %s\n" +
                        "     %s",cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
            }
            adapter = new ArrayAdapter(getActivity(), R.layout.kuro, listTitle);
            habits.setAdapter(adapter);
        }
    }
}