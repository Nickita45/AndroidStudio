package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Debug;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lab1.adapters.FirstAdapter;
import com.example.lab1.adapters.TableTimeAdapter;
import com.example.lab1.dialogs.GridViewDialog;
import com.example.lab1.dialogs.TableDeleteDialog;
import com.example.lab1.entities.TableEntity;

import java.util.ArrayList;
import java.util.Calendar;

public class Table_Activity extends AppCompatActivity {
    ArrayList<TableEntity> list;
    Calendar dateAndTime = Calendar.getInstance();
    EditText enteredDate;
    TextView view_ENTERED_DATE;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        dbHelper = new DBHelper(this);


        SQLiteDatabase db = dbHelper.getWritableDatabase();

        list = new ArrayList<>();
        Cursor usercurs = db.rawQuery("select * from mytable",null);
        while(usercurs.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            Log.d("SQL",usercurs.getString(0)+" "+usercurs.getString(3));
            list.add(new TableEntity(usercurs.getInt(0),usercurs.getString(1),usercurs.getString(3)));
        }

        final ListView lvMain = (ListView) findViewById(R.id.list_table);
        final TableTimeAdapter adapter = new TableTimeAdapter(this, list);
        lvMain.setAdapter(adapter);
        enteredDate = (EditText) findViewById(R.id.editText_table_date);

        //setInitialDateTime();
        Button add = (Button) findViewById(R.id.button_table_add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db1 = dbHelper.getWritableDatabase();
                // Кнопка "Добавить"
                EditText enteredName = (EditText) findViewById(R.id.editText_table_name);
                //String view_ENTERED_DATE = "";
                setInitialDateTime();
                setDate(enteredDate);


                String sendString = enteredDate.getText().toString();
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", enteredName.getText().toString());
                contentValues.put("datetable", sendString);
                db1.insert("mytable",null,contentValues);


                list.add(new TableEntity(enteredName.getText().toString(), sendString));
                adapter.notifyDataSetChanged();
            }
        });
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final TextView name = (TextView) view.findViewById(R.id.textView_NameTableItem);
                AlertDialog.Builder builder = new AlertDialog.Builder(Table_Activity.this);
                builder.setTitle("Удаление");
                builder.setMessage("Вы точно хотите удалить? ");
                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (!list.isEmpty()) {

                            list.remove(position);
                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                            int pos = Integer.valueOf(name.getText().toString());
                            db.execSQL("DELETE FROM mytable WHERE id = "+pos);

                            adapter.notifyDataSetChanged();
                        }
                    }
                });
                builder.setNegativeButton("Нет",null);
                builder.create().show();
               // TextView text = (TextView) view.findViewById(R.id.textView_itemGridView);
                /*Log.d("BBB","AAAAA");
                TableDeleteDialog dialog = new TableDeleteDialog(position);
                dialog.show(getSupportFragmentManager(),"dddd");
                if(dialog.getDeletedItem() != -1)
                {
                    if (!list.isEmpty()) {
                        list.remove(dialog.getDeletedItem());
                        adapter.notifyDataSetChanged();
                    }
                }*/
            }
        });
    }


    private void setInitialDateTime() {

        enteredDate.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));

    }
    public void setDate(View v) {
        new DatePickerDialog(Table_Activity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();

    }
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };
}
