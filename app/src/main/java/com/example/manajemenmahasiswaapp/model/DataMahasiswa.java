package com.example.manajemenmahasiswaapp.model;

public class DataMahasiswa {
    String id, name, email, jurusan, fakultas;

    public DataMahasiswa(){

    }

    public DataMahasiswa(String id, String email, String name, String jurusan, String fakultas){
        this.id = id;
        this.name = name;
        this.jurusan = jurusan;
        this.fakultas = fakultas;
        this.email = email;

    }

    public String getFakultas() {
        return fakultas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
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
