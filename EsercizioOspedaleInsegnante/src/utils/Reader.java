package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

    public static ArrayList<String> righeFile(String percorso) throws FileNotFoundException {
        ArrayList<String> ris = new ArrayList<String>();
        Scanner file  = new Scanner(new File(percorso));
        while(file.hasNextLine()){
            ris.add(file.nextLine());
        }
        return ris;
    }

    public static ArrayList<String []> righeSplittate(ArrayList<String> righe, String separatore){
        ArrayList<String[]> ris = new ArrayList<String[]>();
        for(String riga : righe)
            ris.add(riga.split(separatore));
        return ris;
    }


    // metodo che unisce i due precedenti
    public static ArrayList<String[]> leggiESplitta(String percorso, String separatore) throws FileNotFoundException {
        // prima parte: metodo righeFile()
        ArrayList<String> righe  = new ArrayList<>();
        righe = righeFile(percorso);

        // seconda parte: metodo righeSplittate()
        ArrayList<String[]> ris = new ArrayList<String[]>();
        ris = righeSplittate(righe,separatore);

        // posso fare entrambi i passaggi su una riga sola
        // ris = righeSplittate(righeFile(percorso),separatore);

        return ris;
    }



}
