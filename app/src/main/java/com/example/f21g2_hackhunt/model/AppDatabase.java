package com.example.f21g2_hackhunt.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.f21g2_hackhunt.interfaces.PostDao;
import com.example.f21g2_hackhunt.interfaces.RecommendationDao;

@Database(entities = {Recommendation.class, Post.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RecommendationDao recommendationDao();

    public  abstract PostDao postDao();
}
