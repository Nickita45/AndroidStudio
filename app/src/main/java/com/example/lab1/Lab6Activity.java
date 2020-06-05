package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lab1.entities.Classmates;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class Lab6Activity extends AppCompatActivity {
    DBHelper dbHelper;
    ArrayList<Classmates> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab6);
        dbHelper = new DBHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
       // db.execSQL("drop table classmates");
       // dbHelper.addOneClassmates(db);
        dbHelper.onUpgrade(db,1,2);
        arrayList=UpdateValue();

        Button button_list = (Button) findViewById(R.id.button_lab6_list);
        Button button_add = (Button) findViewById(R.id.button_lab6_add);
        Button button_rename = (Button) findViewById(R.id.button_lab6_rename);
        Button button_delete = (Button) findViewById(R.id.button_lab6_delete);
       // ArrayList<Classmates> arrayList = new ArrayList<Classmates>();
        button_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList=UpdateValue();
                Intent i = new Intent(Lab6Activity.this,GridViewLab6.class);
                EditText editText = (EditText) findViewById(R.id.sendData);
                i.putExtra("Data",arrayList);
                startActivity(i);
            }
        });
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random r = new Random();
                String[] name= {"Nikita","John","Petya"};
                String[] secondname= {"Petrov","Sidorov","Ovcharenko"};
                String[] otchestvo= {"Stepanovich","Romanovich","Alexandrovich"};
                int index1= r.nextInt(3);
                int index2= r.nextInt(3);
                int index3= r.nextInt(3);
               /* String alphabet = "QWERTYUIOPASDFGHJKLZXCVBNM";
                for (int i = 0; i < 3; i++) {
                    fio+=alphabet.charAt(r.nextInt(alphabet.length()))+".";
                   // System.out.println(alphabet.charAt(r.nextInt(alphabet.length())));
                }*/
                ContentValues contentValues = new ContentValues();
              //  contentValues.put("id",0);
                contentValues.put("name",name[index1]);
                contentValues.put("secondname",secondname[index2]);
                contentValues.put("otchestvo",otchestvo[index3]);
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

                contentValues.put("datetable",timeStamp);
                db.insert("classmates",null,contentValues);

            }
        });
        button_rename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList=UpdateValue();
                ContentValues contentValues = new ContentValues();
                //  contentValues.put("id",0);
                contentValues.put("name","Ivan");
                contentValues.put("secondname","Ivanov");
                contentValues.put("otchestvo","Ivanov");

                String where = "id" + "=" + arrayList.get(arrayList.size()-1).id;
                db.update("classmates",contentValues,where,null);
            }
        });
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!arrayList.isEmpty()) {




                    db.execSQL("DELETE FROM classmates");


                }
            }
        });

    }
    public ArrayList<Classmates> UpdateValue()
    {
        ArrayList<Classmates> arrayList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor usercurs = db.rawQuery("select * from classmates",null);
        while(usercurs.moveToNext()){
            Log.d("SQL",usercurs.getString(0)+" "+usercurs.getString(1));
            arrayList.add(new Classmates(usercurs.getInt(0),usercurs.getString(1),usercurs.getString(2),usercurs.getString(3),usercurs.getString(4)));
        }
        return arrayList;
    }
}
