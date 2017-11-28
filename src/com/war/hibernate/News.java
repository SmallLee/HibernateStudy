package com.war.hibernate;

import java.util.Date;

public class News {
    private Integer id;
    private String title;
    private String content;
    private Date date;

    public Integer getId() {
        return id;
    }

    public News(){

    }

    public News(String title, String content, Date date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
