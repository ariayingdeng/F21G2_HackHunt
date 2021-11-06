package com.example.f21g2_hackhunt;

import java.util.Date;

public class Post {
    private String date;
    private int imgPic;
    private String caption;

    public Post(String date, int imgPic, String caption) {
        this.date = date;
        this.imgPic = imgPic;
        this.caption = caption;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImgPic() {
        return imgPic;
    }

    public void setImgPic(int imgPic) {
        this.imgPic = imgPic;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
