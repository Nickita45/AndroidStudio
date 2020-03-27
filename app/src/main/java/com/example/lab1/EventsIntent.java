package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EventsIntent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_events_intent);
        Button add = (Button) findViewById(R.id.button3);
        TextView textViewName = (TextView) findViewById(R.id.activityEventsIntentName);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            textViewName.setText(extras.getString("Name").toString());
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EventsIntent.this, Events.class);
                    startActivity(intent);
                    // Кнопка "Добавить"

                }
            });

           // FragmentDetailed fragmentDetailed = (FragmentDetailed) getSupportFragmentManager().findFragmentById(R.id.fragment_detailed);

        }
    }

}
