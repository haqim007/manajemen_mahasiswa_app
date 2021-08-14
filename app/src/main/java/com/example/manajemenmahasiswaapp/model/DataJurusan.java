package com.example.manajemenmahasiswaapp.model;

public class DataJurusan {
    String id, code, name, fakultas;

    public DataJurusan(){

    }

    public DataJurusan(String id, String code, String name, String Fakultas){
        this.id = id;
        this.code = code;
        this.name = name;
        this.fakultas = fakultas;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
