package com.example.f21g2_hackhunt.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "comments")
public class Comment {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="commentId")
    private int commentId;

    @NonNull
    @ColumnInfo(name="postId")
    private String postId;

    @ColumnInfo(name="comments")
    private String comments;

    @ColumnInfo(name="commenter")
    private String commenter;

    public Comment(){

    }

    public Comment(@NonNull String postId, String comments, String commenter) {
        this.postId = postId;
        this.comments = comments;
        this.commenter = commenter;
    }

    public Comment(int commentId, @NonNull String postId, String comments, String commenter) {
        this.commentId = commentId;
        this.postId = postId;
        this.comments = comments;
        this.commenter = commenter;
    }

    @NonNull
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(@NonNull int commentId) { this.commentId = commentId; }

    @NonNull
    public String getPostId() {
        return postId;
    }

    public void setPostId(@NonNull String postId) {
        this.postId = postId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }
}
