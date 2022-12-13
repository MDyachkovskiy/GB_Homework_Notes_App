package com.example.gb_homeworknotesapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;


public class NewNoteFragment extends Fragment {

    private NoteData note;

    public NewNoteFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            requireActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_note, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.note_menu, menu);

        MenuItem menu_exit = menu.findItem(R.id.action_exit);
        MenuItem menu_sort = menu.findItem(R.id.action_sorting);
        MenuItem menu_search = menu.findItem(R.id.action_search);

        if (menu_exit != null) {
            menu_exit.setVisible(false);
        }
        if (menu_sort != null) {
            menu_sort.setVisible(false);
        }
        if (menu_search != null) {
            menu_search.setVisible(false);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvTitle = view.findViewById(R.id.title_of_note);

        TextView tvDescription = view.findViewById(R.id.body_of_note);

        TextView tvData = view.findViewById(R.id.date_of_note);


        ImageView buttonBack = view.findViewById(R.id.btnBack);
        if (buttonBack != null)
            buttonBack.setOnClickListener(view1 -> {
                requireActivity().getSupportFragmentManager().popBackStack();
            });

        MaterialButton btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newTitle = tvTitle.getText().toString();
                String newDescription = tvDescription.getText().toString();
                String newDate = tvData.getText().toString();

                note = new NoteData(newTitle, newDescription, newDate);

                for (Fragment fragment : requireActivity()
                        .getSupportFragmentManager()
                        .getFragments())
                    if (fragment instanceof NotesListFragment_2) {
                        ((NotesListFragment_2<?>) fragment).addNote(note);
                    }
                requireActivity().getSupportFragmentManager().popBackStack();
                Toast.makeText(getContext(), "Вы добавили новую заметку", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

}