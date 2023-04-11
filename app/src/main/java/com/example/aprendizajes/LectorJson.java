package com.example.aprendizajes;

import android.content.Context;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.InflaterInputStream;

public class LectorJson {

    public Map<String, List<String>> obtener_diccionario(Context context) {
        try {
            //Obtengo el nodo raiz, de donde puedo obtener todos los valores como claves.
            ObjectMapper objectMapper = new ObjectMapper();
            /*
            Path filePath = Paths.get("assets", "Preguntas_Respuestas.json");
            System.out.println(filePath.getFileName());
            JsonNode nodoRaiz = objectMapper.readTree(filePath.toFile());
            */
            JsonNode nodoRaiz = objectMapper.readTree(new InputStreamReader(context.getAssets().open("Preguntas_Respuestas.json")));
            //Inicializo el diccionario que voy a retornar
            Map<String, List<String>> diccionario = new HashMap<>();

            //Defino las cantidades de preguntas de cada autor
            int cantVark = 16;
            int cantKolb = 12;
            for(int i=1;i<cantKolb+1;i++){
                List<String> preguntas_respuestas = new ArrayList<>();
                JsonNode e = nodoRaiz.get("k"+i);
                String pregunta = e.get("Pregunta").asText();
                preguntas_respuestas.add(pregunta);
                JsonNode NodoRespuestas = e.get("alternativas");
                for (JsonNode respuesta : NodoRespuestas){
                    preguntas_respuestas.add(respuesta.asText());
                }
                diccionario.put("k"+i,preguntas_respuestas);
            }
            for (int i=1;i<cantVark+1;i++){
                List<String> preguntas_respuestas = new ArrayList<>();
                JsonNode e = nodoRaiz.get("v"+i);
                String pregunta = e.get("Pregunta").asText();
                preguntas_respuestas.add(pregunta);
                JsonNode NodoRespuestas = e.get("alternativas");
                for (JsonNode respuesta : NodoRespuestas){
                    preguntas_respuestas.add(respuesta.asText());
                }
                diccionario.put("v"+i,preguntas_respuestas);
            }
            return diccionario;
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("porq?");
            return null;
        }
    }
}

