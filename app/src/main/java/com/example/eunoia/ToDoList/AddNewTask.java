package com.example.eunoia.ToDoList;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eunoia.R;
import com.example.eunoia.ToDoList.Model.ToDoModel;
import com.example.eunoia.ToDoList.Utils.DataBaseHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Locale;

public class AddNewTask extends BottomSheetDialogFragment {

    public static final String TAG = "AddNewTask";

    private EditText mEditText1,mEditText2;
    private Button mSaveButton,timeButton;
    private static String username,taskDesc,taskName;

    private CheckBox t;
    private DataBaseHelper myDB;
    private int hour, minute;
    private int check = 0;


    public static AddNewTask newInstance(String name){
        username = name;
        return new AddNewTask();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_new_task,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mEditText1 = view.findViewById(R.id.TaskName);
        mEditText2 = view.findViewById(R.id.TaskDesc);
        mSaveButton = view.findViewById(R.id.Save);

        myDB = new DataBaseHelper(getActivity());

        boolean isUpdate = false;

        Bundle bundle = getArguments();
        if(bundle!=null){
            isUpdate = true;
            username = bundle.getString("username");
            taskName = bundle.getString("task");
            taskDesc = bundle.getString("tdesc");
            mEditText1.setText(taskName);
            mEditText2.setText(taskDesc);

        }
        boolean finalIsUpdate = isUpdate;
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = mEditText1.getText().toString();
                String text2 = mEditText2.getText().toString();

                if(finalIsUpdate){
                    myDB.updateTask(bundle.getInt("id"),text,text2);
                }else{
                    ToDoModel item = new ToDoModel();
                    item.setUsername(username);
                    item.setTaskName(text);
                    item.setTaskDesc(text2);
                    item.setStatus(0);
                    myDB.insertTask(item);
                }
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if(activity instanceof OnDialogCloseListener){
            ((OnDialogCloseListener)activity).onDialogClose(dialog);
        }
    }
}
