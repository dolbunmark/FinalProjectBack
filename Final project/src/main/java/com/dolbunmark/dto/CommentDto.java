package com.dolbunmark.dto;

public class CommentDto {

    private long id;
    private String text;
    private String date;
    private String user;


    public String getName() {
        return user;
    }

    public CommentDto() {
    }

    public CommentDto(String text, String date, String user) {
        this.text = text;
        this.date = date;
        this.user = user;
    }



    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
