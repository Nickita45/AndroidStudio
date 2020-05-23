package com.example.lab1.adapters;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.lab1.DBHelper;
import com.example.lab1.R;
import com.example.lab1.Table_Activity;
import com.example.lab1.entities.TableEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class TableTimeAdapter extends BaseAdapter {

    Context context;
    ArrayList<TableEntity> list;
    Calendar dateAndTime= Calendar.getInstance();
    TextView dateView;
    DBHelper dbHelper;
    public TableTimeAdapter(Context context, ArrayList<TableEntity> list) {
        this.list=list;
        this.context=context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // 2. С помощью метода inflate мы преобразовали xml-разметку
        // в контейнер View, который содержит внутри себя элементы из xml-разметки
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_gridview_table1, parent, false);
            /*dateView = (TextView) view.findViewById(R.id.textView_DateTableItem);
            TextView name = (TextView) view.findViewById(R.id.textView_NameTableItem);
            setDate(dateView);
            name.setText(list.get(position).name);
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", list.get(position).name);
            String data=DateUtils.formatDateTime(context,
                    dateAndTime.getTimeInMillis(),
                    DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                            | DateUtils.FORMAT_SHOW_TIME);
            contentValues.put("datetable", data);
            db.insert("mytable",null,contentValues);*/
        }
        dateView = (TextView) view.findViewById(R.id.textView_DateTableItem);
        TextView name = (TextView) view.findViewById(R.id.textView_NameTableItem);
        TextView description = (TextView) view.findViewById(R.id.textView_DescriptionTableItem);
        TextView nameOriginal = (TextView) view.findViewById(R.id.textView_NameOriginalTableItem);
        //Log.d("AAAA",""+list.get(position).id);
        name.setText(""+list.get(position).id);
        description.setText(list.get(position).description);
        nameOriginal.setText(list.get(position).name);
        dateView.setText(list.get(position).date);
        //setInitialDateTime();
        return view;
    }
    private void setInitialDateTime() {

        dateView.setText(DateUtils.formatDateTime(context,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }
    public void setDate(View v) {
        new DatePickerDialog(context, d,
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
