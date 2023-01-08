package com.example.eunoia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eunoia.ToDoList.Adapter.ToDoAdapter;
import com.example.eunoia.ToDoList.AddNewTask;
import com.example.eunoia.ToDoList.Model.ToDoModel;
import com.example.eunoia.ToDoList.OnDialogCloseListener;
import com.example.eunoia.ToDoList.RecyclerViewTouchHelper;
import com.example.eunoia.ToDoList.Utils.DataBaseHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateNew extends AppCompatActivity implements OnDialogCloseListener {

    private RecyclerView RV;
    private Button bt;
    private DataBaseHelper myDB;
    private static String username;

    private List<ToDoModel> mList;
    private ToDoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);

        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");

        RV = findViewById(R.id.recycle);
        bt = findViewById(R.id.addTask);
        myDB = new DataBaseHelper(CreateNew.this);
        mList = new ArrayList<>();
        adapter = new ToDoAdapter(myDB,CreateNew.this);

        mList = myDB.getAll(username);
        Collections.reverse(mList);
        adapter.setTask(mList);

        RV.setHasFixedSize(true);
        RV.setLayoutManager(new LinearLayoutManager(this));
        RV.setAdapter(adapter);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                AddNewTask.newInstance(username).show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(RV);
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList = myDB.getAll(username);
        Collections.reverse(mList);
        adapter.setTask(mList);
        adapter.notifyDataSetChanged();
    }
}