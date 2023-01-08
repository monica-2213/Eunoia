package com.example.eunoia.ToDoList.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.example.eunoia.ToDoList.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    private static final String DATABASE_NAME = "TODO_DATABASE";
    private static final String TABLE_NAME = "TODO_TABLE";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "USERNAME";
    private static final String COL_3 = "TITLE";
    private static final String COL_4 = "TDESC";
    private static final String COL_5 = "STATUS";
    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT , TITLE TEXT , TDESC TEXT , STATUS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertTask(ToDoModel model){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, model.getUsername());
        values.put(COL_3, model.getTaskName());
        values.put(COL_4, model.getTaskDesc());
        values.put(COL_5, 0);
        db.insert(TABLE_NAME,null,values);
    }

    public void updateTask(int id, String title, String tDesc){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_3, title);
        values.put(COL_4,tDesc);
        db.update(TABLE_NAME,values,"ID=?", new String[]{String.valueOf(id)});
    }

    public void updateStatus(int id, int status){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_5, status);
        db.update(TABLE_NAME,values,"ID=?", new String[]{String.valueOf(id)});
    }

    public void deleteTask(int id){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete(TABLE_NAME,"ID=?", new String[]{String.valueOf(id)});
    }

    public List<ToDoModel> getAll(String username){
        //y = Current user
        String y = username;
        String x;
        db = this.getWritableDatabase();
        Cursor cursor = null;
        List<ToDoModel> modelList = new ArrayList<>();
        db.beginTransaction();
        try{
            cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do{
                        //x = all users
                        x = cursor.getString(cursor.getColumnIndexOrThrow(COL_2));
                        ToDoModel task = new ToDoModel();
                        task.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_1)));
                        task.setTaskName(cursor.getString(cursor.getColumnIndexOrThrow(COL_3)));
                        task.setTaskDesc(cursor.getString(cursor.getColumnIndexOrThrow(COL_4)));
                        task.setStatus(cursor.getInt(cursor.getColumnIndexOrThrow(COL_5)));
                        if(TextUtils.equals(x, y)) {
                            modelList.add(task);
                        }
                    }while(cursor.moveToNext());
                }
            }
        }finally {
            db.endTransaction();
            cursor.close();
        }
        return modelList;
    }

}