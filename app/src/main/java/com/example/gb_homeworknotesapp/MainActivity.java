package com.example.gb_homeworknotesapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.gb_homeworknotesapp.menu_fragments.InformationFragment;
import com.example.gb_homeworknotesapp.menu_fragments.ProfileFragment;
import com.example.gb_homeworknotesapp.menu_fragments.SettingsFragment;
import com.example.gb_homeworknotesapp.menu_fragments.SupportFragment;
import com.google.android.material.navigation.NavigationView;

import java.security.AccessController;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDrawer();
        //initToolbar();

        initFragmentLayout(savedInstanceState);
    }

    //ошибки

    /*private void initToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }*/


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

                        initProfileFragment();
                        return true;
                    case R.id.menuSettings:

                       initSettingsFragment();
                        return true;

                    case R.id.menuSupport:

                        initSupportFragment();
                        return true;

                    case R.id.menuInfo:

                        initInformationFragment();
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

    private void initProfileFragment (){
        if(isLandscape()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("tag")
                    .replace(R.id.fragment_container_2, new ProfileFragment())
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("tag")
                    .replace(R.id.fragment_container, new ProfileFragment())
                    .commit();
        }
    }

    private void initSettingsFragment (){
        if(isLandscape()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("tag")
                    .replace(R.id.fragment_container_2, new SettingsFragment())
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("tag")
                    .replace(R.id.fragment_container, new SettingsFragment())
                    .commit();
        }
    }

    private void initSupportFragment (){
        if(isLandscape()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("tag")
                    .replace(R.id.fragment_container_2, new SupportFragment())
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("tag")
                    .replace(R.id.fragment_container, new SupportFragment())
                    .commit();
        }
    }

    private void initInformationFragment (){
        if(isLandscape()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("tag")
                    .replace(R.id.fragment_container_2, new InformationFragment())
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("tag")
                    .replace(R.id.fragment_container, new InformationFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_exit:
                ShowExitDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

    private void ShowExitDialog() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Внимание!")
                .setMessage("Подтвердите выход из приложения")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        Toast.makeText(MainActivity.this, "Вы закрыли приложение", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Нет", null)
                .show();
    }
}