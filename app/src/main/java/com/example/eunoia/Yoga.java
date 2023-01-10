package com.example.eunoia;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Yoga#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Yoga extends Fragment {
     Button BtnYoga1,BtnYoga2,BtnYoga3;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Yoga() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Yoga.
     */
    // TODO: Rename and change types and number of parameters
    public static Yoga newInstance(String param1, String param2) {
        Yoga fragment = new Yoga();
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
        return inflater.inflate(R.layout.fragment_yoga, container, false);
    }
    @Override
    public void onViewCreated( View view, @Nullable Bundle savedInstanceState) {
        //Calling YogaType1 Fragment from Yoga Fragment
        BtnYoga1 = view.findViewById(R.id.BtnYoga1);
        View.OnClickListener OCLYoga1 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.DesyogaType1);
            }
        };
        BtnYoga1.setOnClickListener(OCLYoga1);

        //Calling YogaType2 Fragment from Yoga Fragment
        BtnYoga2 = view.findViewById(R.id.BtnYoga2);
        View.OnClickListener OCLYoga2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.DesyogaType2);
            }
        };
        BtnYoga2.setOnClickListener(OCLYoga2);

        //Calling YogaType3 Fragment from Yoga Fragment
        BtnYoga3 = view.findViewById(R.id.BtnYoga3);
        View.OnClickListener OCLYoga3 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.DesyogaType3);
            }
        };
        BtnYoga3.setOnClickListener(OCLYoga3);

    }
}