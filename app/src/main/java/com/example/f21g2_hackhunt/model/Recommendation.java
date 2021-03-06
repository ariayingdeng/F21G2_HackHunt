package com.example.f21g2_hackhunt.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recommendations")
public class Recommendation {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "recommendid")
    private String recommendId;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "pic")
    private int pic;

    @ColumnInfo(name = "videolink")
    private String videoLink;

    public Recommendation() {

    }

    public Recommendation(@NonNull String recommendId, String description, int pic, String videoLink) {
        this.recommendId = recommendId;
        this.description = description;
        this.pic = pic;
        this.videoLink = videoLink;
    }

    public String getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(@NonNull String recommendId) {
        this.recommendId = recommendId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }
}
