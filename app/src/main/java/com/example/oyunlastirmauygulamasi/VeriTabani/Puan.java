 package com.example.oyunlastirmauygulamasi.VeriTabani;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oyunlastirmauygulamasi.VeriTabani.VeriTabani;

public class Puan {
    public void puanKaydet(VeriTabani vt, int ID){
        SQLiteDatabase db = vt.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put("ID",ID);
        values1.put("Seviye",1);
        values1.put("Puan",0);
        db.insertOrThrow("PuanTablo",null,values1);
        db.close();
    }
    public int puanGetir(VeriTabani vt, int kullanıciID){
        int puan = 0;
        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT Puan FROM PuanTablo WHERE ID = ?", new String[]{String.valueOf(kullanıciID)});
        while (c.moveToNext()){
            puan = c.getInt(c.getColumnIndex("Puan"));
        }
        db.close();
        return puan;
    }
    public void puanGüncelle (VeriTabani vt, int ID,int guncelPuan){
        SQLiteDatabase db = vt.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Puan",guncelPuan);
        db.update("PuanTablo",values,"ID=?",new String[]{String.valueOf(ID)});
        db.close();
    }
    public  int  seviyeGetir(VeriTabani vt, int kullanıciID ){
        int seviye = 0;
        SQLiteDatabase db  = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT Seviye FROM PuanTablo WHERE ID = ?", new String[]{String.valueOf(kullanıciID)});
        while (c.moveToNext()){
          seviye = c.getInt(c.getColumnIndex("Seviye"));
        }
        db.close();
        return  seviye;
    }
    public void seviyeGuncelle(VeriTabani vt, int kullanıciID, int guncelSeviye){
        SQLiteDatabase db = vt.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Seviye",guncelSeviye);
        db.update("PuanTablo",values,"ID=?",new String[]{String.valueOf(kullanıciID)});
        db.close();
    }
}
