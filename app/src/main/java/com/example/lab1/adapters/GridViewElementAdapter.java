package com.example.lab1.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab1.R;

import java.util.Random;

public class GridViewElementAdapter extends BaseAdapter {
    private Context mContext;
    int[] colors = {Color.BLUE, Color.RED, Color.GRAY, Color.GREEN, Color.MAGENTA, Color.CYAN,Color.BLACK,Color.YELLOW};
    Random rand = new Random();
    private int N_length;
    public GridViewElementAdapter(Context c,int length)
    {
        mContext=c;
        N_length=length;
    }
    @Override
    public int getCount() {
        return N_length;
    }

    @Override
    public Object getItem(int position) {
        return colors[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View grid = convertView;
        if (grid == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = inflater.inflate(R.layout.item_gridview, parent, false);
        }
        TextView text = (TextView) grid.findViewById(R.id.textView_itemGridView);
        ImageView img = (ImageView) grid.findViewById(R.id.imageView_itemGridView);
        img.setColorFilter(colors[rand.nextInt(colors.length)]);
        text.setText(""+rand.nextInt(100));

        return grid;
    }
}
