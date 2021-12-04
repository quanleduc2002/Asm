package com.example.asm_ph10517.DAO;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.asm_ph10517.Database.DatabaseHelper;
import com.example.asm_ph10517.Model.KhoaHoc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class KhoaHocDao {
    private SQLiteDatabase sqLiteDatabase;
    private DatabaseHelper databaseHelper;
    public static final String TABLE_NAME = "KhoaHoc";
    public static final String SQL_KHOA_HOC = "CREATE TABLE KhoaHoc(makhoahoc TEXT PRIMARY KEY,tenkhoahoc TEXT,giangvien TEXT,mota TEXT,ngaynhaphoc DATE,ngayketthuc DATE);";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final String TAG = "KHOAHOCDAO";

    public KhoaHocDao(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public int InsertKhoaHoc(KhoaHoc khoaHoc) {
        ContentValues values = new ContentValues();
        values.put("makhoahoc", khoaHoc.getMaKhoaHoc());
        values.put("tenkhoahoc", khoaHoc.getTenKhoaHoc());
        values.put("giangvien", khoaHoc.getGiangVien());
        values.put("mota", khoaHoc.getMota());
        values.put("ngaynhaphoc", simpleDateFormat.format(khoaHoc.getNgayNhapHoc()));
        values.put("ngayketthuc", simpleDateFormat.format(khoaHoc.getNgayKetThuc()));
        try {
            if (sqLiteDatabase.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return 1;
    }

    public List<KhoaHoc> getAllKhoaHoc() throws ParseException {
        List<KhoaHoc> khoaHocs = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            KhoaHoc khoaHoc = new KhoaHoc();
            khoaHoc.setMaKhoaHoc(cursor.getString(0));
            khoaHoc.setTenKhoaHoc(cursor.getString(1));
            khoaHoc.setGiangVien(cursor.getString(2));
            khoaHoc.setMota(cursor.getString(3));
            khoaHoc.setNgayNhapHoc(simpleDateFormat.parse(cursor.getString(4)));
            khoaHoc.setNgayKetThuc(simpleDateFormat.parse(cursor.getString(5)));
            khoaHocs.add(khoaHoc);
            cursor.moveToNext();

        }
        cursor.close();
        return khoaHocs;
    }

    public int DeleteKhoaHocByID(String maKhoaHoc) {
        int result = sqLiteDatabase.delete(TABLE_NAME, "makhoahoc=?", new String[]{maKhoaHoc});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int updateKhoaHoc(KhoaHoc khoaHoc) {
        ContentValues values = new ContentValues();
        values.put("makhoahoc", khoaHoc.getMaKhoaHoc());
        values.put("tenkhoahoc", khoaHoc.getTenKhoaHoc());
        values.put("giangvien", khoaHoc.getGiangVien());
        values.put("mota", khoaHoc.getMota());
        values.put("ngaynhaphoc", simpleDateFormat.format(khoaHoc.getNgayNhapHoc()));
        values.put("ngayketthuc", simpleDateFormat.format(khoaHoc.getNgayKetThuc()));
        int result = sqLiteDatabase.update(TABLE_NAME, values, "makhoahoc=?", new String[]{khoaHoc.getMaKhoaHoc()});
        if (result == 0) {
            return -1;
        } else {
            return 1;
        }
    }

}
