package com.example.f21g2_hackhunt.interfaces;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.f21g2_hackhunt.model.Post;

import java.util.List;

@Dao
public interface PostDao {

    @Insert(onConflict = IGNORE)
    void insertPost(Post post);

    @Insert(onConflict = IGNORE)
    void insertPostsList(List<Post> posts);

    @Query("SELECT * FROM posts")
    List<Post> getAllPosts();

    @Query("DELETE FROM posts WHERE postid = :postId")
    int deletePost(String postId);

    @Query("UPDATE posts SET caption = :newCaption WHERE postid = :postId")
    int updateCaption(String newCaption, String postId);
}
