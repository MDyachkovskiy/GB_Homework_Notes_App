package com.example.gb_homeworknotesapp;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class NoteSourceImpl implements NoteSource {

    private static List<NoteData> dataSource;

    public NoteSourceImpl() {
        dataSource = new ArrayList<NoteData>();
    }

    /*public NoteSourceImpl init() {

        String[] titles = resources.getStringArray(R.array.titles);
        String[] descriptions = resources.getStringArray(R.array.descriptions);
        String[] creationDates = resources.getStringArray(R.array.creation_dates);

        for (int i = 0; i < 7; i++) {
            dataSource.add(new NoteData(titles[i], descriptions[i], creationDates[i]));
        }

        return this;
    }*/

    @Override
    public NoteData getNoteData(int position) {
        return dataSource.get(position);
    }

    @Override
    public int size() {
        return dataSource.size();
    }

    @Override
    public void deleteNote(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateNote(int position, NoteData note) {
        dataSource.set(position, note);
    }

    @Override
    public void addNote(NoteData note) {
        dataSource.add(note);
    }

    @Override
    public void setNewData(List<NoteData> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<NoteData> getNoteData() {
        return dataSource;
    }

    public int indexOf(NoteData note) {
        return dataSource.indexOf(note);
    }
}
