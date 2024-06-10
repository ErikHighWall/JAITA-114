package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

    public static ArrayList<String> leggiFile(String percorso) throws FileNotFoundException {
        ArrayList<String> ris=new ArrayList<>();
        Scanner scannerFile= new Scanner(new File(percorso));
        while(scannerFile.hasNextLine()){
            ris.add(scannerFile.nextLine());
        }
        return ris;
    }
    public static ArrayList<String[]> fileRigheSplittate(ArrayList<String> file,String separatore)  {
        ArrayList<String[]> ris=new ArrayList<>();
        for(String s:file){
            String[] riga=s.split(separatore);
            ris.add(riga);
        }
        return ris;
    }
    public static ArrayList<String[]> leggiFileESplitta(String percorso,String separatore) throws FileNotFoundException {
        return fileRigheSplittate(leggiFile(percorso),separatore);
    }
}
