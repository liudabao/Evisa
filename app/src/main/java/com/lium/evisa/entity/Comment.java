package com.lium.evisa.entity;

import java.util.Date;

/**
 * Created by lenovo on 2016/4/19.
 */
public class Comment {
    private String author;
    private String conent;
    private Date day;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getConent() {
        return conent;
    }

    public void setConent(String conent) {
        this.conent = conent;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
}
