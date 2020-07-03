package com.sajalgupta.onboardingscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SlideActivity extends AppCompatActivity {
    public static ViewPager viewPager;
     SlideViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        viewPager=findViewById(R.id.viewpager);
        adapter=new SlideViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        if(isOpenAlready())
        {

        }
        else
        {
            SharedPreferences.Editor editor=getSharedPreferences("slide",MODE_PRIVATE).edit();
            editor.putBoolean("slide",true);
            editor.commit();
            
        }
    }

    private boolean isOpenAlready() {
        SharedPreferences sharedPreferences=getSharedPreferences("slide",MODE_PRIVATE);
        boolean result=sharedPreferences.getBoolean("slide",false);
        return result;
    }
}