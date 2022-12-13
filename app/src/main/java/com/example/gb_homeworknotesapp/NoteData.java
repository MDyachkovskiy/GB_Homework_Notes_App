package com.example.gb_homeworknotesapp;

import android.os.Parcel;
import android.os.Parcelable;

public class NoteData implements Parcelable{
    
    private String title;
    private String description;
    private String creationDate;

    public NoteData(String title, String description, String creationDate) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
    }

    protected NoteData(Parcel in) {
        title = in.readString();
        description = in.readString();
        creationDate = in.readString();
    }

    public static final Creator<NoteData> CREATOR = new Creator<NoteData>() {
        @Override
        public NoteData createFromParcel(Parcel in) {
            return new NoteData(in);
        }

        @Override
        public NoteData[] newArray(int size) {
            return new NoteData[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(creationDate);
    }
}
