package com.example.codicefiscale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class CodiceLuogo {

    public static final Map<String, String> comuniCodici = new TreeMap<>();


    static {
        try {
            BufferedReader br = new BufferedReader(new FileReader("comuni.csv"));
            String[] lettura;
            String riga;
            while((riga = br.readLine()) != null){
                lettura = riga.split(";");
                comuniCodici.put(lettura[0], lettura[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static String getCodiceComune(String siglaProvincia) {
        return comuniCodici.getOrDefault(siglaProvincia.toUpperCase(), "Codice non trovato");
    }


    public static final Map<String, String> esteroCodici = new TreeMap<>();

    static {
        try {
            BufferedReader br = new BufferedReader(new FileReader("estero.csv"));
            String[] lettura;
            String riga;
            while((riga = br.readLine()) != null){
                lettura = riga.split(";");
                esteroCodici.put(lettura[0], lettura[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static String getCodiceEstero(String siglaProvincia) {
        return esteroCodici.getOrDefault(siglaProvincia.toUpperCase(), "Codice non trovato");
    }


    private static ArrayList<String> comuni = new ArrayList<>();

    static{
        for (String p : comuniCodici.keySet()) {
            comuni.add(p);
        }
    }

    public static ArrayList<String> getComuni(){
        return comuni;
    }




    private static ArrayList<String> estero = new ArrayList<>();

    static{
        for (String p : esteroCodici.keySet()) {
            estero.add(p);
        }
    }


    public static ArrayList<String> getEstero(){
        return estero;
    }




}
