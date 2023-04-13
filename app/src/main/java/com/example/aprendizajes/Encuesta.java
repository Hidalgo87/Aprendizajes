package com.example.aprendizajes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Encuesta extends AppCompatActivity {
    //        A   ,   B    ,   C  ,    D
    Integer ECkolb, ORkolb, CAkolb, EAkolb;
    Integer Vvark, Avark, Rvark, Kvark;
    Integer contador=1;
    String correo, password;
    Map<String, List<String>> diccionario;
    Map<String, List<String>> copia_diccionario;
    TextView tvPregunta, tvPreguntaA, tvPreguntaB, tvPreguntaC, tvPreguntaD, tvPreguntaContador;
    RadioButton rb1a, rb1b, rb1c, rb1d, rb2a, rb2b, rb2c, rb2d, rb3a, rb3b, rb3c, rb3d, rb4a, rb4b,
    rb4c, rb4d;


 /*
         for (List<RadioButton> fila : lista_filas) {
            final RadioButton[] checked = {null};
            for (RadioButton rb : fila) {
                rb.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        if (checked[0] == null) {
                            checked[0] = rb;
                        } else {
                            fila.remove(rb);
                            fila.get(0).setChecked(false);
                            fila.get(1).setChecked(false);
                            fila.get(2).setChecked(false);
                            fila.add(rb);
                            rb.setChecked(true);
                        }
                    }
                });
            }
        }
  */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);

        //La información con la que inició sesión
        correo = getIntent().getStringExtra("correo");
        password = getIntent().getStringExtra("password");
        //Obtengo los valores de Text View actuales y los botones
        tvPregunta = (TextView)findViewById(R.id.textViewQuestion);
        tvPreguntaA = (TextView)findViewById(R.id.textViewPreguntaA);
        tvPreguntaB = (TextView)findViewById(R.id.textViewPreguntaB);
        tvPreguntaC = (TextView)findViewById(R.id.textViewPreguntaC);
        tvPreguntaD = (TextView)findViewById(R.id.textViewPreguntaD);
        tvPreguntaContador = (TextView)findViewById(R.id.textViewContadorPreguntas);
        rb1a = findViewById(R.id.radioButton1a);
        rb1b =  findViewById(R.id.radioButton1b);
        rb1c =  findViewById(R.id.radioButton1c);
        rb1d =  findViewById(R.id.radioButton1d);

        rb2a =  findViewById(R.id.radioButton2a);
        rb2b =  findViewById(R.id.radioButton2b);
        rb2c =  findViewById(R.id.radioButton2c);
        rb2d =  findViewById(R.id.radioButton2d);

        rb3a =  findViewById(R.id.radioButton3a);
        rb3b =  findViewById(R.id.radioButton3b);
        rb3c =  findViewById(R.id.radioButton3c);
        rb3d =  findViewById(R.id.radioButton3d);

        rb4a =  findViewById(R.id.radioButton4a);
        rb4b =  findViewById(R.id.radioButton4b);
        rb4c =  findViewById(R.id.radioButton4c);
        rb4d =  findViewById(R.id.radioButton4d);

        //Leo el archivo json y crea el diccionario y la copia.
        LectorJson lj = new LectorJson();
        this.diccionario = lj.obtener_diccionario(getApplicationContext());
        this.copia_diccionario = lj.obtener_diccionario(getApplicationContext());

        //Lanza la primera pregunta
        lanzar_pregunta();
    }

    private void lanzar_pregunta() {
        Random random = new Random();
        List<String> preguntas_respuestas;
        String claveAleatoria;
        System.out.println("origins");
        System.out.println(diccionario.size());
        System.out.println("copia: ");
        System.out.println(copia_diccionario.size());
        // Obtener una clave aleatoria del diccionario
        List<String> claves = new ArrayList<>(copia_diccionario.keySet());
        claveAleatoria = claves.get(random.nextInt(claves.size()));

        // Obtener las respuestas asociadas a la clave aleatoria y borrar esa pregunta de la
        // copia del diccionario
        preguntas_respuestas = copia_diccionario.get(claveAleatoria);
        copia_diccionario.remove(claveAleatoria);
        List<String> respuestas;
        String pregunta = preguntas_respuestas.get(0);
        preguntas_respuestas.remove(0);
        respuestas = preguntas_respuestas;

        // El orden de las respuestas es aleatorio
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

        //Se le muestra en la pantalla
        tvPregunta.setText(pregunta);
        tvPreguntaA.setText("a. " + rspAl1);
        tvPreguntaB.setText("b. " + rspAl2);
        tvPreguntaC.setText("c. " + rspAl3);
        tvPreguntaD.setText("d. " + rspAl4);
        tvPreguntaContador.setText("PREGUNTA "+contador);
        contador++;
    }

    public void onRadioButtonClicked(View view) {
        // Busca el botón seleccionado en la tabla
        RadioButton selectedButton = (RadioButton) view;

        // Obtén la posición del botón seleccionado en la tabla
        int selectedRow = -1;
        int selectedCol = -1;
        TableLayout tableLayout = findViewById(R.id.tableBotones);
        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            View child = tableLayout.getChildAt(i);
            if (child instanceof TableRow) {
                TableRow row = (TableRow) child;
                for (int j = 0; j < row.getChildCount(); j++) {
                    View cell = row.getChildAt(j);
                    if (cell instanceof RadioButton) {
                        RadioButton button = (RadioButton) cell;
                        if (button == selectedButton) {
                            selectedRow = i;
                            selectedCol = j;
                            break;
                        }
                    }
                }
                if (selectedRow != -1 && selectedCol != -1) {
                    break;
                }
            }
        }

        // Deselecciona todos los botones en la misma fila y columna que el botón seleccionado
        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            View child = tableLayout.getChildAt(i);
            if (child instanceof TableRow) {
                TableRow row = (TableRow) child;
                for (int j = 0; j < row.getChildCount(); j++) {
                    View cell = row.getChildAt(j);
                    if (cell instanceof RadioButton) {
                        RadioButton button = (RadioButton) cell;
                        if (i == selectedRow || j == selectedCol) {
                            if (button != selectedButton) {
                                button.setChecked(false);
                            }
                        }
                    }
                }
            }
        }
    }

    //Cuando le da al botón siguiente.
    public void Next_Question(View view) {
        //Verificar si seleccionó todos los botones
        if (!estan_seleccionados_radio_bt()) {
            Toast.makeText(this, "Debes valorar los 4 items", Toast.LENGTH_SHORT).show();
        } else {
            //TODO: Guardar resultados

            //Verificar si quedan más preguntas
            if (this.copia_diccionario.size() != 0) {
                limpiar_radio_bt();
                lanzar_pregunta();
            } else {
                // ENVIAR LA ENCUESTA
                Toast.makeText(this, "Ya terminaste", Toast.LENGTH_SHORT).show();
                IrAMenuPrincipal();
            }
        }
    }

    private void IrAMenuPrincipal(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private boolean estan_seleccionados_radio_bt(){
        if(rb1a.isChecked() || rb2a.isChecked() || rb3a.isChecked() || rb4a.isChecked()){
            if(rb1b.isChecked() || rb2b.isChecked() || rb3b.isChecked() || rb4b.isChecked()){
                if(rb1c.isChecked() || rb2c.isChecked() || rb3c.isChecked() || rb4c.isChecked()){
                    if(rb1d.isChecked() || rb2d.isChecked() || rb3d.isChecked() || rb4d.isChecked()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void limpiar_radio_bt(){
        rb1a.setChecked(false);
        rb2a.setChecked(false);
        rb3a.setChecked(false);
        rb4a.setChecked(false);

        rb1b.setChecked(false);
        rb2b.setChecked(false);
        rb3b.setChecked(false);
        rb4b.setChecked(false);

        rb1c.setChecked(false);
        rb2c.setChecked(false);
        rb3c.setChecked(false);
        rb4c.setChecked(false);

        rb1d.setChecked(false);
        rb2d.setChecked(false);
        rb3d.setChecked(false);
        rb4d.setChecked(false);
    }
}
