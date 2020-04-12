package com.example.lab1.receivers;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals(Intent.ACTION_BATTERY_LOW))
            Toast.makeText(context,"НИКЗКИЙ ЗАРЯД БАТАРЕИ!",Toast.LENGTH_SHORT).show();

        if(action.equals(Intent.ACTION_CAMERA_BUTTON))
            Toast.makeText(context,"НАЖАТА КНОПКА КАМЕРЫ!",Toast.LENGTH_SHORT).show();
        if(action.equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
            if (isAirplaneModeOn(context))
                Toast.makeText(context, "РЕЖИМ В САМОЛЕТЕ ВКЛЮЧЕН!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "РЕЖИМ В САМОЛЕТЕ ВЫКЛЮЧЕН!", Toast.LENGTH_SHORT).show();
        }
    }
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isAirplaneModeOn(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        } else {
            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        }
    }
}
