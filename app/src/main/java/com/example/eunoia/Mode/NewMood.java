package com.example.eunoia.Mode;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tracker.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewMood#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewMood extends Fragment {

    private SeekBar seekBar;
    private TextView moodInd;
    private Button submit;
    private String mainMood="", subMood="", username,currentDate,currentTime;
    private CheckBox c1,c2,c3,c4,c5,c6;
    private MoodDB tdb;
    private EditText description;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewMood() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewMood.
     */
    // TODO: Rename and change types and number of parameters
    public static NewMood newInstance(String param1, String param2) {
        NewMood fragment = new NewMood();
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
        return inflater.inflate(R.layout.fragment_new_mood, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        seekBar = view.findViewById(R.id.seekBar);
        moodInd = view.findViewById(R.id.moodInd);
        submit = view.findViewById(R.id.submit);
        description = view.findViewById(R.id.description);

        tdb = new MoodDB(getActivity());
        username = this.getArguments().getString("username");

        c1 = view.findViewById(R.id.Bored);
        c2 = view.findViewById(R.id.Anxious);
        c3 = view.findViewById(R.id.Tired);
        c4 = view.findViewById(R.id.Excited);
        c5 = view.findViewById(R.id.Angry);
        c6 = view.findViewById(R.id.Relaxed);

        Calendar calendar = Calendar.getInstance();
        currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        currentTime = sdf.format(calendar.getTime());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i<=33){
                    moodInd.setText("Sad");
                    mainMood = "Sad";
                }
                else if(i>33 && i<=66){
                    moodInd.setText("Neutral");
                    mainMood = "Neutral";
                }
                else if(i>66 && i<=100){
                    moodInd.setText("Happy");
                    mainMood = "Happy";
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(c1.isChecked()){
                    subMood = subMood + "Bored ";
                }
                else{
                    subMood = subMood.replace("Bored ","");
                }

                if(c2.isChecked()){
                    subMood = subMood + "Anxious ";
                }
                else{
                    subMood = subMood.replace("Anxious ","");
                }
                if(c3.isChecked()){
                    subMood = subMood + "Tired ";
                }
                else{
                    subMood = subMood.replace("Tired ","");
                }
                if(c4.isChecked()){
                    subMood = subMood + "Excited ";
                }
                else{
                    subMood = subMood.replace("Excited ","");
                }
                if(c5.isChecked()){
                    subMood = subMood + "Angry ";
                }
                else{
                    subMood = subMood.replace("Angry ","");
                }
                if(c6.isChecked()){
                    subMood = subMood + "Relaxed ";
                }
                else{
                    String replace = subMood.replace("Angry ","");
                }

                if(mainMood.isEmpty()||description.getText().toString().isEmpty()||subMood.isEmpty()){
                    Toast.makeText(getActivity(), "Please fill in all the required fields", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean insert = tdb.insertData(username, mainMood, subMood, description.getText().toString(), currentDate, currentTime);
                    Toast.makeText(getActivity(), "Mood Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}