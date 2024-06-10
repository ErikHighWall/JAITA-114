package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

    public static ArrayList<String> leggiFile(String percorso) throws FileNotFoundException {
        ArrayList<String> ris = new ArrayList<>();
        Scanner scanner = new Scanner(new File(percorso));
        while (scanner.hasNextLine()) {
            ris.add(scanner.nextLine());
        }
        scanner.close();
        return ris;
    }

    public static ArrayList<String[]> leggiRigheSplit(ArrayList<String> file, String separatore) {
        ArrayList<String[]> ris = new ArrayList<>();
        String[] rigaSplittata;
        for(String s:file){
            rigaSplittata=s.split(separatore);
            ris.add(rigaSplittata);
        }
        return ris;
    }
    public static ArrayList<String[]> leggiFileESplitta(String percorso,String separatore) throws FileNotFoundException {

        return leggiRigheSplit(leggiFile(percorso),separatore);
    }
}
