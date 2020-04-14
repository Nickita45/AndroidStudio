package com.example.lab1.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MakeBitMapFromUrl {
    public static Bitmap getBitmapFromURL(String image_url) {
        Bitmap bitmap = null;

        try {
            URL url = new URL(image_url);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            Log.d("THREAD_EX", "Exception: " + e.toString());
        }

        return bitmap;
    }
}
