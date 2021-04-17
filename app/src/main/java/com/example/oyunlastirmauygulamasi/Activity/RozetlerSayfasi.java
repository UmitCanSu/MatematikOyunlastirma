package com.example.oyunlastirmauygulamasi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.example.oyunlastirmauygulamasi.Adapter.RozetAdapter;
import com.example.oyunlastirmauygulamasi.R;
import com.example.oyunlastirmauygulamasi.VeriTabani.RozetlerVt;
import com.example.oyunlastirmauygulamasi.VeriTabani.VeriTabani;
import com.example.oyunlastirmauygulamasi.Rozetler;


import java.util.ArrayList;

import static com.example.oyunlastirmauygulamasi.Activity.MainActivity.k_Id;

public class RozetlerSayfasi extends AppCompatActivity {

    ArrayList<Rozetler> rozetlerArrayList;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rozetler_sayfasi);
        rv = findViewById(R.id.rozetlerSayfasi_rv);

        rozetlerArrayList = new ArrayList<>();

        rozetlerArrayList.add(new Rozetler("R1","Zaman Ustası","3 Yıldızla seviye tamamlamanız gerek",R.drawable.r1_timer));
        /*
        rozetlerArrayList.add(new Rozetler("R2","Casus","",R.drawable.casus));
        rozetlerArrayList.add(new Rozetler("R3","Rotkit","",R.drawable.rotkit));
        rozetlerArrayList.add(new Rozetler("R4","TruvaAti","",R.drawable.truvaati));
*/
        ArrayList<String> rozetIDlist= new ArrayList<>();
        /*
        rozetIDlist.add("R1");
        rozetIDlist.add("R2");
        rozetIDlist.add("R3");
        rozetIDlist.add("R4");
        */
        VeriTabani vt = new VeriTabani(this);


        rozetIDlist = new RozetlerVt().toplananRozetler(vt,k_Id);

        RozetAdapter adapter = new RozetAdapter(rozetlerArrayList,rozetIDlist,this);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

    }
}