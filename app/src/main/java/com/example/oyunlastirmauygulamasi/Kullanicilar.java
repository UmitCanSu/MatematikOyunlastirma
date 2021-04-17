package com.example.oyunlastirmauygulamasi;

import java.sql.Blob;

public class Kullanicilar {

    int k_ID;
    String k_nickName;
    String k_Ad;
    String k_Soyad;
    int puan;
    String eposta;
    byte[] resim;
    public Kullanicilar() {
    }

    public Kullanicilar(int k_ID, String k_nickName, String k_Ad, String k_Soyad, int puan, String eposta, byte[] resim) {
        this.k_ID = k_ID;
        this.k_nickName = k_nickName;
        this.k_Ad = k_Ad;
        this.k_Soyad = k_Soyad;
        this.puan = puan;
        this.eposta = eposta;
        this.resim = resim;
    }

    public int getK_ID() {
        return k_ID;
    }

    public String getK_nickName() {
        return k_nickName;
    }

    public String getK_Ad() {
        return k_Ad;
    }

    public String getK_Soyad() {
        return k_Soyad;
    }

    public int getPuan() {
        return puan;
    }

    public String getEposta() {
        return eposta;
    }

    public byte[] getResim() {
        return resim;
    }
}
