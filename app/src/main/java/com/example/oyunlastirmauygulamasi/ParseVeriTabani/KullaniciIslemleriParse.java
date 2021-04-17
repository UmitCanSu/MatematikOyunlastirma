package com.example.oyunlastirmauygulamasi.ParseVeriTabani;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;


public class KullaniciIslemleriParse{
Context context;

    public KullaniciIslemleriParse(Context context) {
        this.context = context;
    }
/*
    public void kullaniciKaydet(String ad, String soyAd, String mail, String kullaniciAdi, String sifre){
        ParseObject kaydet = new ParseObject("Kullanici");
        kaydet.put("Ad",ad);
        kaydet.put("Soyad",soyAd);
        kaydet.put("Mail",mail);
        kaydet.put("Kullanici_Adi",kullaniciAdi);
        kaydet.put("Sifre",sifre);
        kaydet.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Log.e("Kayit Hata",e.getLocalizedMessage());
                }else {
                    Log.e("Kayit Hata","Hata Yok");
                }
            }
        });
    }
    */
    public String IDBul(String kullaniciAdi){
       String id ="";
        ParseQuery<ParseObject> parse = ParseQuery.getQuery("Kullanici");


       return id;
    }
    public boolean kullaniciKaydet2(String ad, String soyAd, String mail, String kullaniciAdi, String sifre){

        final boolean[] kontrol = {false};
        ParseUser user = new ParseUser();
        user.setUsername(kullaniciAdi);
        user.setPassword(sifre);
        user.setEmail(mail);
        user.put("kullaniciAdi",ad);
        user.put("kullaniciSoyadi",soyAd);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    kontrol[0] = true;
                    Toast.makeText(context.getApplicationContext(),"Kayıt Başarılı",Toast.LENGTH_SHORT);
                }else {
                    e.printStackTrace();
                    Toast.makeText(context.getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT);
                }
            }
        });
        return kontrol[0];
    }
    public String kullaniciGirisi(String kullaniciAdi, String sifre){
        final String[] id = {""};

        ParseUser.logInInBackground(kullaniciAdi,sifre, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e == null){
                     id[0] = user.getObjectId();
                     Log.e("of","12");
                    Toast.makeText(context.getApplicationContext(),id[0],Toast.LENGTH_SHORT);
                    Log.e("of",id[0]);

                }else {
                    e.printStackTrace();
                }
            }
        });

        return id[0];
    }
}
