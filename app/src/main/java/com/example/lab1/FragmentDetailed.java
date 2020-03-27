package com.example.lab1;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Date;
import java.util.List;

public class FragmentDetailed extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_events_intent,container,false);
        return view;
    }
    public void setFragment(View view, int position, List list)
    {
        TextView name = (TextView) view.findViewById(R.id.activityEventsIntentName);
        TextView text = (TextView) view.findViewById(R.id.activityEventsIntentDescription);
        ImageView img = (ImageView) view.findViewById(R.id.imageView2);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        String currentDateandTime = sdf.format(new Date());

        name.setText(""+list.get(position));
        text.setText(currentDateandTime);
        img.setImageResource(R.drawable.images2);
    }
}

