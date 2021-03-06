package com.example.f21g2_hackhunt.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.f21g2_hackhunt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.drawable.hack_hunt_logo);
        actionBar.setTitle("Hack Hunt");

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    return true;
                case R.id.recommendation:
                    startActivity(new Intent(getApplicationContext(), RecommendationActivity.class));
                    return true;
                case R.id.myPost:
                    startActivity(new Intent(getApplicationContext(), UserPostsActivity.class));
                    return true;
                case R.id.newPost:
                    startActivity(new Intent(getApplicationContext(), NewPostActivity.class));
                    return true;
                default:
                    return false;
            }
        });

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.profile:
                Log.i("Selected", "Edit Profile");
                startActivity(new Intent(getApplicationContext(), EditProfileActivity.class));
                return true;
            case R.id.logout:
                Log.i("Selected", "Log Out");
                ParseUser.logOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                return true;
            default:
                return false;
        }
    }
}