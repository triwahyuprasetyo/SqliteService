package com.triwahyuprasetyo.sqliteservice.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.triwahyuprasetyo.sqliteservice.database.AnggotaContract.AnggotaTable;

/**
 * Created by why on 7/31/16.
 */

public class AnggotaDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Anggota.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ANGGOTA =
            "CREATE TABLE " + AnggotaTable.TABLE_NAME + " (" +
                    AnggotaTable.COLUMN_NAME_ANGGOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    AnggotaTable.COLUMN_NAME_ANGGOTA_NAME + TEXT_TYPE + COMMA_SEP +
                    AnggotaTable.COLUMN_NAME_ANGGOTA_ADDRESS + TEXT_TYPE + COMMA_SEP +
                    AnggotaTable.COLUMN_NAME_ANGGOTA_AGE + " INTEGER)";

    private static final String SQL_DELETE_ANGGOTA =
            "DROP TABLE IF EXISTS " + AnggotaTable.TABLE_NAME;

    public AnggotaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ANGGOTA);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ANGGOTA);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void cleanTable(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_ANGGOTA);
        onCreate(db);
    }
}
