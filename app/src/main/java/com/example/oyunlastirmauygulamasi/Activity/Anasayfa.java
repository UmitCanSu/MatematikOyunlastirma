package com.example.oyunlastirmauygulamasi.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.oyunlastirmauygulamasi.Adapter.LiderTahtasiListAdapter;
import com.example.oyunlastirmauygulamasi.Kullanicilar;
import com.example.oyunlastirmauygulamasi.R;
import com.example.oyunlastirmauygulamasi.VeriTabani.KullaniciIslemleri;
import com.example.oyunlastirmauygulamasi.VeriTabani.VeriTabani;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import static com.example.oyunlastirmauygulamasi.Activity.MainActivity.k_Id;

public class Anasayfa extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    VeriTabani vt;
    SharedPreferences sp;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);
        vt = new VeriTabani(this);
        recyclerView = findViewById(R.id.main_rclerView);
        navigationView = findViewById(R.id.anasayfa_navigationView);
        toolbar = findViewById(R.id.anasayfa_toolbar);
        drawerLayout = findViewById(R.id.anasfa_drawerLayout);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        KullaniciIslemleri kl = new KullaniciIslemleri();

        sp = getSharedPreferences("hafiza",MODE_PRIVATE);

        ArrayList<Kullanicilar> list = kl.tumKullanıcıList(vt);
        ArrayList<Kullanicilar> kullanicilarArrayList = new ArrayList<>();
        int possionKontrol = 0;
        for(int i =0; i<list.size(); i++){
            if(i<3 ){
                kullanicilarArrayList.add(list.get(i));
            }else if(list.get(i).getK_ID() ==k_Id){
                kullanicilarArrayList.add(list.get(i));
                possionKontrol = i;
            }
        }

        Log.e("36789", list.size()+">-<");
        LiderTahtasiListAdapter adapter = new LiderTahtasiListAdapter(this,kullanicilarArrayList,k_Id,possionKontrol);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

        View view = navigationView.inflateHeaderView(R.layout.navigasyon_baslik);
        TextView baslikIsim= view.findViewById(R.id.navigasyon_text);
        ImageView baslikResim = view.findViewById(R.id.navigasyon_resim);
        Kullanicilar kullanici = new KullaniciIslemleri().kullaniciBilgileri(vt,k_Id);
        baslikIsim.setText(kullanici.getK_nickName());

        if(kullanici.getResim() != null){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            options.outWidth = 300;
            options.outHeight = 300;
            Bitmap bitmap = BitmapFactory.decodeByteArray(kullanici.getResim(),0,kullanici.getResim().length,options);
            baslikResim.setImageBitmap(bitmap);
        }else{
            baslikResim.setImageDrawable(getResources().getDrawable(R.drawable.avatar));
        }


        navigationView.setNavigationItemSelectedListener(this);

    }



    public void btnClick(View view){
        Button btn = findViewById(view.getId());
        SharedPreferences.Editor  editor = sp.edit();
        editor.putString("isaret",btn.getText().toString());
        editor.commit();
        startActivity(new Intent(getApplicationContext(),SeviyelerActivity.class));

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.menu_Ayarlar){
            startActivity(new Intent(getApplicationContext(),AyarlarActivity.class));
        }else if(item.getItemId() == R.id.menu_Profil){
            startActivity(new Intent(getApplicationContext(),ProfilActivity.class));
        }else if(item.getItemId() == R.id.menu_Rozetler){
            startActivity(new Intent(getApplicationContext(),RozetlerSayfasi.class));
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}