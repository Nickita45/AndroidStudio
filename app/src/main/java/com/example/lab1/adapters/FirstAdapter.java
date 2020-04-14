package com.example.lab1.adapters;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab1.R;

import java.util.ArrayList;
import java.util.Date;

public class FirstAdapter extends BaseAdapter {
    ArrayList<String> list;
    Context context;
    public FirstAdapter(ArrayList<String> list,Context context) {
        this.list = list;
        this.context=context;
    }
    // Возвращает количество элементов в списке

    @Override
    public int getCount() {
        return list.size();
    }


    // Должен вернуть элемент данных, которые указаны в элементе списка position
    @Override
    public Object getItem(int position) {
        return null;
    }

    // Должен вернуть id элемента данных, которые указаны в элементе списка position
    @Override
    public long getItemId(int position) {
        return 0;
    }
// Должен возвращать View пункта списка
    // В данном методе мы должны из
    // layout-ресурса создать View, заполнить его данными и отдать списку

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        // 2. С помощью метода inflate мы преобразовали xml-разметку
        // в контейнер View, который содержит внутри себя элементы из xml-разметки
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.events_list_icons, parent, false);
        }
        // 3. С помощью метода findViewById() получаем ссылку на два TextView внутри
        // нашего созданного view
        TextView name = (TextView) view.findViewById(R.id.textViewName);
        TextView text = (TextView) view.findViewById(R.id.textViewDescription);
        ImageView img = (ImageView) view.findViewById(R.id.imageView2);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        String currentDateandTime = sdf.format(new Date());

        name.setText(list.get(position));
        text.setText(currentDateandTime);
        img.setImageResource(R.drawable.images2);

        return view;
    }



}
