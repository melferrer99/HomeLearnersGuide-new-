package com.example.homelearnersguide;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
            .withFullScreen()
            .withTargetActivity(MainActivity.class)
            .withSplashTimeOut(4000)
            .withBackgroundColor(Color.parseColor("#1a1b29"))
            .withAfterLogoText("Cite Home Learners Guide")
            .withLogo(R.drawable.logo);

        config.getAfterLogoTextView().setTextColor(Color.WHITE);
        config.getAfterLogoTextView().setTextSize(30);


        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}