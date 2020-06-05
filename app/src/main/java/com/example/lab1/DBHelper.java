package com.example.lab1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DBHelper extends SQLiteOpenHelper {
    public String mytable;
    public String classmates;
    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("EEEE", "--- onCreate database ---");
        // создаем таблицу с полями

        db.execSQL("create table mytable ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "description text,"
                + "datetable text"+");");
        db.execSQL("create table classmates ("
                + "id integer primary key autoincrement,"
                + "fio text,"
                + "datetable text"+");");
    }
    public void addOneClassmates(SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",0);
        contentValues.put("fio","P.F.D");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        contentValues.put("datetable",timeStamp);

        db.insert("classmates",null,contentValues);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table classmates");
        Log.d("AAa","DB WORK");
        db.execSQL("create table classmates ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "secondname text,"
                + "otchestvo text,"
                + "datetable text"+");");
    }

}