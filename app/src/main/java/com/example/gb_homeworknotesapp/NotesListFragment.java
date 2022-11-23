package com.example.gb_homeworknotesapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class NotesListFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNotesList(view);
    }

    private void initNotesList(View view) {
        FrameLayout noteListFragment = getView().findViewById(R.id.note_list_fragment);

        String[] titles = getResources().getStringArray(R.array.titles);
        String[] dates = getResources().getStringArray(R.array.dates);
        String[] notes = getResources().getStringArray(R.array.notes);

        TextView titleTextView = getView().findViewById(R.id.note_title);
        TextView dateTextView = getView().findViewById(R.id.note_date);
        TextView descriptionTextView = getView().findViewById(R.id.note_description);

        for (int i = 0; i < titles.length; i++) {

            titleTextView.setText(titles[i]);
            dateTextView.setText(dates[i]);
            descriptionTextView.setText(notes[i]);
            noteListFragment.setOnClickListener(v -> {
                showNotesBlankFragment(1);
            });
        }
    }

    private void showNotesBlankFragment(int index) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showLandNotesBlank(index);
        } else {
            showPortNotesBlank(index);
        }
    }

    private void showLandNotesBlank(int index) {
        //создание нового фрагмента
        NotesBlankFragment blankFragment = new NotesBlankFragment();

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Bundle data = new Bundle();
        data.putInt("index", index);
        blankFragment.setArguments(data);


        fragmentTransaction.replace(R.id.fragment_container_2, blankFragment);

        fragmentTransaction.addToBackStack("");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showPortNotesBlank(int index) {
        NotesBlankFragment blankFragment = new NotesBlankFragment();

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Bundle data = new Bundle();
        data.putInt("index", index);
        blankFragment.setArguments(data);

        fragmentTransaction.add(R.id.blank_fragment, blankFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }
}
