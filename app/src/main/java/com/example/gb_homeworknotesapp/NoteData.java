package com.example.gb_homeworknotesapp;

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
}
