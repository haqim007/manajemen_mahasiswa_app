package com.example.manajemenmahasiswaapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.manajemenmahasiswaapp.R;
import com.example.manajemenmahasiswaapp.model.DataFakultas;
import com.example.manajemenmahasiswaapp.model.DataJurusan;

import java.util.List;

public class AdapterJurusan extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<DataJurusan> lists;

    public AdapterJurusan(Activity activity, List<DataJurusan> lists){
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
            view = inflater.inflate(R.layout.card_jurusan, null);
        }else{
            TextView code = view.findViewById(R.id.f_jurusan_code);
            TextView name = view.findViewById(R.id.f_jurusan_name);
            TextView fakultas_name = view.findViewById(R.id.f_fakultas_name);
            DataJurusan data  = lists.get(i);
            code.setText(data.getCode());
            name.setText(data.getName());
            fakultas_name.setText(data.getFakultas());
        }
        return view;
    }
}
