package com.example.oyunlastirmauygulamasi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.oyunlastirmauygulamasi.Activity.Anasayfa;
import com.example.oyunlastirmauygulamasi.Kullanicilar;
import com.example.oyunlastirmauygulamasi.ParseVeriTabani.KullaniciIslemleriParse;
import com.example.oyunlastirmauygulamasi.R;
import com.example.oyunlastirmauygulamasi.VeriTabani.KullaniciIslemleri;
import com.example.oyunlastirmauygulamasi.VeriTabani.Puan;
import com.example.oyunlastirmauygulamasi.VeriTabani.VeriTabani;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Switch switchUyeOl;
    EditText sifre_Edittext;
    EditText kullaniAdi_Edittext;
    EditText ad_Edittext;
    EditText soyad_Edittext;
    EditText mail_Edittext;
    Button kayıtOlButton;
    boolean girisYapKontrol = true;
    ArrayList<EditText> editTextsLists;
    VeriTabani vt;
    String kullaniciAdi="";
    String sifre="";
    String ad="";
    String soyAd="";
    String mail="";
   public static int k_Id= -1;
   public static String s_k_Id= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchUyeOl = findViewById(R.id.switchUyeOl_Main);
        sifre_Edittext = findViewById(R.id.sifreEdittext_Main);
        kullaniAdi_Edittext = findViewById(R.id.kullaniciAdiEdittext_Main);
        ad_Edittext = findViewById(R.id.adEdittext_Main);
        soyad_Edittext = findViewById(R.id.soyisim_Edittext_Main);
        mail_Edittext = findViewById(R.id.editTextTextEmailAddress_Mail);
        kayıtOlButton = findViewById(R.id.kayıtolButton_Main);
        editTextsLists = new ArrayList<>();
        editTextsLists.add(kullaniAdi_Edittext);
        editTextsLists.add(sifre_Edittext);
        editTextsLists.add(ad_Edittext);
        editTextsLists.add(sifre_Edittext);
        editTextsLists.add(soyad_Edittext);
        editTextsLists.add(mail_Edittext);

        vt = new VeriTabani(this);
        Log.e("Kullanıcı Liste->", "text");
        ArrayList<String> list = new KullaniciIslemleri().kullanıcılarıListele(vt);
        for (String text: list) {
            Log.e("Kullanıcı Liste->", text);
        }
        KullaniciIslemleri kl = new KullaniciIslemleri();
/*

        new KullaniciIslemleri().kullaniciKaydet(vt,"Umit Can ","SU","umit@gmail.com","umit","111");
        new Puan().puanKaydet(vt,kl.ID_Bul(vt,"umit"));
        new KullaniciIslemleri().kullaniciKaydet(vt,"Semih  ","Keles","semih@gmail.com","semih","111");
        new Puan().puanKaydet(vt,kl.ID_Bul(vt,"semih"));

 */

/*

        SQLiteDatabase db1 =vt.getWritableDatabase();
        db1.delete("kullanici_islemleri",null,null);
        db1.delete("PuanTablo",null,null);
        VeriTabani vt1= new VeriTabani(this);
        SQLiteDatabase db = vt.getWritableDatabase();
        vt.onUpgrade(db,1,1);

*/

/*


        new KullaniciIslemleri().kullaniciKaydet(vt,"Umit Can ","SU","umit@gmail.com","umit","111");
        new KullaniciIslemleri().(vt,kl.ID_Bul(vt,"umit"));
         new KullaniciIslemleri().kullaniciKaydet(vt,"Semih  ","Keles","semih@gmail.com","semih","111");
        new Puan().puanKaydet(vt,kl.ID_Bul(vt,"semih"));
*/

