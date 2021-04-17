package com.example.oyunlastirmauygulamasi;

public class Rozetler {
    String RID;
    String rozetAdi;
    String rozetTanitimYazisi;
    int rozet_resim;

    public Rozetler(String RID, String rozetAdi, String rozetTanitimYazisi, int rozet_resim) {
        this.RID = RID;
        this.rozetAdi = rozetAdi;
        this.rozetTanitimYazisi = rozetTanitimYazisi;
        this.rozet_resim = rozet_resim;
    }

    public String getRID() {
        return RID;
    }

    public String getRozetAdi() {
        return rozetAdi;
    }

    public String getRozetTanitimYazisi() {
        return rozetTanitimYazisi;
    }

    public int getRozet_resim() {
        return rozet_resim;
    }


}
