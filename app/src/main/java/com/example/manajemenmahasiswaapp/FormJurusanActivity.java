package com.example.manajemenmahasiswaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.manajemenmahasiswaapp.adapter.AdapterFakultas;
import com.example.manajemenmahasiswaapp.helper.Helper;
import com.example.manajemenmahasiswaapp.model.DataFakultas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FormJurusanActivity extends AppCompatActivity {

    private EditText field_code, field_name, field_fakultas_name;
    private Button btnSaveJurusan;
    private Helper db = new Helper(this);
    private String id, code, name, fakultas_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_jurusan);

        field_name = findViewById(R.id.input_jurusan_name);
        field_code = findViewById(R.id.input_jurusan_code);
        field_fakultas_name = findViewById(R.id.input_fakultas_name);
        btnSaveJurusan = findViewById(R.id.btn_save_jurusan);

        id = getIntent().getStringExtra("id");
        code = getIntent().getStringExtra("code");
        name = getIntent().getStringExtra("name");
        fakultas_name = getIntent().getStringExtra("fakultas_name");

        if(id == null || id.equals("")){
            setTitle("Tambah Data");
        }else{
            setTitle("Ubah Data");
            field_code.setText(code);
            field_name.setText(name);
            field_fakultas_name.setText(fakultas_name);
        }
        btnSaveJurusan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(id == null || id.equals("")){
                        submitCreateJurusan();
                    }else{
                        submitEditJurusan();
                    }
                }catch (Exception e){
                    Log.e("saving ", e.getMessage());
                }
            }
        });
    }

    private void submitCreateJurusan(){
        if(String.valueOf(field_name.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
        }else{
            db.insertJurusan(field_code.getText().toString(), field_name.getText().toString(), field_fakultas_name.getText().toString());
            finish();
        }
    }

    private void submitEditJurusan(){
        if(String.valueOf(field_name.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
        }else{
            db.updateJurusan(Integer.parseInt(id), field_code.getText().toString(), field_name.getText().toString(), field_fakultas_name.getText().toString());
            finish();
        }
    }


}