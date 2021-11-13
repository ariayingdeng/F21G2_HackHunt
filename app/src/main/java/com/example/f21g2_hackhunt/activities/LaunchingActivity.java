package com.example.f21g2_hackhunt.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.f21g2_hackhunt.R;

import java.util.Timer;
import java.util.TimerTask;

public class LaunchingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching);

        setTitle("Hack Hunt");

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(LaunchingActivity.this, LoginActivity.class));
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 3000);

    }
}