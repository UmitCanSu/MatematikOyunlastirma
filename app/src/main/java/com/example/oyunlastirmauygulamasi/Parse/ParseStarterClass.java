package com.example.oyunlastirmauygulamasi.Parse;
import android.app.Application;

import com.parse.Parse;


public class ParseStarterClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("LVz0pUWzSkGnEx4IlaMXj1uWIvz3M5kKq34kHhRK")
                .clientKey("Wjcp8eA7tPsx4y9uFVrYqTdaRGwZonsOuBOHZwsv")
                .server("https://parseapi.back4app.com/")
                .build()
        );


    }
}
