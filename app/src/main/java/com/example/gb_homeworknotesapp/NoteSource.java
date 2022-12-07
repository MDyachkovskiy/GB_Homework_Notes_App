package com.example.gb_homeworknotesapp;

import java.util.List;

public interface NoteSource {

    NoteData getNoteData(int position);

    int size();

    List<NoteData> getAllNotes();

}
