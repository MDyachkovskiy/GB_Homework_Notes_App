package com.example.gb_homeworknotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //для инициализации

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new NotesListFragment())
                .commit();
    }
}