package com.example.gb_homeworknotesapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class NotesListFragment extends Fragment {

    // создание константы для хранения Parcelable значений
    static final String SELECTED_DATA ="data";

    // объявляем объект для хранения значений из фрагмента
    private Data data;

    // создание пустого конструктора
    public NotesListFragment () {
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        outState.putParcelable(SELECTED_DATA, data);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // создание фрагмента
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        FloatingActionButton buttonAdd = (FloatingActionButton) view.findViewById(R.id.btnAdd);
        buttonAdd.setOnClickListener(view1 -> {
                showEmptyNoteBlank();
            });

        // если в savedInstanceState что-то помещено, то извлекаем и помещаем в объект data
        if (savedInstanceState != null) {
            data = savedInstanceState.getParcelable(SELECTED_DATA);
        }

        initNotesList(view.findViewById(R.id.note_list_fragment));

    }

    private void initNotesList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        for (int i = 0; i < Data.getNotes().length; i++) {
            TextView tv = new TextView(getContext());
            tv.setText(Data.getNotes()[i].getTitle());
            tv.setTextSize(24);
            layoutView.addView(tv);

            final int index = i;
            tv.setOnClickListener(v -> {
                showNotesBlankFragment(Data.getNotes()[index]);
            });
        }
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void showNotesBlankFragment(Data data) {
        if (isLandscape()) {
            showLandNotesBlank(data);
        } else {
            showPortNotesBlank(data);
        }
    }

    private void showEmptyNoteBlank (){
        if (isLandscape()){
            showEmptyLandNotesBlank();
        } else {
            showEmptyPortNotesBlank();
        }
    }

    private void showLandNotesBlank(Data data) {
        //создание нового фрагмента
        NotesBlankFragment blankFragment = NotesBlankFragment.newInstance(data);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_2, blankFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showPortNotesBlank(Data data) {

        NotesBlankFragment blankFragment = NotesBlankFragment.newInstance(data);

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, blankFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
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
