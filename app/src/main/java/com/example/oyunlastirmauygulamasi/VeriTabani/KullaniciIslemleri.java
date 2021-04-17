package com.example.oyunlastirmauygulamasi.VeriTabani;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Debug;
import android.util.Log;
import android.widget.Toast;

import com.example.oyunlastirmauygulamasi.Kullanicilar;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class KullaniciIslemleri {
    public boolean kullaniciKaydet(VeriTabani vt, String ad, String soyAd, String mail, String kullaniciAdi, String sifre){


           try{
               SQLiteDatabase db = vt.getWritableDatabase();
               ContentValues values = new ContentValues();
               values.put("Isim",ad);
               values.put("Soyad",soyAd);
               values.put("Mail",mail);
               values.put("Kullaniciadi",kullaniciAdi);
               values.put("Sifre",sifre);
               db.insertOrThrow("kullanici_islemleri",null,values);
               db.close();
               return true;
           }catch (Exception e){
               return false;
           }




    }



    public  String kullaniciVarmi(VeriTabani vt, String kullaniciAdi, String mail){
        SQLiteDatabase db = vt.getWritableDatabase();
        String kontrol = "Yok";
        Cursor c = db.rawQuery("SELECT * from kullanici_islemleri where Kullaniciadi LIKE ? and Mail LIKE ?",new String[]{kullaniciAdi,mail});
        while (c.moveToNext()){
            if(c.getString(c.getColumnIndex("Mail")).equals(mail)){
                kontrol ="Sistemimizde Bu Mail Adresi Bulunmaktadır";
            }
            else if(c.getString(c.getColumnIndex("Isım")).equals(kullaniciAdi)){
                kontrol ="Sistemimizde Bu Kulalnıcı Adı Bulunmaktadır";
            }

        }
        c.close();
        return  kontrol;
    }

    public  int GirisYap(VeriTabani vt,String kullaniciAdi, String sifre){
        int kontrolID = -1;
        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SElect ID from kullanici_islemleri where Kullaniciadi like ? and Sifre like ? ",new String[]{kullaniciAdi,sifre});
        while (c.moveToNext()){

            kontrolID = c.getInt(c.getColumnIndex("ID"));
        }
        c.close();
        return  kontrolID;
    }
    public String GirisYap2(String kullaniciAdi, String sifre){
        final String[] hata = {""};
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Kullanici");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    for(ParseObject p: objects){
                        String k_Ad = p.getString("Kullanici_Adi");
                        String k_Sifre = p.getString("Sifre");
                        if(k_Ad.equals(kullaniciAdi) && k_Sifre.equals(sifre)){
                            hata[0] =  p.getString("ID");
                        }
                    }
                }else {
                    Log.e("Giris Hata","Girişte Hata");
                }

            }
        });
       return hata[0];
    }
    public ArrayList<String> kullanıcılarıListele(VeriTabani vt){
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();
        Log.e("Girdi","KullanıcıYa Girdi");
        Cursor c = db.rawQuery("SELECT * from kullanici_islemleri",null);
        while (c.moveToNext()){

                list.add(c.getString(c.getColumnIndex("Isim")));
        }
        c.close();
        return  list;
    }
    public int ID_Bul(VeriTabani vt, String kullanıcıadi){
        int ID =0;
        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT ID FROM kullanici_islemleri WHERE Kullaniciadi = ?", new String[]{kullanıcıadi});
        while (c.moveToNext()){
            ID = c.getInt(c.getColumnIndex("ID"));
        }
        return ID;
    }
    public  ArrayList<Kullanicilar> tumKullanıcıList(VeriTabani vt){
        ArrayList<Kullanicilar> list = new ArrayList<>();
        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT k.ID,k.Kullaniciadi,k.Isim,k.Soyad,p.Puan, k.Mail,k.Profil_Resmi from kullanici_islemleri as k inner join PuanTablo as p on k.ID=P.ID order by p.Puan DESC  ",null);
        while (c.moveToNext()){

            Kullanicilar kullanicilar = new Kullanicilar(c.getInt(c.getColumnIndex("ID")),
                    c.getString(c.getColumnIndex("Kullaniciadi")),
                    c.getString(c.getColumnIndex("Isim")),
                    c.getString(c.getColumnIndex("Soyad")),
                    c.getInt(c.getColumnIndex("Puan")),
                    c.getString(c.getColumnIndex("Mail")),
                    c.getBlob(c.getColumnIndex("Profil_Resmi"))
            );



            list.add(kullanicilar);

        }
        c.close();
        return  list;
    }
    public Kullanicilar kullaniciBilgileri( VeriTabani vt, int kullanici_id){
        Kullanicilar kullanici = new Kullanicilar() ;
        SQLiteDatabase db = vt.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT k.ID,k.Kullaniciadi,k.Isim,k.Soyad,p.Puan,k.Mail,k.Profil_Resmi from kullanici_islemleri " +
                "as k inner join PuanTablo as p on k.ID=P.ID Where  k.ID =?",new String[]{String.valueOf(kullanici_id)});
        while (c.moveToNext()){

            kullanici = new Kullanicilar(
                    kullanici_id,
                    c.getString(c.getColumnIndex("Kullaniciadi")),
                    c.getString(c.getColumnIndex("Isim")),
                    c.getString(c.getColumnIndex("Soyad")),
                    c.getInt(c.getColumnIndex("Puan")),
                    c.getString(c.getColumnIndex("Mail")),
                    c.getBlob(c.getColumnIndex("Profil_Resmi")));
        }
        db.close();
        return kullanici;
    }
    public void kullaniciResimKaydet(VeriTabani vt, int kullanici_ID, byte[] resim){
        SQLiteDatabase db = vt.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Profil_Resmi",resim);
        db.update("kullanici_islemleri",values,"ID = ?",new String[]{String.valueOf(kullanici_ID)});
        db.close();

    }

}
