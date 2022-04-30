package com.example.proyectopdm;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BD {
    Context context;
    Usuario usuario;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String db="BD";
    //Secuencia sql
    String tabla = "create table if not exists usuario (id integer primary key autoincrement, usuario text, contraseña text)";

    public BD(Context context){
        this.context=context;
        sql=context.openOrCreateDatabase(db,context.MODE_PRIVATE,null );
        sql.execSQL(tabla);
        usuario= new Usuario();

    }
    //Permite ingresar un nuevo usuario siempre y no haya ningun otro con dicho nombre de usuario
    public boolean insertUsuario(Usuario usuario){
        if(buscar(usuario.getUsuario())==0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("Usuario ",usuario.getUsuario());
            contentValues.put("Contraseña ",usuario.getContraseña());
            return (sql.insert("usuario",null,contentValues)>0);
        }else{
            return false;
        }

    }
    //Busca los usuarios
    public int buscar(String usuario){
        int x=0;
        lista=selectUsuarios();
        for (Usuario u:lista) {
            if(u.getUsuario().equals(usuario)){
                x++;
            }

        }
        return x;

    }
    //Retorna todos los usuarios de la DB
    public ArrayList<Usuario> selectUsuarios(){
        ArrayList<Usuario> lista =new ArrayList<Usuario>();
        lista.clear();
        Cursor cursor = sql.rawQuery("select * from usuario", null );
        if(cursor!=null&&cursor.moveToFirst()){
            do{
                Usuario usuario= new Usuario();
                usuario.setId(cursor.getInt(0));
                usuario.setUsuario(cursor.getString(1));
                usuario.setContraseña(cursor.getString(2));
                lista.add(usuario);
            }while (cursor.moveToNext());
        }
        return lista;
    }
    //Inicio de secion
    public int login(String u,String p){
        int a=0;
        Cursor cursor = sql.rawQuery("select * from usuario", null );
        if(cursor!=null&&cursor.moveToFirst()){
            do{
                if(cursor.getString(1).equals(u)&&cursor.getString(2).equals(p)){
                    a++;
                }
            }while (cursor.moveToNext());

        }
        return a;
    }
    public Usuario getUsuario(String u,String p){
        lista=selectUsuarios();
        for (Usuario usu:lista) {
            if(usu.getUsuario().equals(u)&&usu.getContraseña().equals(p)){
                return usu;
            }
        }
        return null;

    }
    public Usuario getUsuarioId(int id){
        lista=selectUsuarios();
        for (Usuario usu:lista) {
            if(usu.getId()==id){
                return usu;
            }
        }
        return null;

    }
}
