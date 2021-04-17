package com.example.oyunlastirmauygulamasi.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import de.hdodenhof.circleimageview.CircleImageView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.oyunlastirmauygulamasi.Kullanicilar;
import com.example.oyunlastirmauygulamasi.R;
import com.example.oyunlastirmauygulamasi.VeriTabani.KullaniciIslemleri;
import com.example.oyunlastirmauygulamasi.VeriTabani.VeriTabani;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Permission;

import static com.example.oyunlastirmauygulamasi.Activity.MainActivity.k_Id;

public class ProfilActivity extends AppCompatActivity {

    CircleImageView resim;
    TextView kullaniciAdi;
    TextView e_posta;
    TextView ad_soyadi;
    TextView sifre;
    Bitmap selectedImage ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        resim = findViewById(R.id.profil_resim);
        kullaniciAdi = findViewById(R.id.profil_kullanıciAdi);
        e_posta = findViewById(R.id.profil_epostaAdresi);
        ad_soyadi = findViewById(R.id.profil_adSoyad);
        sifre = findViewById(R.id.profil_sifre);
        VeriTabani vt = new VeriTabani(this);
        Kullanicilar kullanici = new KullaniciIslemleri().kullaniciBilgileri(vt,k_Id);

        kullaniciAdi.setText(kullanici.getK_nickName());
        ad_soyadi.setText(kullanici.getK_Ad()+" "+kullanici.getK_Soyad());
        e_posta.setText(kullanici.getEposta());

        if(kullanici.getResim() != null){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            options.outWidth = 300;
            options.outHeight = 300;
            Bitmap bitmap = BitmapFactory.decodeByteArray(kullanici.getResim(),0,kullanici.getResim().length,options);
            resim.setImageBitmap(bitmap);
        }else{
            resim.setImageDrawable(getResources().getDrawable(R.drawable.avatar));
        }

    }
    public void profilResmiDegis(View view){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }else {
            Intent toGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(toGalery,2);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1){
            if(grantResults.length >0  && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent toGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(toGalery,2);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.e("hat","yok");
        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            Uri imageData = data.getData();

            try {

                if(Build.VERSION.SDK_INT>= 28){
                    ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(),imageData);
                    selectedImage = ImageDecoder.decodeBitmap(source);
                    resim.setImageBitmap(selectedImage);

                }else{
                    selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageData);
                    resim.setImageBitmap(selectedImage);

                }
                resmiKaydet();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void resmiKaydet(){

        Bitmap kucukResim = resimKucultme(selectedImage,300);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.PNG,50,byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();

        new KullaniciIslemleri().kullaniciResimKaydet(new VeriTabani(this),k_Id,bytes);
    }
    public Bitmap resimKucultme(Bitmap image, int maximumSize){
        int height = image.getHeight();
        int widht = image.getWidth();
        float bitmapRatio = (float) height/(float) widht;
        if(bitmapRatio> 1){
            widht = maximumSize;
            height = (int) (widht/bitmapRatio);
        }else {
            height = maximumSize;
            widht = (int) (height*bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image,widht,height,true);
    }
}