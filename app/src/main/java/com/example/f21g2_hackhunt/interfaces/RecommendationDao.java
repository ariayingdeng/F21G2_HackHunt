package com.example.f21g2_hackhunt.interfaces;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.f21g2_hackhunt.model.Recommendation;

import java.util.List;

@Dao
public interface RecommendationDao {
    @Insert(onConflict = IGNORE)
    void insertRecommendation(Recommendation recommendation);

    @Insert(onConflict = IGNORE)
    void insertRecommendationsList(List<Recommendation> recommendations);

    @Query("SELECT * FROM recommendations")
    List<Recommendation> getAllRecommendations();



}