/*
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("OyunlastirmaUygulamasi",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS kullanici_islemleri( " +
                            "ID	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                            "Isim	VARCHAR NOT NULL," +
                            "Soyad	VARCHAR NOT NULL," +
                            "Mail	VARCHAR NOT NULL," +
                            "Kullaniciadi	VARCHAR NOT NULL UNIQUE," +
                            "Sifre	VARCHAR NOT NULL UNIQUE) ");
            // Veri Ekleme
            database.execSQL("INSERT INTO kullanici_islemleri (Isim,Soyad,Mail,Kullaniciadi,Sifre) VALUES () ");
            // Veri Okuma
            Cursor cursor = database.rawQuery("SELECT * FROM kullanici_islemleri",null);
            int isimIndex = cursor.getColumnIndex("INTEGER");
            int soyisimIndex = cursor.getColumnIndex("Soyad");
            int mailIndex = cursor.getColumnIndex("Mail");
            int kullanıcıAdiIndex = cursor.getColumnIndex("Kullaniciadi");
            int sifreIndex = cursor.getColumnIndex("Sifre");
            while (cursor.moveToNext()){

            }
            cursor.close();
            database.close();
        }catch (Exception e){

        }
        */


    }

    public void uyeOlSwitch(View view){
        if(girisYapKontrol){
            girisYapKontrol = false;
            switchUyeOl.setText("Kayıt Ol");
            ad_Edittext.setVisibility(View.VISIBLE);
            soyad_Edittext.setVisibility(View.VISIBLE);
            mail_Edittext.setVisibility(View.VISIBLE);
            kayıtOlButton.setVisibility(View.VISIBLE);
            kayıtOlButton.setText("Kayıt Ol");

        }else {
            girisYapKontrol = true;
            switchUyeOl.setText("Giriş Yap");
            ad_Edittext.setVisibility(View.GONE);
            soyad_Edittext.setVisibility(View.GONE);
            mail_Edittext.setVisibility(View.GONE);
            kayıtOlButton.setText("Giriş Yap");
        }
    }
    public void kaydetButton(View view){
/*
        KullaniciIslemleri kl = new KullaniciIslemleri();
        k_Id = kl.GirisYap(vt,kullaniciAdi,sifre);
        k_Id=1;
        startActivity(new Intent(getApplicationContext(), Anasayfa.class));

        */


      boolean bosAlanVArmı= true;
        if (girisYapKontrol){
            for (int i = 0; i<2; i++) {
                if (editTextsLists.get(i).getText().length()==0){
                    editTextsLists.get(i).setBackgroundColor(Color.RED);
                    bosAlanVArmı = true;
                }else {
                    editTextsLists.get(i).setBackgroundColor(Color.WHITE);
                    bosAlanVArmı = false;
                }
            }
        }else {
            for (EditText text:editTextsLists) {
                if (text.getText().length() == 0){
                    text.setBackgroundColor(Color.RED);
                    bosAlanVArmı = true;
                }else {
                    text.setBackgroundColor(Color.WHITE);
                    bosAlanVArmı = false;
                }
            }
        }


        if(bosAlanVArmı == false){
            kullaniciAdi = kullaniAdi_Edittext.getText().toString();
            sifre = sifre_Edittext.getText().toString();
            ad= ad_Edittext.getText().toString();
            soyAd = soyad_Edittext.getText().toString();
            mail = mail_Edittext.getText().toString();
            KullaniciIslemleri kl = new KullaniciIslemleri();

            if(girisYapKontrol){
               k_Id = kl.GirisYap(vt,kullaniciAdi,sifre);

               if(k_Id !=-1){
                   startActivity(new Intent(getApplicationContext(),Anasayfa.class));
               }else {
                   Toast.makeText(this,"Kullanıcı Adı ve Şifreniz Yanlış",Toast.LENGTH_LONG).show();
               }
            }else {

                if(kl.kullaniciVarmi(vt,kullaniciAdi,mail).equals("Yok")){
                    if(kl.kullaniciKaydet(vt,ad,soyAd,mail,kullaniciAdi,sifre)){
                        k_Id=kl.ID_Bul(vt,kullaniciAdi);
                        new Puan().puanKaydet(vt,k_Id);
                        startActivity(new Intent(getApplicationContext(),Anasayfa.class));
                    }
                }
                else {
                    Toast.makeText(this,"Sistemimizde bu kullanıcı adı ve mail bulunmaktadır",Toast.LENGTH_LONG).show();
                }

            }


/*

            if(girisYapKontrol){
               if(kullaniciIslemleriParse.kullaniciKaydet2(ad,soyAd,mail,kullaniciAdi,sifre)){
                   s_k_Id = kullaniciIslemleriParse.kullaniciGirisi(kullaniciAdi,sifre);
                   if (s_k_Id.length()>0){
                       startActivity(new Intent(getApplicationContext(),Anasayfa.class));
                   }
               }
            }else {
                s_k_Id = kullaniciIslemleriParse.kullaniciGirisi(kullaniciAdi,sifre);
                if (s_k_Id.length()>0){
                    startActivity(new Intent(getApplicationContext(),Anasayfa.class));
                }


            }
*/
        }

    }
}