package com.example.codicefiscale;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class CodiceFiscale {
    private String cognome;
    private String nome;
    private LocalDate data;
    private String comune;
    private boolean maschio, estero;


    public CodiceFiscale(String cognome, String nome, LocalDate data, String comune, boolean maschio, boolean estero){
        this.cognome = cognome;
        this.nome = nome;
        this.data = data;
        this.comune = comune;
        this.maschio = maschio;
        this.estero = estero;
    }


    private boolean vocale (char c){
        return "AEIOUY".indexOf(c) != -1;
    }


    private String codificaNome(){
        StringBuilder ritorno = new StringBuilder();
        int numConsonanti = 0;
        int count = 0;
        int temp = 0;
        char c;
        nome = nome.toUpperCase();
        for (int i = 0; i < nome.length(); i++) {
            c = nome.charAt(i);
            if(c == ' ' || c == '\'') continue;
            if (c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U' && c != 'Y'){
                numConsonanti++;
            }
        }

        for (int i = 0; i < nome.length() && count < 3; i++) {
            c = nome.charAt(i);
            if(c == ' ' || c == '\'') continue;
            if (c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U' && c != 'Y'){
                temp++;
                if (numConsonanti >= 4){
                    if (temp == 1 || temp == 3 || temp == 4){
                        ritorno.append(c);
                        count++;
                    }
                }else{
                    ritorno.append(c);
                    count++;
                }
            }
        }
        for (int i = 0; i < nome.length() && count < 3; i++) {
            c = nome.charAt(i);
            if(c == ' ' || c == '\'') continue;
            if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'Y'){
                ritorno.append(c);
                count++;
            }
        }
        if (count < 3){
            for (int i = 0; i < 3 - count; i++) {
                ritorno.append('X');
            }
        }
        return ritorno.toString();
    }


    private String codificaCognome(){
        StringBuilder ritorno = new StringBuilder();
        int count = 0;
        char c;
        cognome = cognome.toUpperCase();
        for (int i = 0; i < cognome.length() && count < 3; i++) {
            c = cognome.charAt(i);
            if(c == ' ' || c == '\'') continue;
            if (c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U' && c != 'Y'){
                ritorno.append(c);
                count++;
            }
        }
        for (int i = 0; i < cognome.length() && count < 3; i++) {
            c = cognome.charAt(i);
            if(c == ' ' || c == '\'') continue;
            if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'Y'){
                ritorno.append(c);
                count++;
            }
        }
        if (count < 3){
            for (int i = 0; i < 3 - count; i++) {
                ritorno.append('X');
            }
        }
        return ritorno.toString();
    }


    private String codificaGiorno(int giorno){
        if (maschio){
            if (giorno >= 10){
                return String.valueOf(giorno);
            }else{
                return "0" + String.valueOf(giorno);
            }
        }else{
            return String.valueOf(giorno+40);
        }
    }


    private String codificaData(){
        StringBuilder ritorno = new StringBuilder();
        ritorno.append(data.getYear()%100 < 10 ? "0" + data.getYear()%100 : data.getYear()%100);
        ritorno.append(CodificaMese.codificaMese(data.getMonthValue()));
        ritorno.append(codificaGiorno(data.getDayOfMonth()));
        return ritorno.toString();
    }


    private String codificaProvincia(){
        return this.estero ? CodiceLuogo.getCodiceEstero(comune) : CodiceLuogo.getCodiceComune(comune);
    }


    private String codificaCodiceControllo(){
        return CodiceControllo.codiceControllo(codificaCognome() + codificaNome() + codificaData() + codificaProvincia());
    }


    public String calcolaCodiceFiscale(){
        return codificaCognome() + codificaNome() + codificaData() + codificaProvincia() + codificaCodiceControllo();
    }


    public class CodificaMese {

        private static final Map<Integer, Character> meseCodici = new TreeMap<>();

        static{
            meseCodici.put(1, 'A');
            meseCodici.put(2, 'B');
            meseCodici.put(3, 'C');
            meseCodici.put(4, 'D');
            meseCodici.put(5, 'E');
            meseCodici.put(6, 'H');
            meseCodici.put(7, 'L');
            meseCodici.put(8, 'M');
            meseCodici.put(9, 'P');
            meseCodici.put(10, 'R');
            meseCodici.put(11, 'S');
            meseCodici.put(12, 'T');
        }
        public static Character codificaMese(int mese){
            return meseCodici.getOrDefault(mese, 'X');
        }
    }
}
