package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity {
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

      //  if(getSupportActionBar()!=null)
      //      getSupportActionBar().hide();
        Button button = (Button) findViewById(R.id.button2);
       // final
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this,MainActivity.class);
                EditText editText = (EditText) findViewById(R.id.sendData);
                //  Intent i = new Intent(Main2Activity.this,MainActivity.class);
                i.putExtra("Data",editText.getText().toString());
                startActivity(i);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i = new Intent(Main2Activity.this,Calls.class);
        Intent intentEvents = new Intent(Main2Activity.this,Events.class);

        switch (item.getItemId())
        {
            case R.id.calls:
                startActivity(i);
                break;
                
            case R.id.events:
                startActivity(intentEvents);
                //Toast.makeText(this, "Events!", Toast.LENGTH_SHORT).show();
                break;

                default:
                    Toast.makeText(this, "Work in progress!", Toast.LENGTH_SHORT).show();
                    break;
        }
        return super.onOptionsItemSelected(item);
    }
}
