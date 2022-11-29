package com.example.gb_homeworknotesapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.gb_homeworknotesapp.menu_fragments.InformationFragment;
import com.example.gb_homeworknotesapp.menu_fragments.ProfileFragment;
import com.example.gb_homeworknotesapp.menu_fragments.SettingsFragment;
import com.example.gb_homeworknotesapp.menu_fragments.SupportFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDrawer();

        initFragmentLayout(savedInstanceState);

    }

    private void initDrawer() {

        DrawerLayout mainLayout = findViewById(R.id.main_layout);

        findViewById(R.id.menu_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.menuProfile:

                        getSupportFragmentManager()
                                .beginTransaction()
                                .addToBackStack("tag")
                                .replace(R.id.fragment_container, new ProfileFragment())
                                .commit();
                        return true;
                    case R.id.menuSettings:

                        getSupportFragmentManager()
                                .beginTransaction()
                                .addToBackStack("tag")
                                .replace(R.id.fragment_container, new SettingsFragment())
                                .commit();
                        return true;

                    case R.id.menuSupport:

                        getSupportFragmentManager()
                                .beginTransaction()
                                .addToBackStack("tag")
                                .replace(R.id.fragment_container, new SupportFragment())
                                .commit();
                        return true;

                    case R.id.menuInfo:

                        getSupportFragmentManager()
                                .beginTransaction()
                                .addToBackStack("tag")
                                .replace(R.id.fragment_container, new InformationFragment())
                                .commit();
                        return true;
                }
                return false;
            }
        });
    }

    private void initFragmentLayout(Bundle savedInstanceState) {

        if (savedInstanceState == null) getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new NotesListFragment())
                .commit();
    }

}