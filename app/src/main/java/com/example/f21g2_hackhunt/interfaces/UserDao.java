package com.example.f21g2_hackhunt.interfaces;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.f21g2_hackhunt.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = IGNORE)
    void insertUsers(User... users);

    @Insert(onConflict = IGNORE)
    void insertUser(User user);

    @Query("SELECT * FROM users")
    List<User> getAllUsers();

}
