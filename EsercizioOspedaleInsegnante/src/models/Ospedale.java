package models;

import utils.Reader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Ospedale {
    private ArrayList<Paziente> listaPazienti;
    private ArrayList<Dottore> listaDottori;
    private ArrayList<Chirurgo> listaChirurghi;

    public Ospedale(String percorsoPazienti, String percorsoDottori, String percorsoChirurghi) throws FileNotFoundException {

        String separatore = ",";
        // chiamo i due metodi del Reader...
        ArrayList<String> righe = Reader.righeFile(percorsoPazienti);
        ArrayList<String[]> righeSplittate = Reader.righeSplittate(righe, separatore);

        // oppure chiamo direttamente il metodo leggiESplitta che unisce i due precedenti
        // ArrayList<String[]> righeSplittate = Reader.leggiESplitta(percorsoPazienti,separatore);

        listaPazienti = new ArrayList<Paziente>();
        Paziente p;
        for (String[] rigaSplittata : righeSplittate) {
            // il metodo con cui creare l'oggetto dipende da come avete strutturato la classe Persona...
            // vediamo alcuni esempi:

            // 1) se come me avete la proprieta int eta allora fate cosi:
            p = new Paziente(rigaSplittata[0], rigaSplittata[1], rigaSplittata[2], rigaSplittata[3],
                    rigaSplittata[4], Integer.parseInt(rigaSplittata[5]), Double.parseDouble(rigaSplittata[6]));

            // 2) se dentro Persona avete la proprieta String annoDiNascita;
//            String anno = rigaSplittata[2].split("-")[0];
//            p = new Paziente(rigaSplittata[0],rigaSplittata[1],anno,rigaSplittata[3],
//                    rigaSplittata[4],Integer.parseInt(rigaSplittata[5]),Double.parseDouble(rigaSplittata[6]));

            listaPazienti.add(p);
        }


        // passiamo ai dottori
        listaDottori = new ArrayList<Dottore>();
        Dottore d;
        righeSplittate = Reader.leggiESplitta(percorsoDottori,separatore);
        for (String[] rigaSplittata : righeSplittate) {
            // il metodo con cui creare l'oggetto dipende da come avete strutturato la classe Persona...
            // vediamo alcuni esempi:

            // 1) se come me avete la proprieta int eta allora fate cosi:
            d = new Dottore(rigaSplittata[0], rigaSplittata[1], rigaSplittata[2], rigaSplittata[3],
                    rigaSplittata[4], Integer.parseInt(rigaSplittata[5]), rigaSplittata[6]);

            // 2) se dentro Persona avete la proprieta String annoDiNascita;
//            String anno = rigaSplittata[2].split("-")[0];
//            p = new Paziente(rigaSplittata[0],rigaSplittata[1],anno,rigaSplittata[3],
//                    rigaSplittata[4],Integer.parseInt(rigaSplittata[5]),rigaSplittata[6]);

                    // OSSERVAZIONE: siccome  nel file cè un dato si/no che corrisponde alla posizione in cui
                    // dovrebbe trovarsi un booleano, allora passo una stringa si_no al costruttore che poi delegherà
                    // al setter la modifica da stringa a boolean tirocinante

            listaDottori.add(d);
        }


        // passiamo ai chirurghi
        listaChirurghi = new ArrayList<Chirurgo>();
        Chirurgo c;
        righeSplittate = Reader.leggiESplitta(percorsoChirurghi,separatore);
        for (String[] rigaSplittata : righeSplittate) {
            // il metodo con cui creare l'oggetto dipende da come avete strutturato la classe Persona...
            // vediamo alcuni esempi:

            // 1) se come me avete la proprieta int eta allora fate cosi:
            c = new Chirurgo(rigaSplittata[0], rigaSplittata[1], rigaSplittata[2], rigaSplittata[3],
                    rigaSplittata[4], Integer.parseInt(rigaSplittata[5]), rigaSplittata[6],
                    Integer.parseInt(rigaSplittata[7]),Integer.parseInt(rigaSplittata[8]));

            // 2) se dentro Persona avete la proprieta String annoDiNascita;
//            String anno = rigaSplittata[2].split("-")[0];
//            p = new Paziente(rigaSplittata[0],rigaSplittata[1],anno,rigaSplittata[3],
//                    rigaSplittata[4],Integer.parseInt(rigaSplittata[5]),rigaSplittata[6],
//                    Integer.parseInt(rigaSplittata[7]),Integer.parseInt(rigaSplittata[8]));

            // OSSERVAZIONE: siccome  nel file cè un dato si/no che corrisponde alla posizione in cui
            // dovrebbe trovarsi un booleano, allora passo una stringa si_no al costruttore che poi delegherà
            // al setter la modifica da stringa a boolean tirocinante

            listaChirurghi.add(c);
        }

    } // fine costruttore

    public ArrayList<Paziente> getListaPazienti() {
        return listaPazienti;
    }

    public ArrayList<Dottore> getListaDottori() {
        return listaDottori;
    }

    public ArrayList<Chirurgo> getListaChirurghi() {
        return listaChirurghi;
    }
}
