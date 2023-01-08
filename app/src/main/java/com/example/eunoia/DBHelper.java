package com.example.eunoia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DBHelper extends SQLiteOpenHelper {

    static String name = "database";
    static int version = 1;

    String createTableUser = "CREATE TABLE if not exists `user` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `email` TEXT," +
            "`name` TEXT, `password` TEXT, `dob` TEXT, `gender` TEXT)";

    public DBHelper(Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createTableUser);
    }

    public void insertUser(ContentValues contentValues){
        getWritableDatabase().insert("user", "", contentValues);
    }

    public boolean isLoginValid(String email, String password){
        String sql = "Select count(*) from user where email = '"+email+"' and password='"+password+"'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        statement.close();

        if(l == 1){
            return true;
        }

        else{
            return false;
        }
    }

    public Boolean checkusername(String email){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from user where email = ?", new String[] {email});
        if (cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public String returnEmail(String email){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from user where email = ?", new String[] {email});
        return email;
    }

    public String returnName(String name){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from user where email = ?", new String[] {name});
        return name;
    }

    public String returnPassword(String password){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from user where email = ?", new String[] {password});
        return password;
    }

    public String returnDOB(String dob){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from user where email = ?", new String[] {dob});
        return dob;
    }

    public String returnGender(String gender){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from user where email = ?", new String[] {gender});
        return gender;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor getUsername(String email){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from user where email = ?", new String[] {email});
        return cursor;
    }

}