package com.example.aprendizajes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.xmlbeans.impl.soap.Text;

public class ResultadosEncuesta extends AppCompatActivity {

    TextView tvEstiloKolb, tvEstiloVark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_encuesta);
        Intent i = getIntent();
        String estiloVark = i.getStringExtra("vark");
        String estiloKolb = i.getStringExtra("kolb");
        tvEstiloKolb = findViewById(R.id.tvEstiloKOLB);
        tvEstiloVark = findViewById(R.id.tvEstiloVARK);
        tvEstiloKolb.setText(estiloKolb);
        tvEstiloVark.setText(estiloVark);
    }

    public void IrAMenu(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}