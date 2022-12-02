package com.example.gb_homeworknotesapp;



import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Data implements Parcelable {


    private static ArrayList<Data> notes;
    private String title;
    private String description;
    private String creationDate;

    public static String[] titles = new String[] { "Запись1", "Запись2", "Запись3", "Запись4","Запись5"};

    public static String[] notes_body = new String[] {
            "Не следует, однако, забывать о том, что дальнейшее развитие различных форм деятельности напрямую зависит от системы обучения кадров",
            "Дорогие друзья, новая модель организационной деятельности создаёт предпосылки качественно новых шагов для соответствующих условий активизации?",
            "Практический опыт показывает, что реализация намеченного плана развития требует определения",
            "Значимость этих проблем настолько очевидна, что постоянное информационно-техническое обеспечение",
            "Соображения..."};

    public static String[] dates = new String[] {
            "01.01.2022",
            "02.03.2022",
            "12.04.2022",
            "18.08.2022",
            "23.10.2022" };

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

    static {
        notes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            notes.add(initData(i));
        }
    }


    public static ArrayList<Data> getNotes() {
        return notes;
    }


    public static Data initData(int i) {

        String title = titles[i];
        String creationDate = dates[i];
        String description = notes_body[i];

        return new Data(title, description, creationDate);
    }




}

