package com.example.oyunlastirmauygulamasi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oyunlastirmauygulamasi.Activity.IslemSayfasi;
import com.example.oyunlastirmauygulamasi.R;
import com.example.oyunlastirmauygulamasi.VeriTabani.Puan;
import com.example.oyunlastirmauygulamasi.VeriTabani.VeriTabani;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SeviyelerAdapter extends RecyclerView.Adapter<SeviyelerAdapter.CardViewNesneTutucu> {

        Activity activity ;

    Context context;
    ArrayList<Integer> seviyelerList;
    SharedPreferences sp;
    int sonSeviye;
    public SeviyelerAdapter(Activity activity, Context context, ArrayList<Integer> seviyelerList, SharedPreferences sp, int sonSeviye) {
        this.activity = activity;
        this.context = context;
        this.seviyelerList = seviyelerList;
        this.sp = sp;
        this.sonSeviye = sonSeviye;
    }

    public class CardViewNesneTutucu extends RecyclerView.ViewHolder {
        TextView sayi;
        CardView card;
        ImageView anahtar;


        public CardViewNesneTutucu(View view) {
            super(view);
            sayi = view.findViewById(R.id.adapter_seviyeler_sayi);
            card = view.findViewById(R.id.adapter_seviyeler_card);
            anahtar = view.findViewById(R.id.adapter_seviyeler_resim);

        }
    }
    @Override
    public CardViewNesneTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_seviyeler,null,false);
        return new CardViewNesneTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewNesneTutucu holder, int position) {
        holder.sayi.setText(String.valueOf(seviyelerList.get(position)));
/*
        if (position>=sonSeviye){
            holder.card.setEnabled(false);
            //holder.sayi.setAlpha(0.1f);
            holder.card.setAlpha(0.3f);
            // holder.card.setBackgroundColor(Color.BLUE);
        }else{
            System.out.println("Of-->"+position);
            holder.anahtar.setVisibility(View.INVISIBLE);
        }
*/
        if (position<sonSeviye){
            System.out.println("Of-->"+position);
            holder.anahtar.setVisibility(View.INVISIBLE);
            holder.card.setEnabled(true);
            holder.card.setAlpha(1f);
        }else{
            holder.card.setEnabled(false);
            holder.anahtar.setVisibility(View.VISIBLE);
            //holder.sayi.setAlpha(0.1f);
            holder.card.setAlpha(0.3f);
        }

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardView card  = activity.findViewById(view.getId());
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("seviye",(holder.getLayoutPosition()+1));
                editor.commit();


               context.startActivity(new Intent(context.getApplicationContext(), IslemSayfasi.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return seviyelerList.size();
    }
    public void nesneleriGöstermek(CardViewNesneTutucu holder){
        for (int i =0; i<seviyelerList.size(); i++){
            if (i>=sonSeviye){
                holder.card.setEnabled(false);
                holder.sayi.setAlpha(0.1f);
                holder.card.setAlpha(0.3f);
                // holder.card.setBackgroundColor(Color.BLUE);
            }else{
                holder.anahtar.setVisibility(View.INVISIBLE);
            }
        }
    }


}
