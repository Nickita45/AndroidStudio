package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ThreatAsyncActivity extends AppCompatActivity {
    private final String IMAGE_URL = "https://i.imgur.com/vtexofP.jpg";
    private final String FILE_URL = "https://goo.gl/KXPn7F";

    private ProgressBar mProgressBar;
    private ImageView imageView;
    private DownloadImage downloadImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threat_async);
        StrictMode.ThreadPolicy policy = new StrictMode
                .ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBarAsync);
        imageView = (ImageView) findViewById(R.id.imageViewAsync);

        // Загружаем изображение по нажатию кнопки
        final Button button_image_download = (Button) findViewById(R.id.buttonAsync);
        button_image_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new MyThread().start();
                if(downloadImage!=null)
                    downloadImage.cancel(true);
                downloadImage = new DownloadImage();
                downloadImage.execute(IMAGE_URL);

            }
        });

        Button archive = (Button) findViewById(R.id.archive_buttonAsync);
        archive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DownloadTask task = new DownloadTask();
                task.execute(FILE_URL);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cancel running task(s) to avoid memory leaks
        if (downloadImage != null)
            downloadImage.cancel(true);

    }
    class MyThread extends Thread {
        @Override
        public void run() {
            // Загружаем картинку
            Log.d("D","THREAD");
            ThreatAsyncActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Bitmap bm = getBitmapFromURL(IMAGE_URL);

                    // Устанавливаем картинку
                    imageView.setImageBitmap(bm);
                }
            });
        }
    }
    private class DownloadTask extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Toast.makeText(ThreatAsyncActivity.this,
                    "Началась загрузка файла", Toast.LENGTH_SHORT).show();
            mProgressBar.setProgress(0);
        }

        @Override
        protected Boolean doInBackground(String... params) {

            // Объявляем потоки и HTTP-соединение
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // Если сервер вернул что-то кроме 200, выходим с ошибкой
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return false;
                }

                // Получаем длину файла
                int fileLength = connection.getContentLength();

                // Получаем поток для чтения файла
                input = connection.getInputStream();

                // Получаем ссылку на внутреннее хранилище
                File path = ThreatAsyncActivity.this.getExternalCacheDir();
                File file = new File(path, "/" + "file.rar");
                output = new FileOutputStream(file);
               // Log.d("D/THREAD_EX",path.toString());
                byte data[] = new byte[4096];
                long total = 0;
                int count;

                // Пока не дошли до конца файла, читаем по 4096 байт
                while ((count = input.read(data)) != -1) {

                    // Если работа AsyncTask была прервана,
                    // закрываем пото и выходим
                    if (isCancelled()) {
                        input.close();
                        return false;
                    }

                    // Подсчитываем сколько уже загрузили
                    total += count;
                    if (fileLength > 0) {
                        // Считаем процен загрузки и передаем в onProgressUpdate()
                        publishProgress((int) (total * 100 / fileLength));
                    }
                    // Записываем 4096 байт в файл
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                return false;
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }

            // Если все ок, то возвращаем true
            // Если были бы какие-то проблемы, то метод закончился
            // бы раньше с false
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            // Обновляем ProgressBar

            mProgressBar.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(Boolean isSuccessful) {
            String msg = "";

            // Проверяем результат
            if (isSuccessful) {
                msg = "Файл успешно загружен!";
            } else {
                msg = "Ошибка при скачивании файла!";
            }
            // Выводим сообщение
            Toast.makeText(ThreatAsyncActivity.this, msg, Toast.LENGTH_SHORT).show();

            mProgressBar.setProgress(0);
        }
    }

    /**
     * Метод используется для получения
     * статического ресурса (изображения) через протокол HTTP
     *
     * @param image_url - URL статического ресурса (изображения)
     * @return - Bitmap с изображением
     */
    public Bitmap getBitmapFromURL(String image_url) {
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
    private class DownloadImage extends AsyncTask<String, Integer, Bitmap>{
        Bitmap bm;
        @Override
        protected Bitmap doInBackground(String... params) {

            try {
                  bm = getBitmapFromURL(params[0]);

            }  catch (Exception e){
            return null;
            }
            return bm;
        }
        @Override
        protected void onPostExecute(Bitmap isSuccessful) {
            String msg =  "Картинка!";

            Toast.makeText(ThreatAsyncActivity.this, msg, Toast.LENGTH_SHORT).show();
            imageView.setImageBitmap(bm);
            mProgressBar.setProgress(0);
        }
    }
}