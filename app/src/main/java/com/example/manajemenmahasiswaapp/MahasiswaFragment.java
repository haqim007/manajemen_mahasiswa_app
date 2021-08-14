package com.example.manajemenmahasiswaapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.manajemenmahasiswaapp.adapter.AdapterJurusan;
import com.example.manajemenmahasiswaapp.adapter.AdapterMahasiswa;
import com.example.manajemenmahasiswaapp.helper.Helper;
import com.example.manajemenmahasiswaapp.model.DataJurusan;
import com.example.manajemenmahasiswaapp.model.DataMahasiswa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
*
* @author: Muhamad Nurul Khaqim
*
* */
public class MahasiswaFragment extends Fragment {

    ListView listView;
    AlertDialog.Builder dialog;
    List<DataMahasiswa> listsMahasiswa = new ArrayList<>();
    AdapterMahasiswa adapterMahasiswa;
    Helper db = new Helper(getContext());
    Button btnAddMahasiswa;
    MainActivity packageContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_mahasiswa, container, false);
        db = new Helper(getContext());
        btnAddMahasiswa = (Button) view.findViewById(R.id.btn_add_mahasiswa);
        btnAddMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FormMahasiswaActivity.class);
                startActivity(intent);
            }
        });
        listView = view.findViewById(R.id.lists_mahasiswa);
        adapterMahasiswa = new AdapterMahasiswa(getActivity(), listsMahasiswa);
        listView.setAdapter(adapterMahasiswa);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String nim = listsMahasiswa.get(i).getId();
                final String email = listsMahasiswa.get(i).getEmail();
                final String name = listsMahasiswa.get(i).getName();
                final String fakultas_name = listsMahasiswa.get(i).getFakultas();
                final String jurusan_name = listsMahasiswa.get(i).getJurusan();
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(getActivity());
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0: //edit
                                Intent intent = new Intent(getActivity(), FormMahasiswaActivity.class);
                                intent.putExtra("email", email);
                                intent.putExtra("jurusan_name", jurusan_name);
                                intent.putExtra("name", name);
                                intent.putExtra("fakultas_name", fakultas_name);
                                startActivity(intent);
                                break;
                            case 1: //hapus
                                db.deleteMahasiswa(Integer.parseInt(nim));
                                listsMahasiswa.clear();
                                getDataMahasiswa();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getDataMahasiswa();
        return view;
    }

    /*
     * Retrieve data from database
     * */
    private void getDataMahasiswa(){
        ArrayList<HashMap<String, String>> rows = db.getAllMahasiswa();
        for (int i = 0; i < rows.size(); i++){
            String nim = rows.get(i).get("nim");
            String email = rows.get(i).get("email");
            String name = rows.get(i).get("name");
            String fakultas_name = rows.get(i).get("fakultas_name");
            String jurusan_name = rows.get(i).get("jurusan_name");

            DataMahasiswa data = new DataMahasiswa();
            data.setId(nim);
            data.setEmail(email);
            data.setName(name);
            data.setFakultas(fakultas_name);
            data.setJurusan(jurusan_name);

            listsMahasiswa.add(data);
        }

        adapterMahasiswa.notifyDataSetChanged();
    }

    /*
     * Reload data after create/delete/update
     * */
    @Override
    public void onResume(){
        super.onResume();
        listsMahasiswa.clear();
        getDataMahasiswa();
    }
}
