package com.example.gb_homeworknotesapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;


public class NotesBlankFragment extends Fragment {

    private int index;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NotesBlankFragment fragment = new NotesBlankFragment();

        EditText titleOfNote = getView().findViewById(R.id.title_of_note);
        EditText dateOfNote = getView().findViewById(R.id.date_of_note);
        EditText bodyOfNote = getView().findViewById(R.id.body_of_note);

        String[] titles = getResources().getStringArray(R.array.titles);
        String[] dates = getResources().getStringArray(R.array.dates);
        String[] notes = getResources().getStringArray(R.array.notes);

        Bundle data = getArguments();
        if (data != null) {
            index = data.getInt("index");
        }
        titleOfNote.setText(titles[index]);
        dateOfNote.setText(dates[index]);
        bodyOfNote.setText(notes[index]);
    }


}