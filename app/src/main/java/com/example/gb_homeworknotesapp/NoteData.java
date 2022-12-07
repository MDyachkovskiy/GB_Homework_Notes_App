package com.example.gb_homeworknotesapp;

import android.os.Parcelable;

public class NoteData {
    
    private String title;
    private String description;
    private String creationDate;

    public NoteData(String title, String description, String creationDate) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
