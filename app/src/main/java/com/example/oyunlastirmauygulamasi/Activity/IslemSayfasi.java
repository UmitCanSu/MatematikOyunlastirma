package com.example.oyunlastirmauygulamasi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import com.example.oyunlastirmauygulamasi.R;
import com.example.oyunlastirmauygulamasi.VeriTabani.Puan;
import com.example.oyunlastirmauygulamasi.VeriTabani.RozetlerVt;
import com.example.oyunlastirmauygulamasi.VeriTabani.VeriTabani;

import java.util.ArrayList;
import java.util.Random;

import static com.example.oyunlastirmauygulamasi.Activity.MainActivity.k_Id;

public class IslemSayfasi extends AppCompatActivity {

    TextView sonucText;
    TextView soruText;
    int sayi1;
    int sayi2;
    int sonuc;
    SeekBar seekBar;
    TextView zamanText;
    int seekBarArtis = 100;
    int seviye=10;
    ArrayList<Integer> sayi1List;
    ArrayList<Integer> sayi2List;
    String isaret = "";
    int puan =0;
    TextView puanText;
    VeriTabani vt;
    int dogruSayisi =0;
    SharedPreferences sp ;
    boolean seviyeGecKontrol = true;
    CountDownTimer zaman2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_islem_sayfasi);
        sonucText = findViewById(R.id.islemSayfasiText_Sonuc);
        soruText = findViewById(R.id.islemSayfasiText_Soru);
        seekBar = findViewById(R.id.islem_seekBar_zaman);
        zamanText = findViewById(R.id.islem_text_zaman);
        puanText = findViewById(R.id.islemSayfasiText_Puan);

        sayi1List = new ArrayList<>();
        sayi2List = new ArrayList<>();
        sp = getSharedPreferences("hafiza",MODE_PRIVATE);
        vt =new VeriTabani(this);
        seviye = sp.getInt("seviye",10);
        puan = new Puan().puanGetir(vt,k_Id);
        puanText.setText(String.valueOf(puan));
        isaret = sp.getString("isaret","+");
        rakamUret();
        zaman();



    }/*
    @Override
    protected void onDestroy() {
        zaman().cancel();
        zaman().onFinish();
        super.onDestroy();

    }
    */

    @Override
    public void onBackPressed() {
        zaman2.cancel();
        super.onBackPressed();
    }

    void rakamUret() {
        Random random = new Random();
        sayi1 = random.nextInt(seviye*10);
        sayi2 = random.nextInt(seviye*10);

        while (sayi1 < seviye-10  || sayi2 < seviye-10 ) {

            if(sayi1 < seviye-10  ){
                sayi1 = random.nextInt(seviye);
            }else if (sayi2 < seviye-10  ){
                sayi2 = random.nextInt(seviye);
            }
        }

        if(isaret.equals("+")){
            sonuc = sayi1 + sayi2;
        }else if (isaret.equals("-")){
            sonuc = sayi1 - sayi2;
        }else if(isaret.equals("X")){
            sonuc = sayi1*sayi2;
        }else {
            sayi1 = sayi1*sayi2;
            sonuc = sayi1 / sayi2;
        }
        soruText.setText(sayi1 + " "+isaret+" " + sayi2);

    }
    public void numaraButtonClick(View view){
        Button btn = findViewById(view.getId());
        String girilenText= sonucText.getText()+btn.getText().toString();
        sonucText.setText(girilenText);
    }
    public void kontrolEtButton(View view){
        Button btn= findViewById(view.getId());

        if(Integer.valueOf(sonucText.getText().toString()) == sonuc){
           // rakamUret();
            dogruSayisi ++;
            if(dogruSayisi >3){

                seviye++;
                Log.e("Seviye",new Puan().seviyeGetir(vt,k_Id)+"");
                if(seviye>new Puan().seviyeGetir(vt,k_Id)){
                    new Puan().seviyeGuncelle(vt,k_Id,seviye);
                }
                seviyeGec();
            }else{
                rakamUret();
            }
            puan =puan + 30;

            sonucText.setText("");
        }else {
            seviyeGecKontrol = false;

            alertShow(R.drawable.carpi,"Yanliş Cevap \n Doğru Cevap: "+sonuc);
        }
    }
    public void ButtonClickSil(View view){
        sonucText.setText("");
    }
   public CountDownTimer zaman(){
       zamanText.setText(String.valueOf(seekBarArtis));
       seekBar.setProgress(seekBarArtis);
       /* CountDownTimer*/ zaman2= new CountDownTimer(10000,100) {
            @Override
            public void onTick(long l) {
                seekBarArtis =seekBarArtis -1;
                zamanText.setText(String.valueOf(seekBarArtis));
                seekBar.setProgress(seekBarArtis);
                puanText.setText(String.valueOf(puan));
            }

            @Override
            public void onFinish() {
                new Puan().puanGüncelle(vt,k_Id,puan);
                if(seviyeGecKontrol){
                    alertShow(R.drawable.zaman,"Süreniz Doldu");
                }

            }
        }.start();

        return  zaman2;
   }
   public void alertShow(int resimID, String mesaj){
        zaman2.cancel();
        View view = LayoutInflater.from(this).inflate(R.layout.alert_uyari_sayfasi,null,false);

        Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
       dialog.setContentView(R.layout.alert_uyari_sayfasi);
       ImageView resim = dialog.findViewById(R.id.alert_coustem_resim);
       Button yenidenDeneButton = dialog.findViewById(R.id.alert_coustem_yenidenButton);
       TextView text = dialog.findViewById(R.id.alert_coustem_text);
       resim.setImageDrawable(getResources().getDrawable(resimID));
       text.setText(mesaj);
       yenidenDeneButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               zaman2.cancel();
               SharedPreferences.Editor editor =sp.edit();
               editor.putInt("seviye",seviye);
               editor.commit();
               startActivity(new Intent(getApplicationContext(),IslemSayfasi.class));
               finish();
           }
       });
       dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
       dialog.show();
   }
   public Dialog seviyeGec(){

       //View view = LayoutInflater.from(this).inflate(R.layout.alert_seviyeyi_gecme_sayfasi,null,false);
        zaman2.cancel();
       Dialog dialog = new Dialog(this);
       dialog.setCancelable(false);
       dialog.setContentView(R.layout.alert_seviyeyi_gecme_sayfasi);
       Button yenidenDeneButton = dialog.findViewById(R.id.alert_seviye_gec_yenidendeneButton);
       Button devamEtDeneButton = dialog.findViewById(R.id.alert_seviye_gec_devametButton);
       TextView textView = dialog.findViewById(R.id.alert_seviye_gec_text);
       RatingBar ratingBar = dialog.findViewById(R.id.alert_seviye_gec_ratingBar);


       if (seekBar.getProgress()>0){
           ratingBar.setRating(3);

       }else if(seekBar.getProgress()>40){
           ratingBar.setRating(2);
       }else{
           ratingBar.setRating(1);
       }
       rozetler(ratingBar);
       textView.setText(seviye+". Bölüme Geçtiniz");
       yenidenDeneButton.setVisibility(View.GONE);
       yenidenDeneButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               zaman2.cancel();
               SharedPreferences.Editor editor =sp.edit();
               editor.putInt("seviye",seviye);
               editor.commit();

               startActivity(new Intent(getApplicationContext(),IslemSayfasi.class));
               finish();
           }
       });
       devamEtDeneButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              zaman2.cancel();
               SharedPreferences.Editor editor =sp.edit();
               editor.putInt("seviye",seviye);
               editor.commit();
               startActivity(new Intent(getApplicationContext(),IslemSayfasi.class));
               finish();
           }
       });
       seviyeGecKontrol = false;
       dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
       dialog.show();
       return dialog;
   }

   public void rozetler(RatingBar rt){
       if(rt.getRating() ==3){
           if (new RozetlerVt().rozetAlinmismiKontrol(vt,k_Id,"R1")<2){
               new RozetlerVt().rozetLeriEkle(vt,k_Id,"R1");
           }
       }
   }
}