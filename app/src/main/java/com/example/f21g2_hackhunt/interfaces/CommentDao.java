package com.example.f21g2_hackhunt.interfaces;

import static androidx.room.OnConflictStrategy.IGNORE;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.f21g2_hackhunt.model.Comment;

import java.util.List;

@Dao
public interface CommentDao {
    @Insert(onConflict = IGNORE)
    void insertComment(Comment comment);

    @Insert(onConflict = IGNORE)
    void insertCommentList(List<Comment> comment);

    @Query("SELECT * FROM comments WHERE postId = :pId")
    List<Comment> getAllCommentsForPost(String pId);


}
