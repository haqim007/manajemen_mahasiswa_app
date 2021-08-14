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
import com.example.manajemenmahasiswaapp.adapter.AdapterJurusan;
import com.example.manajemenmahasiswaapp.helper.Helper;
import com.example.manajemenmahasiswaapp.model.DataJurusan;
import com.example.manajemenmahasiswaapp.model.DataJurusan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
*
* @author: Muhamad Nurul Khaqim
*
* */
public class JurusanFragment extends Fragment {
    ListView listView;
    AlertDialog.Builder dialog;
    List<DataJurusan> listsJurusan = new ArrayList<>();
    AdapterJurusan adapterJurusan;
    Helper db = new Helper(getContext());
    Button btnAddJurusan;
    MainActivity packageContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_jurusan, container, false);
        db = new Helper(getContext());
        btnAddJurusan = (Button) view.findViewById(R.id.btn_add_jurusan);
        btnAddJurusan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FormJurusanActivity.class);
                startActivity(intent);
            }
        });
        listView = view.findViewById(R.id.lists_jurusan);
        adapterJurusan = new AdapterJurusan(getActivity(), listsJurusan);
        listView.setAdapter(adapterJurusan);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String id = listsJurusan.get(i).getId();
                final String code = listsJurusan.get(i).getCode();
                final String name = listsJurusan.get(i).getName();
                final String fakultas_id = listsJurusan.get(i).getFakultas();
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(getActivity());
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0: //edit
                                Intent intent = new Intent(getActivity(), FormJurusanActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("code", code);
                                intent.putExtra("name", name);
                                intent.putExtra("fakultas_id", fakultas_id);
                                startActivity(intent);
                                break;
                            case 1: //hapus
                                db.deleteJurusan(Integer.parseInt(id));
                                listsJurusan.clear();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getDataJurusan();
        return view;
    }

    /*
     * Retrieve data from database
     * */
    private void getDataJurusan(){
        ArrayList<HashMap<String, String>> rows = db.getAllJurusan();
        for (int i = 0; i < rows.size(); i++){
            String id = rows.get(i).get("id");
            String code = rows.get(i).get("code");
            String name = rows.get(i).get("name");
            String fakultas_name = rows.get(i).get("fakultas_name");

            DataJurusan data = new DataJurusan();
            data.setId(id);
            data.setCode(code);
            data.setName(name);
            data.setFakultas(fakultas_name);

            listsJurusan.add(data);
        }

        adapterJurusan.notifyDataSetChanged();
    }

    /*
     * Reload data after create/delete/update
     * */
    @Override
    public void onResume(){
        super.onResume();
        listsJurusan.clear();
        getDataJurusan();
    }
}
