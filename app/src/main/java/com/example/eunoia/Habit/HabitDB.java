package com.example.eunoia.Habit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HabitDB extends SQLiteOpenHelper {

    public static final String DBNAME = "HabitTrackerDB.db";
    public HabitDB(Context context){ super(context, "HabitTrackerDB.db",null,1);}
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table habitList(id INTEGER primary key autoincrement, username TEXT, sleepHours TEXT, breakfast TEXT, lunch TEXT, dinner TEXT, date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists moodList");
    }

    public Boolean insertData(String username, String sleepHours,String breakfast, String lunch, String dinner, String date){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("sleepHours", sleepHours);
        contentValues.put("breakfast",breakfast);
        contentValues.put("lunch",lunch);
        contentValues.put("dinner",dinner);
        contentValues.put("date",date);
        long result = MyDB.insert("habitList", null, contentValues);
        if(result == -1) return false;
        else
            return true;
    }

    public Cursor viewData(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from habitList where username = ?", new String[]{username});
        return cursor;
    }

}
