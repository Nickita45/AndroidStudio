package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab1.receivers.MyReceiver;


public class Main2Activity extends AppCompatActivity {
    MyReceiver myReceiver = new MyReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

      //  if(getSupportActionBar()!=null)
      //      getSupportActionBar().hide();
        Button button = (Button) findViewById(R.id.button2);

        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
       this.registerReceiver(myReceiver, filter);
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
    protected void onDestroy() {
        super.onDestroy();
        // дерегистрируем (выключаем) BroadcastReceiver
        unregisterReceiver(myReceiver);
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
        Intent gridviewLab3 = new Intent(Main2Activity.this,GridViewActivity.class);
        Intent asyncLab4 = new Intent(Main2Activity.this,ThreatAsyncActivity.class);
        Intent asyncAnimalLab4 = new Intent(Main2Activity.this,AsyncAnimalsActivity.class);

        switch (item.getItemId())
        {
            case R.id.calls:
                startActivity(i);
                break;
                
            case R.id.events:
                startActivity(intentEvents);
                //Toast.makeText(this, "Events!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_gridview:
                startActivity(gridviewLab3);
                break;
            case R.id.item_async:
                startActivity(asyncLab4);
                break;
            case R.id.item_animal:
                startActivity(asyncAnimalLab4);
                break;

                default:
                    Toast.makeText(this, "Work in progress!", Toast.LENGTH_SHORT).show();
                    break;
        }
        return super.onOptionsItemSelected(item);
    }
}
