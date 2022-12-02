package com.example.gb_homeworknotesapp.menu_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gb_homeworknotesapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class InformationFragment extends Fragment {


    public InformationFragment() {
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
        return inflater.inflate(R.layout.fragment_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton buttonBack = (FloatingActionButton) view.findViewById(R.id.btnBack);
        if (buttonBack != null)
            buttonBack.setOnClickListener(view1 -> {
                requireActivity().getSupportFragmentManager().popBackStack();
            });
    }
}