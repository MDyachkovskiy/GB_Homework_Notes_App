package com.example.gb_homeworknotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            initLandFragment();

        } else {
            initPortFragment();
        }
    }

    private void initPortFragment() {
        String[] titles = getResources().getStringArray(R.array.titles);

        for (int i = 0; i < titles.length; i++) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,
                            new NotesListFragment()).commit();
        }

    }

    private void initLandFragment() {

        String[] titles = getResources().getStringArray(R.array.titles);

        for (int i = 0; i <titles.length; i++) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_1,
                            new NotesListFragment()).commit();
        }
    }
}
