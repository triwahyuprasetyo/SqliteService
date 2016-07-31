package com.triwahyuprasetyo.sqliteservice.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.triwahyuprasetyo.sqliteservice.database.AnggotaContract.AnggotaTable;

import java.util.ArrayList;

/**
 * Created by why on 7/31/16.
 */

public class AnggotaDbDao {
    private SQLiteDatabase db;

    public AnggotaDbDao(SQLiteDatabase db) {
        this.db = db;
    }

    public void insertAnggota(Anggota anggota) {
        ContentValues values = new ContentValues();
        values.put(AnggotaTable.COLUMN_NAME_ANGGOTA_NAME, anggota.getAnggotaName());
        values.put(AnggotaTable.COLUMN_NAME_ANGGOTA_ADDRESS, anggota.getAnggotaAddress());
        values.put(AnggotaTable.COLUMN_NAME_ANGGOTA_AGE, anggota.getAnggotaAge());
        db.insert(AnggotaTable.TABLE_NAME, null, values);
    }

    public ArrayList<Anggota> getAllAnggota() {
        ArrayList<Anggota> anggotaList = new ArrayList<Anggota>();
        String[] projection = {
                AnggotaTable.COLUMN_NAME_ANGGOTA_ID,
                AnggotaTable.COLUMN_NAME_ANGGOTA_NAME,
                AnggotaTable.COLUMN_NAME_ANGGOTA_ADDRESS,
                AnggotaTable.COLUMN_NAME_ANGGOTA_AGE
        };

        //Mengurutkan hasil dalam bentuk sortOrder
        String sortOrder = AnggotaTable.COLUMN_NAME_ANGGOTA_ID + " ASC";
        Cursor cursor = db.query(
                AnggotaTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int anggotaId = cursor.getInt(0);
            String anggotaName = cursor.getString(1);
            String anggotaAddress = cursor.getString(2);
            int anggotaAge = cursor.getInt(3);
            Anggota anggota = new Anggota();
            anggota.setAnggotaId(anggotaId);
            anggota.setAnggotaName(anggotaName);
            anggota.setAnggotaAddress(anggotaAddress);
            anggota.setAnggotaAge(anggotaAge);
            anggotaList.add(anggota);
        }
        return anggotaList;
    }

    public Anggota getAnggotaById(int anggId) {
        String[] projection = {
                AnggotaTable.COLUMN_NAME_ANGGOTA_ID,
                AnggotaTable.COLUMN_NAME_ANGGOTA_NAME,
                AnggotaTable.COLUMN_NAME_ANGGOTA_ADDRESS,
                AnggotaTable.COLUMN_NAME_ANGGOTA_AGE
        };

        Cursor cursor = db.query(
                AnggotaTable.TABLE_NAME,
                projection,
                AnggotaTable.COLUMN_NAME_ANGGOTA_ID + "=" + anggId,
                null,
                null,
                null,
                null
        );

        Anggota anggota = new Anggota();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int anggotaId = cursor.getInt(0);
            String anggotaName = cursor.getString(1);
            String anggotaAddress = cursor.getString(2);
            int anggotaAge = cursor.getInt(3);
            anggota.setAnggotaId(anggotaId);
            anggota.setAnggotaName(anggotaName);
            anggota.setAnggotaAddress(anggotaAddress);
            anggota.setAnggotaAge(anggotaAge);
        }
        return anggota;
    }

    public void updateAnggota(Anggota anggota) {
        ContentValues values = new ContentValues();
        values.put(AnggotaTable.COLUMN_NAME_ANGGOTA_NAME, anggota.getAnggotaName());
        values.put(AnggotaTable.COLUMN_NAME_ANGGOTA_ADDRESS, anggota.getAnggotaAddress());
        values.put(AnggotaTable.COLUMN_NAME_ANGGOTA_AGE, anggota.getAnggotaAge());
        db.update(AnggotaTable.TABLE_NAME, values, AnggotaTable.COLUMN_NAME_ANGGOTA_ID + "=" + anggota.getAnggotaId(), null);
    }

    public void deleteAnggota(int anggotaId) {
        db.delete(AnggotaTable.TABLE_NAME, AnggotaTable.COLUMN_NAME_ANGGOTA_ID + "=" + anggotaId, null);
    }
}
