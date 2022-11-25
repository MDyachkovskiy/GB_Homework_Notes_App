package com.example.gb_homeworknotesapp;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.os.BaseBundle;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.IconCompat;
import androidx.fragment.app.Fragment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Data extends Fragment implements Parcelable{

    private static Data[] notes;

    private String title;
    private String description;
    private String creationDate;

    protected Data(Parcel in) {
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
        parcel.writeString(title);
        parcel.writeString(description);
    }

    {
        notes = new Data[5];
        for (int i = 0; i < notes.length; i++) {
            notes[i] = initData(i);
        }
    }

    @SuppressLint("DefaultLocale")
    private Data initData(int i) {

        String[] titles = getResources().getStringArray(R.array.titles);
        String[] dates = getResources().getStringArray(R.array.dates);
        String[] notes = getResources().getStringArray(R.array.notes);

        String title = titles[i];
        String creationDate = dates[i];
        String description = notes[i];

        return new Data(title, description, creationDate);
    }

}
