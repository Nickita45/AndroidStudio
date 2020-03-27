package com.example.lab1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomListView extends ArrayAdapter<String> {
    private String[] names;
    private String[] description;
    private Integer[] imgid;
    private Activity context;

    public CustomListView(Activity context, String[] names, String[] description,Integer[] imgid) {
        super(context, R.layout.events_list_icons,names);
        this.names=names;
        this.description=description;
        this.imgid=imgid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r=convertView;
        ViewHolder viewHolder = null;
        if(r==null)
        {
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.events_list_icons,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder)r.getTag();
        }
        viewHolder.imv1.setImageResource(imgid[position]);
        viewHolder.tv1.setText(names[position]);
        viewHolder.tv1.setText(description[position]);

        return r;
    }
    class ViewHolder {
        TextView tv1;
        TextView tv2;
        ImageView imv1;

        ViewHolder(View v)
        {
            tv1 =v.findViewById(R.id.textViewName);
            tv2 =v.findViewById(R.id.textViewDescription);
            imv1 =v.findViewById(R.id.imageView2);

        }
    }
}
