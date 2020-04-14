package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.lab1.adapters.GridViewAnimalAdapter;
import com.example.lab1.adapters.GridViewElementAdapter;

public class AsyncAnimalsActivity extends AppCompatActivity {
    GridView gvMain;
    GridViewAnimalAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_animals);
        gvMain = (GridView) findViewById(R.id.gridview_animals);
        adapter = new GridViewAnimalAdapter(this);
        gvMain.setAdapter(adapter);

        gvMain.setNumColumns(3);
    }
}
