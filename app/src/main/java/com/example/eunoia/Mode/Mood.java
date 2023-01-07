package com.example.eunoia.Mode;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eunoia.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Mood#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Mood extends Fragment {

    MoodDB tdb;
    ArrayList<String> listTitle;
    ArrayAdapter adapter;
    String username;

    ListView moods;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Mood() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Mood.
     */
    // TODO: Rename and change types and number of parameters
    public static Mood newInstance(String param1, String param2) {
        Mood fragment = new Mood();
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
        return inflater.inflate(R.layout.fragment_mood, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        username = this.getArguments().getString("username");
        Button addMood = view.findViewById(R.id.addMood);
        View.OnClickListener OCLadd =new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                Fragment newMood = new NewMood();
                newMood.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, newMood).addToBackStack(null).commit();
            }
        };
        addMood.setOnClickListener(OCLadd);

        tdb = new MoodDB(getActivity());

        listTitle = new ArrayList<>();
        moods = view.findViewById(R.id.ListView);

        viewData(username);

        moods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = moods.getItemAtPosition(i).toString();
            }
        });
    }
    private void viewData(String name) {
        Cursor cursor = tdb.viewData(name);
        if(cursor.getCount()==0){
            Toast.makeText(getActivity(),"No data to show", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                listTitle.add(String.format("     %s\n     %s\n     %s\n     %s\n     %s",cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
            }

            adapter = new ArrayAdapter(getActivity(), R.layout.kuro, listTitle);
            moods.setAdapter(adapter);
        }
    }

}