package com.example.oyunlastirmauygulamasi.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oyunlastirmauygulamasi.Kullanicilar;
import com.example.oyunlastirmauygulamasi.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class LiderTahtasiListAdapter extends RecyclerView.Adapter<LiderTahtasiListAdapter.CardViewNesneTutucu> {
    Context contex;
    ArrayList<Kullanicilar> kullanıcıListe;
    int kullaniciID;
    int posisionKontrol;


    public LiderTahtasiListAdapter(Context contex, ArrayList<Kullanicilar> kullanıcıListe, int kullaniciID, int posisionKontrol) {
        this.contex = contex;
        this.kullanıcıListe = kullanıcıListe;
        this.kullaniciID = kullaniciID;
        this.posisionKontrol = posisionKontrol;
    }
    public class CardViewNesneTutucu extends RecyclerView.ViewHolder {
        TextView kullanıcıADi;
        TextView puan;
        TextView siralama;
        CardView cardView;
        LinearLayout linearLayout;
        public CardViewNesneTutucu(@NonNull View itemView) {
            super(itemView);
            kullanıcıADi = itemView.findViewById(R.id.liiderTahtasi_txt_kullanıcıAdi);
            puan = itemView.findViewById(R.id.liiderTahtasi_txt_puan);
            siralama = itemView.findViewById(R.id.liiderTahtasi_txt_siralama);
            cardView = itemView.findViewById(R.id.liiderTahtasi_card);
            linearLayout = itemView.findViewById(R.id.liiderTahtasi_linearLayouth);
        }
    }

    @NonNull
    @Override
    public CardViewNesneTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapte_lider_tastasi,parent,false);

        return new CardViewNesneTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewNesneTutucu holder, int position) {
        Kullanicilar kullanıcı = kullanıcıListe.get(position);


            if(position < 3){
                holder.siralama.setText(String.valueOf(position + 1));
                holder.kullanıcıADi.setText(kullanıcı.getK_nickName());
                holder.puan.setText(String.valueOf(kullanıcı.getPuan()));
            }else {
                holder.siralama.setText(String.valueOf(posisionKontrol+1));
                holder.kullanıcıADi.setText(kullanıcı.getK_nickName());
                holder.puan.setText(String.valueOf(kullanıcı.getPuan()));
            }
            if(kullanıcı.getK_ID() == kullaniciID){
                holder.linearLayout.setBackgroundColor(Color.MAGENTA);
            }

    }
    @Override
    public int getItemCount() {

        return kullanıcıListe.size();
    }


}
