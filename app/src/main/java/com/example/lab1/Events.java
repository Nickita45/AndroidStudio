package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.lab1.adapters.FirstAdapter;

import java.util.ArrayList;

public class Events extends AppCompatActivity {
    String[] name = {"Vasya","Stas","Nikola"};
    String[] description = {"Hello","I am fine","omg"};
    Integer[] images = {R.drawable.moba_risotto,R.drawable.images,R.drawable.images2};
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentlayout);
        list = new ArrayList<>();
        final ListView lvMain = (ListView) findViewById(R.id.lvMain);
        final FirstAdapter adapter = new FirstAdapter(list, this);
        lvMain.setAdapter(adapter);



        //final ArrayAdapter adapter = new ArrayAdapter(this,
         //       android.R.layout.simple_list_item_1, names);

        Button add = (Button) findViewById(R.id.events_button_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Кнопка "Добавить"
                list.add("Stas");
                adapter.notifyDataSetChanged();
            }
        });

        Button delete = (Button) findViewById(R.id.events_button_remove);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Кнопка "Удалить"
                if (!list.isEmpty()) {
                    list.remove(list.size()-1);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                {
                    FragmentDetailed fragmentDetailed = (FragmentDetailed)getSupportFragmentManager().findFragmentById(R.id.fragment_detailed);
                    fragmentDetailed.setFragment(view,position,list);
                }
                else{
                    Intent intent = new Intent(Events.this, EventsIntent.class);
                    intent.putExtra("Name", list.get(position));
                    startActivity(intent);
                }
            }
        });
        }
}
