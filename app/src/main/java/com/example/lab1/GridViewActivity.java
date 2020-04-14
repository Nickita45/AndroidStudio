package com.example.lab1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab1.adapters.GridViewElementAdapter;
import com.example.lab1.dialogs.GridViewDialog;


public class GridViewActivity extends AppCompatActivity {
    GridView gvMain;
    int N=20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_lab3);


        gvMain = (GridView) findViewById(R.id.gridview);
        gvMain.setAdapter(new GridViewElementAdapter(this,21));
        gvMain.setNumColumns(3);
        gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView text = (TextView) view.findViewById(R.id.textView_itemGridView);
                GridViewDialog dialog = new GridViewDialog(Integer.parseInt(text.getText().toString()));
                dialog.show(getSupportFragmentManager(),"dddd");
            }
        });
       // gvMain.getOnItemClickListener(gridviewOnItemClickListener);
    }


}
