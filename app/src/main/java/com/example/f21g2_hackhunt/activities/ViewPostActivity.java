package com.example.f21g2_hackhunt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.f21g2_hackhunt.R;
import com.example.f21g2_hackhunt.activities.UserPostsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ViewPostActivity extends UserPostsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        TextView txtViewDateL = findViewById(R.id.txtViewDateL);
        ImageView imgViewPostL = findViewById(R.id.imgViewPostL);
        TextView txtViewCapL = findViewById(R.id.txtViewCapL);

        Intent intent = getIntent();
        Bundle myBundle = intent.getExtras();
        String caption = myBundle.getString("CAPTION","");
        String date = myBundle.getString("DATE","");

        imgViewPostL.setImageBitmap(UserPostsActivity.currentBitmap);
        txtViewDateL.setText(date);
        txtViewCapL.setText(caption);

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottomNav5);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
            }
        });
    }
}