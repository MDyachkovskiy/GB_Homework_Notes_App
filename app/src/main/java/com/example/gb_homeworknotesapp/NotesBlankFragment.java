package com.example.gb_homeworknotesapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class NotesBlankFragment extends Fragment {

    static final String SELECTED_DATA = "data";
    private Data data;

    // пустой конструктор
    public NotesBlankFragment() {
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            requireActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = getArguments();

        FloatingActionButton buttonBack = (FloatingActionButton) view.findViewById(R.id.btnBack);
        if (buttonBack != null)
            buttonBack.setOnClickListener(view1 -> {
                requireActivity().getSupportFragmentManager().popBackStack();
            });

        if (arguments != null) {
            data = arguments.getParcelable(SELECTED_DATA);

            TextView tvTitle = view.findViewById(R.id.title_of_note);
            tvTitle.setText(data.getTitle());
            tvTitle.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    data.setTitle(charSequence.toString());
                }
                @Override
                public void afterTextChanged(Editable editable) {
                }
            });

            TextView tvDescription = view.findViewById(R.id.body_of_note);
            tvDescription.setText(data.getDescription());

            TextView tvData = view.findViewById(R.id.date_of_note);
            tvData.setText(data.getCreationDate());
        }
    }

    public static NotesBlankFragment newInstance(Data data) {
        NotesBlankFragment fragment = new NotesBlankFragment();
        Bundle args = new Bundle();
        args.putParcelable(SELECTED_DATA,data);
        fragment.setArguments(args);
        return fragment;
    }


}