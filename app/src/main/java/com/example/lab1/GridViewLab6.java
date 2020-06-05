package com.example.lab1;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab1.adapters.Lab6Adapter;
import com.example.lab1.adapters.TableTimeAdapter;
import com.example.lab1.entities.Classmates;

import java.util.ArrayList;

public class GridViewLab6 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view_lab6);
        Bundle extras = getIntent().getExtras();
        final ListView lvMain = (ListView) findViewById(R.id.gridview_lab6);
        ArrayList<Classmates> nameValuePairs = (ArrayList<Classmates>) extras.getSerializable("Data");
        Log.d("BASE:",""+nameValuePairs);
        Lab6Adapter adapter = new Lab6Adapter(this, nameValuePairs);
        lvMain.setAdapter(adapter);
    }
}
