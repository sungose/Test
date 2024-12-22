package com.example.model;

import java.util.Date;

public class Note {
    private int id;
    private Date date;
    private String title;
    private String notebook;
    private int userID;

    public Note(int id, Date date, String title, String notebook, int userID) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.notebook = notebook;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotebook() {
        return notebook;
    }

    public void setNotebook(String notebook) {
        this.notebook = notebook;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
