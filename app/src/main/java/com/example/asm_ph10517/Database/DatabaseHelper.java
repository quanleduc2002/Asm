package com.example.asm_ph10517.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.asm_ph10517.DAO.KhoaHocDao;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="dbKhoaHoc";
    public static final int VERSION=1;

    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(KhoaHocDao.SQL_KHOA_HOC);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("Drop table if exists "+KhoaHocDao.TABLE_NAME);
onCreate(db);
    }
}
