package com.example.manajemenmahasiswaapp.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/*
*
* @author: Muhamad Nurul Khaqim
* */
public class Helper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    static final String DATABASE_NAME = "manajemen_mahasiswa_db";

    public Helper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
    * Called when installed
    * */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLE_FAKULTAS = "CREATE TABLE fakultas " +
                "(id integer primary key autoincrement,code varchar(50) not null, name varchar(100) not null)";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_FAKULTAS);
        final String SQL_CREATE_TABLE_JURUSAN = "CREATE TABLE jurusan " +
                "(id integer primary key autoincrement, code varchar(50) not null, name varchar(100) not null, " +
                "fakultas_name varchar(100) not null)";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_JURUSAN);
        final String SQL_CREATE_TABLE_MAHASISWA = "CREATE TABLE mahasiswa " +
                "(nim integer primary key autoincrement, name varchar(100) not null, " +
                "email varchar(100) not null, fakultas_name varchar(100) not null, jurusan_name varchar(100) not null)";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_MAHASISWA);
    }

    /*
    * Called when database is upgraded
    * */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS fakultas");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS jurusan");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS mahasiswa");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<HashMap<String, String>> getAllFakultas(){
        ArrayList<HashMap<String, String>> lists = new ArrayList<>();

        String QUERY = "SELECT * FROM fakultas";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(QUERY, null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("code", cursor.getString(1));
                map.put("name", cursor.getString(2));

                lists.add(map);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return lists;
    }

    public ArrayList<HashMap<String, String>> getAllJurusan(){
        ArrayList<HashMap<String, String>> lists = new ArrayList<>();

        String QUERY = "SELECT jurusan.* FROM jurusan ";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(QUERY, null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("code", cursor.getString(1));
                map.put("name", cursor.getString(2));
                map.put("fakultas_name", cursor.getString(3));
                lists.add(map);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return lists;
    }

    public ArrayList<HashMap<String, String>> getAllMahasiswa(){
        ArrayList<HashMap<String, String>> lists = new ArrayList<>();

        String QUERY = "SELECT mahasiswa.* FROM mahasiswa ";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(QUERY, null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String, String> map = new HashMap<>();
                map.put("nim", cursor.getString(0));
                map.put("name", cursor.getString(1));
                map.put("jurusan_name", cursor.getString(2));
                map.put("fakultas_name", cursor.getString(3));
                lists.add(map);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return lists;
    }

    public void insertFakultas(String code, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "insert into fakultas (code, name) values ('"+code+"', '"+name+"')";
        db.execSQL(QUERY);
    }
    public void insertJurusan(String code, String name, String fakultas_name){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "insert into jurusan (code, name, fakultas_name) values ('"+code+"', '"+name+"', '"+fakultas_name+"')";
        db.execSQL(QUERY);
    }
    public void insertMahasiswa(String name, String email, String fakultas_name, String jurusan_name){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "insert into mahasiswa (name, email, fakultas_name, jurusan_name) values ('"+name+"','"+email+"', '"+fakultas_name+"', '"+jurusan_name+"')";
        db.execSQL(QUERY);
    }

    public void deleteFakultas(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "delete from fakultas where id="+id;
        db.execSQL(QUERY);
    }
    public void deleteJurusan(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "delete from jurusan where id="+id;
        db.execSQL(QUERY);
    }
    public void deleteMahasiswa(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "delete from mahasiswa where nim="+id;
        db.execSQL(QUERY);
    }

    public void updateFakultas(int id, String code, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "Update fakultas set code='"+code+"', name='"+name+"' where id="+id;
        db.execSQL(QUERY);
    }
    public void updateJurusan(int id, String code,String name, String fakultas_name){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "Update jurusan set code='"+code+"',name='"+name+"', fakultas_name='"+fakultas_name+"' where id="+id;
        db.execSQL(QUERY);
    }
    public void updateMahasiswa(int id, String name, String email, String fakultas_name, String jurusan_name){
        SQLiteDatabase db = this.getWritableDatabase();
        String QUERY = "Update mahasiswa set name='"+name+"', email='"+email+"', fakultas_name='"+fakultas_name+"', jurusan_name='"+jurusan_name+"' where id="+id;
        db.execSQL(QUERY);
    }

}
