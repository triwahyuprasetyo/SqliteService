package com.triwahyuprasetyo.sqliteservice;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.triwahyuprasetyo.sqliteservice.database.Anggota;
import com.triwahyuprasetyo.sqliteservice.database.AnggotaDbDao;
import com.triwahyuprasetyo.sqliteservice.database.AnggotaDbHelper;
import com.triwahyuprasetyo.sqliteservice.database.AnggotaDbService;
import com.triwahyuprasetyo.sqliteservice.process.GlobalConst;
import com.triwahyuprasetyo.sqliteservice.process.ServiceSample;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "Anggota";
    private static final int ID_NOTIFICATION = 1;
    private static MainActivity ma;
    private static BroadcastReceiver getProcessStatus = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                final String message = extras.getString(GlobalConst.ID_RESULT_FROM_OTHER_PROCESS);
                Log.i("SDP NOTIFICATION", "");
                ma.generateNotification(message);
            }
        }
    };
    Intent intent;
    private Button insert, getAll, getById, update, delete, notification, service, serviceStop;
    private AnggotaDbService anggotaDbService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ma = this;
        insert = (Button) findViewById(R.id.button_insert_mainActivity);
        insert.setOnClickListener(this);
        getAll = (Button) findViewById(R.id.button_getAllAnggota_mainActivity);
        getAll.setOnClickListener(this);
        getById = (Button) findViewById(R.id.button_getAnggotaById_mainActivity);
        getById.setOnClickListener(this);
        update = (Button) findViewById(R.id.button_update_mainActivity);
        update.setOnClickListener(this);
        delete = (Button) findViewById(R.id.button_delete_mainActivity);
        delete.setOnClickListener(this);
        notification = (Button) findViewById(R.id.button_notification_mainActivity);
        notification.setOnClickListener(this);
        service = (Button) findViewById(R.id.button_serviceStart_mainActivity);
        service.setOnClickListener(this);
        serviceStop = (Button) findViewById(R.id.button_serviceStop_mainActivity);
        serviceStop.setOnClickListener(this);

        AnggotaDbHelper anggotaDbHelper = new AnggotaDbHelper(getApplicationContext());
        SQLiteDatabase sqliteDb = anggotaDbHelper.getWritableDatabase();
        AnggotaDbDao anggotaDbDao = new AnggotaDbDao(sqliteDb);
        anggotaDbService = new AnggotaDbService(anggotaDbDao);

        registerReceiver(getProcessStatus, new IntentFilter(GlobalConst.ID_BROADCAST_OTHER_PROCESS));
        intent = new Intent(getApplicationContext(), ServiceSample.class);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == insert.getId()) {
            Anggota a = new Anggota();
            a.setAnggotaName("Tri");
            a.setAnggotaAddress("Dagen");
            a.setAnggotaAge(25);

            Anggota b = new Anggota();
            b.setAnggotaName("Wahyu");
            b.setAnggotaAddress("Suruh");
            b.setAnggotaAge(24);

            Anggota c = new Anggota();
            c.setAnggotaName("Prasetyo");
            c.setAnggotaAddress("Tasikmadu");
            c.setAnggotaAge(23);

            Anggota d = new Anggota();
            d.setAnggotaName("Why");
            d.setAnggotaAddress("Karanganyar");
            d.setAnggotaAge(22);

            anggotaDbService.insertAnggota(a);
            anggotaDbService.insertAnggota(b);
            anggotaDbService.insertAnggota(c);
            anggotaDbService.insertAnggota(d);
        } else if (v.getId() == getAll.getId()) {
            ArrayList<Anggota> anggotaList = anggotaDbService.getAllAnggota();
            for (Anggota anggota : anggotaList) {
                Log.i(TAG, anggota.getAnggotaId() + "-" + anggota.getAnggotaName() + "-" + anggota.getAnggotaAddress() + "-" + anggota.getAnggotaAge());
            }
        } else if (v.getId() == getById.getId()) {
            Anggota anggota = anggotaDbService.getAnggotaById(1);
            Log.i(TAG, anggota.getAnggotaId() + "-" + anggota.getAnggotaName() + "-" + anggota.getAnggotaAddress() + "-" + anggota.getAnggotaAge());
        } else if (v.getId() == update.getId()) {
            Anggota u = new Anggota();
            u.setAnggotaId(3);
            u.setAnggotaName("Eflana");
            u.setAnggotaAddress("Jogja");
            u.setAnggotaAge(21);
            anggotaDbService.updateAnggota(u);
        } else if (v.getId() == delete.getId()) {
            anggotaDbService.deleteAnggota(3);
        } else if (v.getId() == notification.getId()) {
            generateNotification("ini message nya");
        } else if (v.getId() == service.getId()) {
//            Intent intent = new Intent(getApplicationContext(), ServiceSample.class);
            getApplicationContext().startService(intent);
        } else if (v.getId() == service.getId()) {
//            Intent intent = new Intent(getApplicationContext(), ServiceSample.class);
            getApplicationContext().stopService(intent);
        }
    }

    public void generateNotification(String message) {
        Context context = getApplicationContext();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(ID_NOTIFICATION);//Untuk menutup notifikasi yang ada (dengan id yang disebutkan), ditutup dulu.

        long when = System.currentTimeMillis();

        String contentTitle = "Notification";

        Bitmap icon_big = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);

        Intent notificationIntent = new Intent(context, NotifActivity.class);
        notificationIntent.putExtra("NOTIFICATION", true);

        PendingIntent intent = PendingIntent.getActivity(context, 1, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.addLine(message);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentTitle(contentTitle)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(icon_big)
                .setSubText("Notifikasi Test")
                .setContentInfo("hallo ini notifikasi")
                .setWhen(when)
                .setAutoCancel(true)
                .setContentIntent(intent)
                .setStyle(inboxStyle)
                .setTicker(message);

        notificationManager.notify(1, mBuilder.build());
    }
}
