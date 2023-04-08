package com.example.aprendizajes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InicioSesion extends AppCompatActivity {

    EditText tv_correo, tv_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
    }

    public void IniciarSesion(View view){
        tv_correo = (EditText) findViewById(R.id.et_CorreoInicioSesion);
        tv_password = (EditText) findViewById(R.id.et_PasswordInicioSesion);
        if(tv_correo.getText().toString().length() != 0 && tv_password.getText().toString().length() != 0){
            if(credenciales_validas(tv_correo.getText().toString(), tv_password.getText().toString()))
            {
                IrAEncuesta();
            }
            else {
                // Informar Credenciales inválidas
                Toast.makeText(this, "Las credenciales ingresadas no se encuentran registradas", Toast.LENGTH_SHORT).show();
            }
        }else {
            //Informar el error
            Toast.makeText(this, "Tienes que rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean credenciales_validas(String correo, String password){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BaseDatos", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String[] argumentos= {correo,password};
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM estudiantes e WHERE e.correo= ? AND e.password=?",argumentos);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        admin.close();
        db.close();
        return count > 0;
    }

    private void IrAEncuesta(){
        Intent i = new Intent(this, Encuesta.class);
        i.putExtra("correo", tv_correo.getText().toString());
        i.putExtra("password", tv_password.getText().toString());
        startActivity(i);
    }
}