package com.example.android.university;

/**
 * Created by Ahmed on 8/13/2017.
 */

public class News {
    public String image;
    public String title;
    public String desc;

    public News(String image,String title,String desc){
        this.image=image;
        this.title=title;
        this.desc=desc;

    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
