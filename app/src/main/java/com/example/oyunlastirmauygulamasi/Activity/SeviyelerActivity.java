package com.example.oyunlastirmauygulamasi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.oyunlastirmauygulamasi.Adapter.SeviyelerAdapter;
import com.example.oyunlastirmauygulamasi.R;
import com.example.oyunlastirmauygulamasi.VeriTabani.Puan;
import com.example.oyunlastirmauygulamasi.VeriTabani.VeriTabani;

import java.util.ArrayList;

import static com.example.oyunlastirmauygulamasi.Activity.MainActivity.k_Id;

public class SeviyelerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seviyeler);

        ArrayList<Integer> list = new ArrayList<>();
        for (int i =1; i<100;i++){
            list.add(i);
        }
        int sonSeviye= new Puan().seviyeGetir(new VeriTabani(this),k_Id);
        SharedPreferences sp = getSharedPreferences("hafiza",MODE_PRIVATE);
        RecyclerView rv = findViewById(R.id.activit_seviyeler_rv);
        rv.setHasFixedSize(true);

        SeviyelerAdapter adapter = new SeviyelerAdapter(this,this,list,sp,sonSeviye);
        rv.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL));
        rv.setAdapter(adapter);


    }
}