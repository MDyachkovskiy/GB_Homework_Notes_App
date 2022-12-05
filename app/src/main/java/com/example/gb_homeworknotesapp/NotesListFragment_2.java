package com.example.gb_homeworknotesapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class NotesListFragment_2 extends Fragment {


    public static NotesListFragment_2 newInstance() {
        return new NotesListFragment_2();
    }

    public NotesListFragment_2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_list_2, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.notes_recycler_view);

        NoteSource data = new NoteSourceImpl(getResources()).init();

        initRecyclerView(recyclerView, data);

        return view;
    }

    private void initRecyclerView (RecyclerView recyclerView, NoteSource data) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        NotesListAdapter notesListAdapter = new NotesListAdapter(data);
        recyclerView.setAdapter(notesListAdapter);
    }
}