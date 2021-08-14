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

import com.example.manajemenmahasiswaapp.adapter.AdapterFakultas;
import com.example.manajemenmahasiswaapp.helper.Helper;
import com.example.manajemenmahasiswaapp.model.DataFakultas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
*
* @author: Muhamad Nurul Khaqim
*
* */
public class FakultasFragment extends Fragment {

    ListView listView;
    AlertDialog.Builder dialog;
    List<DataFakultas> listsFakultas = new ArrayList<>();
    AdapterFakultas adapterFakultas;
    Helper db = new Helper(getContext());
    Button btnAddFakultas;
    MainActivity packageContext;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_fakultas, container, false);
        db = new Helper(getContext());
        btnAddFakultas = (Button) view.findViewById(R.id.btn_add_fakultas);
        btnAddFakultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FormFakultasActivity.class);
                startActivity(intent);
            }
        });
        listView = view.findViewById(R.id.lists_fakultas);
        adapterFakultas = new AdapterFakultas(getActivity(), listsFakultas);
        listView.setAdapter(adapterFakultas);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String id = listsFakultas.get(i).getId();
                final String name = listsFakultas.get(i).getName();
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(getActivity());
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0: //edit
                                Intent intent = new Intent(getActivity(), FormFakultasActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("name", name);
                                startActivity(intent);
                                break;
                            case 1: //hapus
                                db.deleteFakultas(Integer.parseInt(id));
                                listsFakultas.clear();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getDataFakultas();
        return view;
    }
    /*
    * Retrieve data from database
    * */
    private void getDataFakultas(){
        ArrayList<HashMap<String, String>> rows = db.getAllFakultas();
        for (int i = 0; i < rows.size(); i++){
            String id = rows.get(i).get("id");
            String code = rows.get(i).get("code");
            String name = rows.get(i).get("name");

            DataFakultas data = new DataFakultas();
            data.setId(id);
            data.setCode(code);
            data.setName(name);
            listsFakultas.add(data);
        }

        adapterFakultas.notifyDataSetChanged();
    }

    /*
    * Reload data after create/delete/update
    * */
    @Override
    public void onResume(){
        super.onResume();
        listsFakultas.clear();
        getDataFakultas();
    }

}
