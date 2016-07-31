package com.triwahyuprasetyo.sqliteservice.process;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class ServiceSample extends Service {

    private final static String TAG = "SERVICESAMPLE";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        processing(0);
        Log.i(TAG, "onStartCommand");
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }

    private void processing(final int index) {
        Log.i(TAG, "processing");
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (index <= 5) {
                    int increment = index + 1;

                    String message = "PROCESS : " + String.valueOf((20 * index)) + "%";
                    Intent intent = new Intent(GlobalConst.ID_BROADCAST_OTHER_PROCESS);
                    intent.putExtra(GlobalConst.ID_RESULT_FROM_OTHER_PROCESS, message);
                    getApplicationContext().sendBroadcast(intent);

                    processing(increment);
                } else {
                    stopSelf();
                }
            }
        }, 1500);
    }

}
