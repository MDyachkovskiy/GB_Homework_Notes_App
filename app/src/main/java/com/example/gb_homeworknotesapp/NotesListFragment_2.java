package com.example.gb_homeworknotesapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class NotesListFragment_2<dataContainer> extends Fragment {

    static final String SELECTED_NOTE = "note";
    private NoteData selectedNote;
    private NoteSource data;
    private RecyclerView recyclerView;
    private NotesListAdapter notesListAdapter;
    private SharedPreferences sharedPreferences;
    private static final String KEY = "KEY";

    public NotesListFragment_2() {
        // Required empty public constructor
    }

    public static NotesListFragment_2 newInstance() {
        return new NotesListFragment_2();
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

        initView(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton buttonAdd = (FloatingActionButton) view.findViewById(R.id.btnAdd);
        buttonAdd.setOnClickListener(view1 -> {
            showNewNoteBlank();
        });

        // если в savedInstanceState что-то помещено, то извлекаем и помещаем в объект data
        if (savedInstanceState != null) {
            selectedNote = (NoteData) savedInstanceState.getParcelable(SELECTED_NOTE);
        }


    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.notes_recycler_view);
        data = new NoteSourceImpl();
                //new NoteSourceImpl(getResources()).init();
        initRecyclerView();
    }


    private void initRecyclerView() {

        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        notesListAdapter = new NotesListAdapter(data);
        recyclerView.setAdapter(notesListAdapter);

        String savedData = sharedPreferences.getString(KEY, null);

        if (savedData == null) {
            Toast.makeText(getContext(), "Empty data", Toast.LENGTH_SHORT).show();
        } else {
            try {
                Type type = new TypeToken<List<NoteData>>() {}.getType();
                notesListAdapter.setNewData(new GsonBuilder().create().fromJson(savedData, type));
            }
            catch (Exception e){
                Toast.makeText(getContext(), "Data saving error", Toast.LENGTH_SHORT).show();
            }
        }

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

    private void showNewNoteBlank() {
        if (isLandscape()) {
            showLandNewNoteBlank();
        } else {
            showPortNewNoteBlank();
        }
    }

    private void showLandNewNoteBlank() {

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container_2, new NewNoteFragment())
                .addToBackStack("tag")
                .commit();
    }

    private void showPortNewNoteBlank() {

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new NewNoteFragment())
                .addToBackStack("tag")
                .commit();
    }

    public void deleteNote(NoteData note) {
        int position = data.indexOf(note);
        data.deleteNote(position);
        notesListAdapter.notifyItemRemoved(position);
        String jsonNoteDataAfterDelete = new GsonBuilder().create().toJson(data.getNoteData());
        sharedPreferences.edit().putString(KEY, jsonNoteDataAfterDelete).apply();
    }

    public void addNote(NoteData note) {
        data.addNote(note);
        notesListAdapter.notifyItemInserted(data.size() - 1);
        recyclerView.scrollToPosition(data.size() - 1);

        String jsonNoteDataAfterAdd = new GsonBuilder().create().toJson(data.getNoteData());
        sharedPreferences.edit().putString(KEY, jsonNoteDataAfterAdd).apply();
    }

    public void updateNote(int position, NoteData note) {
        data.updateNote(position, note);
        notesListAdapter.notifyDataSetChanged();

        String jsonNoteDataAfterUpdate = new GsonBuilder().create().toJson(data.getNoteData());
        sharedPreferences.edit().putString(KEY, jsonNoteDataAfterUpdate).apply();
    }

    public int indexOf(NoteData note) {
        return data.indexOf(note);
    }

}