package com.example.aprendizajes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.tv.TvInputService;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Console;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PrediccionEstiloAprendizaje extends AppCompatActivity {

    Spinner sp_prediccion_kolb, sp_prediccion_vark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediccion_estilo_aprendizaje);
        sp_prediccion_kolb = findViewById(R.id.spinner_KOLB);
        sp_prediccion_vark = findViewById(R.id.spinner_VARK);
        String[] array_kolb = {"Experiencia concreta", "Observación reflexiva", "Conceptualización abstracta",
                "Experimentación activa"};
        String[] array_vark = {"Visual", "Auditivo", "Lecto/Escritor", "Kinestésico"};
        ArrayAdapter<String> adapter_kolb = new ArrayAdapter<>(this, R.layout.spinner_item_prediccion, array_kolb);
        ArrayAdapter<String> adapter_vark = new ArrayAdapter<>(this, R.layout.spinner_item_prediccion, array_vark);
        sp_prediccion_kolb.setAdapter(adapter_kolb);
        sp_prediccion_vark.setAdapter(adapter_vark);
    }

    public void EnviarEncuesta(View view) {
        sp_prediccion_kolb = findViewById(R.id.spinner_KOLB);
        sp_prediccion_vark = findViewById(R.id.spinner_VARK);
        Intent i = getIntent();
        String correo = i.getStringExtra("correo");
        //probando_enviar_correo(correo,"oaa");
        //enviarCorreo("juanfgomez6@gmail.com","conciencia1.", correo, "Terminaste la encuesta", "Bien hecho");
        IrAMenu();
    }

    private void IrAMenu() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

   }

