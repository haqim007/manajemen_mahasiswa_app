package com.example.manajemenmahasiswaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.manajemenmahasiswaapp.adapter.AdapterFakultas;
import com.example.manajemenmahasiswaapp.helper.Helper;
import com.example.manajemenmahasiswaapp.model.DataFakultas;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //       @author: Muhamad Nurul Khaqim
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        *
        * @author: Muhamad Nurul Khaqim
        *
        * */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        * Create Menu Drawer
        * */
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*
        * Create toggle button
        * */
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        /*
        * Open navigation fakultas first on init
        * */
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new FakultasFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_fakultas);
        }

    }

    /*
    *
    * @author: Muhamad Nurul Khaqim
    * Action for navigation in drawer menu
    * */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){

        switch (item.getItemId()){
            case R.id.nav_fakultas:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new FakultasFragment())
                        .commit();
                break;
            case R.id.nav_jurusan:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new JurusanFragment())
                        .commit();
                break;
            case R.id.nav_mahasiswa:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new MahasiswaFragment())
                        .commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*
    * @author: Muhamad Nurul Khaqim
    *
    * If navigation drawer is open, pressing back button will not closing the activity
    * it just closes the drawer menu
    * */
    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }
}