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

import java.util.List;

public class AdapterFakultas extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<DataFakultas> lists;

    public AdapterFakultas(Activity activity, List<DataFakultas> lists){
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
            view = inflater.inflate(R.layout.card_fakultas, null);
        }else{
            TextView code = view.findViewById(R.id.f_fakultas_code);
            TextView name = view.findViewById(R.id.f_fakultas_name);
            DataFakultas data  = lists.get(i);
            code.setText(data.getCode());
            name.setText(data.getName());
        }
        return view;
    }
}
