package com.example.gb_homeworknotesapp;

import static androidx.appcompat.graphics.drawable.DrawableContainer.Api21Impl.getResources;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Data implements Parcelable {

    private static Data[] notes;

    private int id;
    private String title;
    private String description;
    private String creationDate;

    protected Data(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public static Data[] getNotes() {
        return notes;
    }

    public static void setNotes(Data[] notes) {
        Data.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Data(String title, String description, String creationDate) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
    }

    static {
        notes = new Data[5];
        for (int i = 0; i < notes.length; i++) {
            notes[i] = Data.initData(i);
        }
    }

    private static Data initData(int i) {

        String[] titles = getResources().getStringArray(R.array.titles);
        String[] dates = getResources().getStringArray(R.array.dates);
        String[] notes = getResources().getStringArray(R.array.notes);

        String title = titles[i];
        String creationDate = dates[i];
        String description = notes[i];

        return new Data(title, description, creationDate);
    }

}
