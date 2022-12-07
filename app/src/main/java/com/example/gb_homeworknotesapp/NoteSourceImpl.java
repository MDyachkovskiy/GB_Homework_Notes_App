package com.example.gb_homeworknotesapp;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class NoteSourceImpl implements NoteSource {

    private List<NoteData> dataSource;
    private Resources resources;

    public NoteSourceImpl(Resources resources){
        this.resources = resources;
        dataSource = new ArrayList<>(7);
    }

    public NoteSourceImpl init() {

        String[] titles = resources.getStringArray(R.array.titles);
        String[] descriptions = resources.getStringArray(R.array.descriptions);
        String[] creationDates = resources.getStringArray(R.array.creation_dates);

        for (int i = 0; i < 7; i++) {
            dataSource.add(new NoteData(titles[i], descriptions[i], creationDates[i]));
        }

        return this;
    }

    @Override
    public NoteData getNoteData(int position) {
        return dataSource.get(position);
    }

    @Override
    public int size() {
        return dataSource.size();
    }
}
