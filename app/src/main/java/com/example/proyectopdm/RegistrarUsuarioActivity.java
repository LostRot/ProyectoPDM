package com.example.proyectopdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarUsuarioActivity extends AppCompatActivity implements View.OnClickListener {
    EditText usuario,contraseña;
    Button btnRegistro;
    BD db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        usuario=(EditText) findViewById(R.id.registrarUser);
        contraseña=(EditText) findViewById(R.id.registrarPassword);
        btnRegistro=(Button) findViewById(R.id.btnRegistro);
        //Base de datos
        db = new BD(this);

        //Asignacion de eventos a los botones
        btnRegistro.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistro:
                //Llenado de usuario
                Usuario us=new Usuario();
                us.setUsuario(usuario.getText().toString());
                us.setContraseña(contraseña.getText().toString());
                if(!us.isNull()){
                    //Mensaje
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_SHORT).show();
                }else if(db.insertUsuario(us)){
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    Intent i2=new Intent(RegistrarUsuarioActivity.this,MainActivity.class);
                    startActivity(i2);
                    finish();
                }
                else{
                    Toast.makeText(this, "Usuario ya registrado", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}