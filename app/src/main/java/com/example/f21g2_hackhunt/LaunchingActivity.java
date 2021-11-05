package com.example.f21g2_hackhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class LaunchingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(LaunchingActivity.this, MainActivity.class));
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 3000);

    }
}