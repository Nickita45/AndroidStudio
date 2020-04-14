package com.example.lab1.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.lab1.R;

import com.example.lab1.entities.Animal;
import com.example.lab1.utils.MakeBitMapFromUrl;


import java.util.ArrayList;

import java.util.concurrent.TimeUnit;

public class GridViewAnimalAdapter extends BaseAdapter {
    private Context mContext;

    ArrayList<Animal> animals = new ArrayList<Animal>();
    ImageView img;
    TextView textView1;
    TextView textView2;
    static class ViewHolder{
        ImageView imageView;
        TextView textViewName;
        TextView textViewAge;

    }
    ArrayList<View> viewList = new ArrayList<>();
    public GridViewAnimalAdapter(Context c)
    {
        mContext=c;
        initArray();

    }
    @Override
    public int getCount() {

        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
                     //trying to reuse a recycled view
        ViewHolder holder = null;
        View grid = convertView;
        if (grid == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = inflater.inflate(R.layout.item_gridview_animal, parent, false);
            img = (ImageView) grid.findViewById(R.id.imageView_itemPictureAnimal);
            textView1 = (TextView) grid.findViewById(R.id.textView_itemNameAnimal);
            textView2 = (TextView) grid.findViewById(R.id.textViewAge);
            grid.setTag(holder);
            viewList.add(grid);
        } else
        {
            holder = (ViewHolder) grid.getTag();
        }

        DownloadImage downloadImage = new DownloadImage();
        downloadImage.execute(position);


        return grid;
    }
    private class DownloadImage extends AsyncTask<Integer, Integer, Boolean> {
        Bitmap bm ;//new Bitmap[animals.size()];

        //View grid;
        @Override
        protected Boolean doInBackground(Integer... params) {

            try {
                bm =MakeBitMapFromUrl.getBitmapFromURL(animals.get(params[0]).url_picture);
                publishProgress(params[0]);
                TimeUnit.SECONDS.sleep(1);
            }  catch (Exception e){
                return null;
            }
            return true;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            img = (ImageView) viewList.get(values[0]).findViewById(R.id.imageView_itemPictureAnimal);
            textView1 = (TextView) viewList.get(values[0]).findViewById(R.id.textView_itemNameAnimal);
            textView2 = (TextView) viewList.get(values[0]).findViewById(R.id.textViewAge);
            img.setImageBitmap(bm);
            textView1.setText(animals.get(values[0]).name);
            textView2.setText("Лет:"+animals.get(values[0]).age);
            //Log.d("PROGRES",String.valueOf(animals.size()));

        }
        @Override
        protected void onPostExecute(Boolean isSuccessful) {
            String msg =  "Картинка!";

        }
    }
    public void initArray()
    {
        animals.add(new Animal("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Lion_d%27Afrique.jpg/220px-Lion_d%27Afrique.jpg","Лев",14));
        animals.add(new Animal("https://upload.wikimedia.org/wikipedia/commons/thumb/3/37/African_Bush_Elephant.jpg/1200px-African_Bush_Elephant.jpg","Слон",70));
        animals.add(new Animal("https://upload.wikimedia.org/wikipedia/commons/6/60/Equus_quagga.jpg","Зебра",20));
        animals.add(new Animal("https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Medved_mzoo.jpg/275px-Medved_mzoo.jpg","Медведь",20));
        animals.add(new Animal("https://orakul.ru/wp-content/images/sonnik/bukva/e/ezhik.jpg","Ёж",4));
        animals.add(new Animal("https://faunistics.com/wp-content/uploads/2019/07/1-8.jpg","Жираф",30));
        animals.add(new Animal("https://user.vse42.ru/files/ui-5aa62e678d3891.05809335.jpeg","Обезьяна",20));
        animals.add(new Animal("https://i.ytimg.com/vi/nLTTa0ToTU8/maxresdefault.jpg","Собака",12));
        animals.add(new Animal("https://i.ytimg.com/vi/tDzFBXFlbxw/maxresdefault.jpg","Крокодил",70));
        animals.add(new Animal("https://www.myplanet-ua.com/wp-content/uploads/2018/01/%D0%97%D0%B0%D1%8F%D1%86-%D1%80%D1%83%D1%81%D0%B0%D0%BA-%D1%84%D0%BE%D1%82%D0%BE.jpg","Заяц",3));

    }

}
