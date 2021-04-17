package com.example.oyunlastirmauygulamasi.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oyunlastirmauygulamasi.R;
import com.example.oyunlastirmauygulamasi.Rozetler;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RozetAdapter extends RecyclerView.Adapter<RozetAdapter.CardViewNesneTutucu> {

    ArrayList<Rozetler> rozetlerArrayList;
    ArrayList<String> rozetIDList;
    Context context;

    public RozetAdapter(ArrayList<Rozetler> rozetlerArrayList, ArrayList<String> rozetIDList, Context context) {
        this.rozetlerArrayList = rozetlerArrayList;
        this.rozetIDList = rozetIDList;
        this.context = context;
    }

    public class CardViewNesneTutucu  extends RecyclerView.ViewHolder{
        ImageView resim;
        TextView text;
        CardView card;
        ConstraintLayout layout;
        public CardViewNesneTutucu(@NonNull View itemView) {
            super(itemView);
            resim = itemView.findViewById(R.id.rozet_resim);
            text = itemView.findViewById(R.id.rozet_adi);
            card = itemView.findViewById(R.id.rozet_card);
            layout = itemView.findViewById(R.id.rozet_layouth);

        }
    }

    @NonNull
    @Override
    public RozetAdapter.CardViewNesneTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rozetler,null,false);
        return new CardViewNesneTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RozetAdapter.CardViewNesneTutucu holder, int position) {
        holder.resim.setImageDrawable(context.getResources().getDrawable(rozetlerArrayList.get(position).getRozet_resim()));
        holder.text.setText(rozetlerArrayList.get(position).getRozetAdi()+"-->"+rozetIDList.size());
        holder.card.setAlpha(0.3f);
        if(rozetIDList.size()>position){
            if(rozetlerArrayList.get(position).getRID().equals( rozetIDList.get(position)) ){
                holder.card.setAlpha(1f);
            }
        }
/*
        if(rozetlerArrayList.get(position).getRozetAdi() == rozetIDList.get(position)){
            holder.card.setBackgroundColor(Color.WHITE);
        }else {
            holder.card.setBackgroundColor(Color.GRAY);
        }
*/
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rozetler rozet = rozetlerArrayList.get(position);
                AlertOlusturma(rozet.getRozetAdi(),rozet.getRozetTanitimYazisi(),rozet.getRozet_resim());
            }
        });
    }

    @Override
    public int getItemCount() {
        return rozetlerArrayList.size();
    }
    public void AlertOlusturma(String adi_metin, String icerik_metin, int resim_id){
        View view = LayoutInflater.from(context).inflate(R.layout.alert_rozet_tanitim_sayfasi,null,false);
        TextView adı = view.findViewById(R.id.rozet_tanitim_adi);
        TextView icerik = view.findViewById(R.id.rozet_tanitim_icerik);
        ImageView resim = view.findViewById(R.id.rozet_tanitim_resim);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        adı.setText(adi_metin);
        icerik.setText(icerik_metin);
        resim.setImageDrawable(context.getResources().getDrawable(resim_id));
        builder.setView(view);
        //builder.show();
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();



    }



}
