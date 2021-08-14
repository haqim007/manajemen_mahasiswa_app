package com.example.manajemenmahasiswaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manajemenmahasiswaapp.helper.Helper;

public class FormMahasiswaActivity extends AppCompatActivity {

    private EditText field_email, field_name, field_fakultas_name, field_jurusan_name;
    private Button btnSaveMahasiswa;
    private Helper db = new Helper(this);
    private String id, email, name, fakultas_name, jurusan_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_mahasiswa);

        field_name = findViewById(R.id.input_mahasiswa_name);
        field_email = findViewById(R.id.input_mahasiswa_email);
        field_fakultas_name = findViewById(R.id.input_fakultas_name);
        field_jurusan_name = findViewById(R.id.input_jurusan_name);
        btnSaveMahasiswa = findViewById(R.id.btn_save_mahasiswa);

        id = getIntent().getStringExtra("nim");
        email = getIntent().getStringExtra("email");
        name = getIntent().getStringExtra("name");
        fakultas_name = getIntent().getStringExtra("fakultas_name");
        jurusan_name = getIntent().getStringExtra("jurusan_name");

        if(id == null || id.equals("")){
            setTitle("Tambah Data");
        }else{
            setTitle("Ubah Data");
            field_email.setText(email);
            field_name.setText(name);
            field_fakultas_name.setText(fakultas_name);
            field_jurusan_name.setText(jurusan_name);
        }
        btnSaveMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(id == null || id.equals("")){
                        submitCreateMahasiswa();
                    }else{
                        submitEditMahasiswa();
                    }
                }catch (Exception e){
                    Log.e("saving ", e.getMessage());
                }
            }
        });
    }

    private void submitCreateMahasiswa(){
        if(String.valueOf(field_name.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
        }else{
            db.insertMahasiswa(field_name.getText().toString(), field_email.getText().toString(),
                    field_fakultas_name.getText().toString(), field_jurusan_name.getText().toString());
            finish();
        }
    }

    private void submitEditMahasiswa(){
        if(String.valueOf(field_name.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
        }else{
            db.updateMahasiswa(Integer.parseInt(id), field_name.getText().toString(), field_email.getText().toString(),
                    field_fakultas_name.getText().toString(), field_jurusan_name.getText().toString());
            finish();
        }
    }
}