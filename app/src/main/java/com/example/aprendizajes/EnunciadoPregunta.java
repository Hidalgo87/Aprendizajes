package com.example.aprendizajes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EnunciadoPregunta extends AppCompatActivity {

    String correo, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enunciado_pregunta);
        Intent i = getIntent();
        correo = i.getStringExtra("correo");
        password = i.getStringExtra("password");
    }

    public void IrAEncuesta(View view){
        Intent i = new Intent(this, Encuesta.class);
        i.putExtra("correo", correo);
        i.putExtra("password", password);
        startActivity(i);
    }
}