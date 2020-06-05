package com.example.lab1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lab1.R;
import com.example.lab1.entities.Classmates;
import com.example.lab1.entities.TableEntity;

import java.util.ArrayList;
import java.util.Calendar;

public class Lab6Adapter extends BaseAdapter {
    Context context;
    ArrayList<Classmates> list;
    Calendar dateAndTime= Calendar.getInstance();
    public Lab6Adapter(Context context, ArrayList<Classmates> arrayList){
        this.context=context;
        this.list=arrayList;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_classmates, parent, false);
        }
        TextView name = (TextView) view.findViewById(R.id.textView_classmates_name);
        TextView id = (TextView) view.findViewById(R.id.textView_classmates_id);
        TextView date = (TextView) view.findViewById(R.id.textView_classmates_data);
        name.setText(list.get(position).fio);
        id.setText(""+list.get(position).id);
        date.setText(list.get(position).date);
        return view;
    }
}
