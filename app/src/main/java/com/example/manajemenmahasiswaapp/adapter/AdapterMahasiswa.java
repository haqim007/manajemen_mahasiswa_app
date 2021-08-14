package com.example.manajemenmahasiswaapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.manajemenmahasiswaapp.R;
import com.example.manajemenmahasiswaapp.model.DataJurusan;
import com.example.manajemenmahasiswaapp.model.DataMahasiswa;

import java.util.List;

public class AdapterMahasiswa extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<DataMahasiswa> lists;

    public AdapterMahasiswa(Activity activity, List<DataMahasiswa> lists){
        this.activity = activity;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view == null){
            view = inflater.inflate(R.layout.card_mahasiswa, null);
        }else{
            TextView nim = view.findViewById(R.id.f_mahasiswa_id);
            TextView name = view.findViewById(R.id.f_mahasiswa_name);
            TextView email = view.findViewById(R.id.f_mahasiswa_email);
            TextView fakultas_name = view.findViewById(R.id.f_fakultas_name);
            TextView jurusan_name = view.findViewById(R.id.f_jurusan_name);
            DataMahasiswa data  = lists.get(i);
            name.setText(data.getName());
            nim.setText(data.getId());
            email.setText(data.getEmail());
            fakultas_name.setText(data.getFakultas());
            jurusan_name.setText(data.getJurusan());
        }
        return view;
    }
}
