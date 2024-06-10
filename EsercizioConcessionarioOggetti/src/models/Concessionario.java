package models;

import utils.Reader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Concessionario {
    ArrayList<Auto> listaAuto;
    ArrayList<Bicicletta> listaBici;

    public Concessionario(String percorsoAuto,String percorsoBici) throws FileNotFoundException {
        listaAuto=new ArrayList<>();
        String separatore=",";
        ArrayList<String[]> fileSplittato= Reader.leggiFileESplitta(percorsoAuto,separatore);

        for(String[] riga:fileSplittato){
            Auto a= new Auto(riga[0],riga[1],riga[2],riga[3],riga[4],Integer.parseInt(riga[5]),Double.parseDouble(riga[6]),Integer.parseInt(riga[7]),Integer.parseInt(riga[8]));
          a.prezzo("auto");
            listaAuto.add(a);
        }
        listaBici=new ArrayList<>();
        ArrayList<String[]> fileSplittato1= Reader.leggiFileESplitta(percorsoBici,separatore);

        for(String[] riga:fileSplittato1){
            Bicicletta b=new Bicicletta(riga[0],riga[1],riga[2],riga[3],riga[4],Integer.parseInt(riga[5]),Double.parseDouble(riga[6]),Boolean.parseBoolean(riga[7]),Boolean.parseBoolean(riga[8]),Integer.parseInt(riga[9]));
            b.prezzo("bici");
            listaBici.add(b);
        }

    }
    public String stampaAuto(){
        String ris="Elenco auto:\n";
        for(Auto a:listaAuto){
            ris+=a.toString()+"\n";
        }
        return ris;
    }
    public String stampaBici(){
        String ris="Elenco bici:\n";
        for(Bicicletta a:listaBici){
            ris+=a.toString()+"\n";
        }
        return ris;
    }
    public double valoreAutoComplessivo(){
        double ris=0;
        for(Auto a:listaAuto){
            ris+=a.getPrezzoBase();
        }
        return ris;
    }
    public void menu(){
        System.out.println(stampaAuto());
        System.out.println(valoreAutoComplessivo());
        System.out.println(stampaBici());
    }

    @Override
    public String toString() {
        return "Concessionario{" +
                "\nlistaAuto=" + listaAuto +
                '}';
    }
}
