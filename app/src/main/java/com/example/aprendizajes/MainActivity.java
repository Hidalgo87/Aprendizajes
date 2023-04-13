package com.example.aprendizajes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void IrARegistro(View view){
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }

    public void IrAInicioSesion(View view){
        Intent i = new Intent(this, InicioSesion.class);
        startActivity(i);
    }
}