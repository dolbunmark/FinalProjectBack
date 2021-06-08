package com.dolbunmark.dto;

import java.util.Date;

public class HistoryDto {
    private long id;
    private String date;
    private String user;
    private String action;
    private String description;


    public HistoryDto() {
    }

    public HistoryDto(String date, String user, String action, String description) {
        this.date = date;
        this.user = user;
        this.action = action;
        this.description = description;
    }

    public HistoryDto(long id, String date, String user, String action, String description) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.action = action;
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getUser() {
        return user;
    }

    public String getAction() {
        return action;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
