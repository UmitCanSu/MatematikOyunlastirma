package com.example.oyunlastirmauygulamasi.VeriTabani;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class RozetlerVt {
    public ArrayList<String> toplananRozetler(VeriTabani vt, int kullanici_id){
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase dp = vt.getWritableDatabase();
        Cursor c = dp.rawQuery("Select rozetID from RozetlerTablo where Kullanici_ID =? order by rozetID ASC",new String[]{String.valueOf(kullanici_id)});
        while (c.moveToNext()){
            list.add(c.getString(c.getColumnIndex("rozetID")));
        }
        dp.close();
        return list;
    }
    public int rozetAlinmismiKontrol(VeriTabani vt, int k_ID, String rozetID){
        int kontrol = 0;
        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("Select rozetID from RozetlerTablo where rozetID like ? and Kullanici_ID = ?",new String[]{rozetID,String.valueOf(k_ID)});
/*
        if(c == null){
            db.close();
            return false;
        }else{
            db.close();
            return true;
        }
        */
        while (c.moveToNext()){
            if(c.getString(c.getColumnIndex("rozetID")).equals(rozetID)){
                kontrol =2;
                return kontrol;
            }else{
                kontrol =1;
            }
        }
        return kontrol;
    }
    public void rozetLeriEkle(VeriTabani vt, int k_ID, String alınanRozet){
        SQLiteDatabase db = vt.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("Kullanici_ID",k_ID);
            values.put("rozetID",alınanRozet);
            db.insertOrThrow("RozetlerTablo",null,values);
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
    }

}
