package com.example.manajemenmahasiswaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //       @author: Muhamad Nurul Khaqim
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //       @author: Muhamad Nurul Khaqim
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new FakultasFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_fakultas);
        }

    }
//    @author: Muhamad Nurul Khaqim
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

//    @author: Muhamad Nurul Khaqim
    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }
}