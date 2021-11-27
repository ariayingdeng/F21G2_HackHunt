package com.example.f21g2_hackhunt.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts")
public class Post {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "postid")
    private String postId;

    @ColumnInfo(name = "posttime")
    private String postTime;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "caption")
    private String caption;

    public  Post() {

    }

    public Post(@NonNull String postId, String postTime, String username, String caption) {
        this.postId = postId;
        this.postTime = postTime;
        this.username = username;
        this.caption = caption;
    }

    @NonNull
    public String getPostId() {
        return postId;
    }

    public void setPostId(@NonNull String postId) {
        this.postId = postId;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
