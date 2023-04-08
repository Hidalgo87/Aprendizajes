package com.example.aprendizajes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Encuesta extends AppCompatActivity {

    Integer contador=1;
    String correo, password;
    Map<String, List<String>> diccionario;
    TextView tvPregunta, tvPreguntaA, tvPreguntaB, tvPreguntaC, tvPreguntaD, tvPreguntaContador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);

        correo = getIntent().getStringExtra("correo");
        password = getIntent().getStringExtra("password");

        LectorJson lj = new LectorJson();
        tvPregunta = (TextView)findViewById(R.id.textViewQuestion);
        tvPreguntaA = (TextView)findViewById(R.id.textViewPreguntaA);
        tvPreguntaB = (TextView)findViewById(R.id.textViewPreguntaB);
        tvPreguntaC = (TextView)findViewById(R.id.textViewPreguntaC);
        tvPreguntaD = (TextView)findViewById(R.id.textViewPreguntaD);
        tvPreguntaContador = (TextView)findViewById(R.id.textViewContadorPreguntas);
        this.diccionario = lj.obtener_diccionario(getApplicationContext());
        lanzar_sgte_pregunta();

    }

    public void Next_Question(View view){
        if(lanzar_sgte_pregunta()){
            ;
        }else {
            //ENCUESTA TERMINADA: ENVIAR
        }
    }

    boolean lanzar_sgte_pregunta() {
        Random random = new Random();
        List<String> preguntas_respuestas;
        String claveAleatoria;
        Map<String, List<String>> copia_diccionario = diccionario;

        // Obtener una clave aleatoria del diccionario
        List<String> claves = new ArrayList<>(copia_diccionario.keySet());
        if(claves.size() == 0){
            return false;
        }
        claveAleatoria = claves.get(random.nextInt(claves.size()));

        // Obtener las respuestas asociadas a la clave aleatoria
        preguntas_respuestas = copia_diccionario.get(claveAleatoria);
        copia_diccionario.remove(claveAleatoria);
        List<String> respuestas = new ArrayList<>();
        String pregunta = "";
        if (preguntas_respuestas != null) {
            pregunta = preguntas_respuestas.get(0);
            preguntas_respuestas.remove(0);
            respuestas = preguntas_respuestas;
            int rand = random.nextInt(respuestas.size());
            String rspAl1 = respuestas.get(rand);
            respuestas.remove(rand);
            rand = random.nextInt(respuestas.size());
            String rspAl2 = respuestas.get(rand);
            respuestas.remove(rand);
            rand = random.nextInt(respuestas.size());
            String rspAl3 = respuestas.get(rand);
            respuestas.remove(rand);
            rand = random.nextInt(respuestas.size());
            String rspAl4 = respuestas.get(rand);
            respuestas.remove(rand);
            tvPregunta.setText(pregunta);
            tvPreguntaA.setText("a. " + rspAl1);
            tvPreguntaB.setText("b. " + rspAl2);
            tvPreguntaC.setText("c. " + rspAl3);
            tvPreguntaD.setText("d. " + rspAl4);
            tvPreguntaContador.setText("PREGUNTA "+contador);
            contador++;
            return true;
        }
        return false;
    }
}