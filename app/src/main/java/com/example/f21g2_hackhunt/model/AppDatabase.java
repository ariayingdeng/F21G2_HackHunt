package com.example.f21g2_hackhunt.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.f21g2_hackhunt.interfaces.RecommendationDao;
import com.example.f21g2_hackhunt.interfaces.UserDao;

@Database(entities = {Recommendation.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    //public abstract UserDao userDao();
    public abstract RecommendationDao recommendationDao();
}
