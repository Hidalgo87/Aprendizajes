package com.example.aprendizajes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    EditText et_nombre, et_edad, et_correo, et_password;
    Spinner sp_genero, sp_etnia, sp_estrato, sp_ingresos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }


    // Método cuando el usuario le de clic al botón registrar en la pantalla de registro
    public void Registrar(View view) {

        // Obtener todos los campos de la interfaz visual
        et_nombre = (EditText) findViewById(R.id.et_Nombre);
        et_edad = (EditText) findViewById(R.id.et_Edad);
        et_correo = (EditText) findViewById(R.id.et_Email);
        et_password = (EditText)findViewById(R.id.et_Password);
        sp_genero = (Spinner) findViewById(R.id.sp_Genero);
        sp_estrato = (Spinner) findViewById(R.id.sp_Estrato);
        sp_etnia = (Spinner) findViewById(R.id.sp_Etnia);
        sp_ingresos = (Spinner) findViewById(R.id.sp_Ingresos_Familiares);

        //Verificar que estén completos
        if(et_nombre.getText().toString().length() != 0 && et_edad.getText().toString().length() != 0
                && et_correo.getText().toString().length() != 0){
            //Ingresar los datos del estudiante en el objeto
            ContentValues nuevo_estudiante = new ContentValues();
            nuevo_estudiante.put("nombre", et_nombre.getText().toString());
            nuevo_estudiante.put("password", et_password.getText().toString());
            nuevo_estudiante.put("edad", et_edad.getText().toString());
            nuevo_estudiante.put("correo", et_correo.getText().toString());
            nuevo_estudiante.put("genero", sp_genero.getSelectedItem().toString());
            nuevo_estudiante.put("estrato", sp_estrato.getSelectedItem().toString());
            nuevo_estudiante.put("etnia", sp_etnia.getSelectedItem().toString());
            nuevo_estudiante.put("ingresos_familiares", sp_ingresos.getSelectedItem().toString());


            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BaseDatos", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            db.insert("estudiantes", null, nuevo_estudiante);
            /*
            admin.close();
            db.close();
            */
        }
        else{
            // Informar error
            Toast.makeText(this, "Tienes que rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

}