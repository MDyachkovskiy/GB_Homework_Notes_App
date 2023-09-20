package com.example.gb_homeworknotesapp;

import java.util.List;

public interface NoteSource {

    NoteData getNoteData(int position);

    int size();

    int indexOf(NoteData note);

    void deleteNote(int position);

    void updateNote(int position, NoteData note);

    void addNote(NoteData note);

    void setNewData(List<NoteData> dataSource);

    List<NoteData> getNoteData();

}
