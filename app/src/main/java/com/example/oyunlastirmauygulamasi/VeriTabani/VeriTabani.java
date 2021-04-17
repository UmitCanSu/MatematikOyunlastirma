package com.example.oyunlastirmauygulamasi.VeriTabani;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class VeriTabani extends SQLiteOpenHelper {
    public VeriTabani(Context context) {
        super(context, "OyunlastirmaUygulamasi", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS kullanici_islemleri( " +
                "ID	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "Isim	TEXT ," +
                "Soyad	TEXT ," +
                "Mail	TEXT UNIQUE," +
                "Kullaniciadi 	TEXT UNIQUE ," +
                "Sifre	TEXT," +
                "Profil_Resmi BLOB ) ");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS PuanTablo(" +
                "ID INTEGER," +
                "Seviye INTEGER," +
                "Puan INTEGER )");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS RozetlerTablo(" +
                "Kullanici_ID INTEGER," +
                "rozetID TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS kullanici_islemleri");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PuanTablo");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS RozetlerTablo");
        onCreate(sqLiteDatabase);
    }
}
