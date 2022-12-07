package com.example.gb_homeworknotesapp;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class NotesListFragment_2 extends Fragment {

    static final String SELECTED_NOTE = "note";
    private NoteData selectedNote;
    private NoteSource data;
    View dataContainer;

    public static NotesListFragment_2 newInstance() {
        return new NotesListFragment_2();
    }

    public NotesListFragment_2() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        //if(dataSource== null) {
        //    dataSource = (NoteData) NoteSource.getNoteData(0);
       // }
        outState.putParcelable(SELECTED_NOTE, selectedNote);
        super.onSaveInstanceState(outState);
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

        data = new NoteSourceImpl(getResources()).init();

        initRecyclerView(recyclerView, data);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*FloatingActionButton buttonAdd = (FloatingActionButton) view.findViewById(R.id.btnAdd);
        buttonAdd.setOnClickListener(view1 -> {
            showEmptyNoteBlank();
        });*/

        // если в savedInstanceState что-то помещено, то извлекаем и помещаем в объект data
        if (savedInstanceState != null) {
            selectedNote =(NoteData) savedInstanceState.getParcelable(SELECTED_NOTE);
        }

        if (isLandscape()){
            showLandNotesBlank(selectedNote);
        }

        //dataContainer = view.findViewById(R.id.notes_recycler_view );

        //initRecyclerView((RecyclerView) dataContainer, dataSource);



    }

    public void initRecyclerView(){
        initRecyclerView();
    }

    private void initRecyclerView (RecyclerView recyclerView, NoteSource data) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        NotesListAdapter notesListAdapter = new NotesListAdapter(data);
        recyclerView.setAdapter(notesListAdapter);

        notesListAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showNotesBlankFragment(data.getNoteData(position));
            }
        });

    }

    private void showNotesBlankFragment(NoteData note) {
        if (isLandscape()) {
            showLandNotesBlank(note);
        } else {
            showPortNotesBlank(note);
        }
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void showLandNotesBlank(NoteData note) {
        //создание нового фрагмента
        NotesBlankFragment blankFragment = NotesBlankFragment.newInstance(note);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_2, blankFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showPortNotesBlank(NoteData note) {

        NotesBlankFragment blankFragment = NotesBlankFragment.newInstance(note);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, blankFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showEmptyNoteBlank (){
        if (isLandscape()){
            showEmptyLandNotesBlank();
        } else {
            showEmptyPortNotesBlank();
        }
    }

    private void showEmptyLandNotesBlank() {

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_2, new NotesBlankFragment())
                .addToBackStack("tag")
                .commit();
    }

    private void showEmptyPortNotesBlank() {

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new NotesBlankFragment())
                .addToBackStack("tag")
                .commit();
    }

}