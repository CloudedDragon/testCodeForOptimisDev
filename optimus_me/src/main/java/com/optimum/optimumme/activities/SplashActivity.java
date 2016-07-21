package com.optimum.optimumme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.optimum.optimumme.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Bruce_Lan on 16/7/21.
 */
public class SplashActivity extends AppCompatActivity {

    AppCompatActivity activity;
    static private final long splashShowTime = 1600;
    static private final long splashDelay = 0;
    private TimerTask switchTask;
    private Timer switchTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        activity = this;

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        switchTask = new TimerTask() {
            @Override
            public void run() {

                Looper.prepare();
                try {
                    Thread.sleep(splashShowTime);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Intent goMainIntent = null;
                goMainIntent = new Intent().setClass(SplashActivity.this, MainActivity.class);
                startActivity(goMainIntent);
                finish();
                Looper.loop();
            }
        };

        switchTimer = new Timer();
        switchTimer.schedule(switchTask, splashDelay);
    }
}
