package com.triwahyuprasetyo.sqliteservice.database;

import android.provider.BaseColumns;

/**
 * Created by why on 7/31/16.
 */

public class AnggotaContract {

    /* Inner class that defines the table contents */
    public static abstract class AnggotaTable implements BaseColumns {
        public static final String TABLE_NAME = "anggota";
        public static final String COLUMN_NAME_ANGGOTA_ID = "anggota_id";
        public static final String COLUMN_NAME_ANGGOTA_NAME = "anggota_name";
        public static final String COLUMN_NAME_ANGGOTA_ADDRESS = "anggota_address";
        public static final String COLUMN_NAME_ANGGOTA_AGE = "anggota_age";
    }

}
