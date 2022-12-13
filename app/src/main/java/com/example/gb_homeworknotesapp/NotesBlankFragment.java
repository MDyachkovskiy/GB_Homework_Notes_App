package com.example.gb_homeworknotesapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
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


public class NotesBlankFragment extends Fragment {

    static final String SELECTED_NOTE = "note";
    private NoteData note;

    // пустой конструктор
    public NotesBlankFragment() {
    }

    public static NotesBlankFragment newInstance(NoteData note) {
        NotesBlankFragment fragment = new NotesBlankFragment();
        Bundle args = new Bundle();
        args.putParcelable(SELECTED_NOTE, note);
        fragment.setArguments(args);
        return fragment;
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
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_notes_blank, container, false);
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {

            ShowDeleteDialog();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = getArguments();

        if (arguments != null) {
            note = arguments.getParcelable(SELECTED_NOTE);

            TextView tvTitle = view.findViewById(R.id.title_of_note);
            tvTitle.setText(note.getTitle());

            TextView tvDescription = view.findViewById(R.id.body_of_note);
            tvDescription.setText(note.getDescription());

            TextView tvData = view.findViewById(R.id.date_of_note);
            tvData.setText(note.getCreationDate());

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
                    NoteData newNote = new NoteData(newTitle, newDescription, newDate);

                    for (Fragment fragment : requireActivity()
                            .getSupportFragmentManager()
                            .getFragments())
                        if (fragment instanceof NotesListFragment_2) {
                            int position = ((NotesListFragment_2<?>) fragment).indexOf(note);
                            ((NotesListFragment_2<?>) fragment).updateNote(position, newNote);
                        }
                    requireActivity().getSupportFragmentManager().popBackStack();
                    Toast.makeText(getContext(), "Вы добавили новую заметку", Toast.LENGTH_LONG).show();

                }
            });

        }
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void ShowDeleteDialog() {

        new AlertDialog.Builder(getContext())
                .setTitle("Внимание!")
                .setMessage("Подтвердите удаление заметки")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        for (Fragment fragment : requireActivity()
                                .getSupportFragmentManager()
                                .getFragments())
                            if (fragment instanceof NotesListFragment_2) {
                                ((NotesListFragment_2<?>) fragment).deleteNote(note);
                            }
                        Toast.makeText(getContext(), "Вы удалили заметку", Toast.LENGTH_LONG).show();
                        if (!isLandscape()) {
                            requireActivity().getSupportFragmentManager().popBackStack();
                        }
                    }
                })
                .setNegativeButton("Нет", null)
                .show();
    }
}
