package com.comparedost.ssgmce_bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import static java.lang.Thread.sleep;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent i=new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(i);

                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        });
        thread.start();

    }
}