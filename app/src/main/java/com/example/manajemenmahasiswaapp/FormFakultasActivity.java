package com.example.manajemenmahasiswaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manajemenmahasiswaapp.helper.Helper;
/*
*
* @author: Muhamad Nurul Khaqim
* */
public class FormFakultasActivity extends AppCompatActivity {

    private EditText field_code;
    private EditText field_name;
    private Button btnSaveFakultas;
    private Helper db = new Helper(this);
    private String id, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_fakultas);

        field_name = findViewById(R.id.input_fakultas_name);
        field_code = findViewById(R.id.input_fakultas_code);
        btnSaveFakultas = findViewById(R.id.btn_save_fakultas);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");

        if(id == null || id.equals("")){
            setTitle("Tambah Data");
        }else{
            setTitle("Ubah Data");
            field_name.setText(name);
        }
        btnSaveFakultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(id == null || id.equals("")){
                        submitCreateFakultas();
                    }else{
                        submitEditFakultas();
                    }
                }catch (Exception e){
                    Log.e("saving ", e.getMessage());
                }
            }
        });
    }

    private void submitCreateFakultas(){
        if(String.valueOf(field_name.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
        }else{
            db.insertFakultas(field_code.getText().toString(), field_name.getText().toString());
            finish();
        }
    }

    private void submitEditFakultas(){
        if(String.valueOf(field_name.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
        }else{
            db.updateFakultas(Integer.parseInt(id), field_code.getText().toString(), field_name.getText().toString());
            finish();
        }
    }



}