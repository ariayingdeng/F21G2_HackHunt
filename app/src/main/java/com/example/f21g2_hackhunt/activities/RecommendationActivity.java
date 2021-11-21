package com.example.f21g2_hackhunt.activities;

import androidx.annotation.NonNull;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.f21g2_hackhunt.R;
import com.example.f21g2_hackhunt.interfaces.RecommendationDao;
import com.example.f21g2_hackhunt.model.AppDatabase;
import com.example.f21g2_hackhunt.model.Recommendation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RecommendationActivity extends MainActivity {
    List<Recommendation> recommendationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);

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

        addRecommendData();
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "Recommendations.db").build();
        RecommendationDao recommendDao = db.recommendationDao();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
               try {
                   recommendDao.insertRecommendationsList(recommendationList);
               }
               catch (Exception ex) {
                   Log.d("DBrecommend","Database exception occured: " + ex.getMessage());
               }

            }
        });




    }

    private void addRecommendData() {
        recommendationList.add(new Recommendation("How to be more efficient in life",
                R.drawable.efficient, "https://www.youtube.com/watch?v=i5CHDZAam-k&t=6s&ab_channel=Lifehack"));
        recommendationList.add(new Recommendation("44 different ways to wear a scarf",
                R.drawable.scarves, "https://www.youtube.com/watch?v=Kn-tHS9rGug&ab_channel=APlusSchool"));
        recommendationList.add(new Recommendation("Quick breakfasts in 10 minutes",
                R.drawable.breakfast2, "https://www.youtube.com/watch?v=reSGygkhIi8&ab_channel=Tasty"));
        recommendationList.add(new Recommendation("Saving tips: How to save money fast",
                R.drawable.savemoney, "https://www.youtube.com/watch?v=0nWDufd0sLY&ab_channel=BRAINYDOSE"));
        recommendationList.add(new Recommendation("Hair hacks and tricks that really work",
                R.drawable.hairstyle, "https://www.youtube.com/watch?v=liA0LALnsDA&ab_channel=5-MinuteCrafts"));
        recommendationList.add(new Recommendation("Learn how to learn to study better!",
                R.drawable.study, "https://www.youtube.com/watch?v=EC9EDreZmAE&ab_channel=Lifehack"));

    }

}