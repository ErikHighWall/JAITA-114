package models;

import utils.Reader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Negozio {
    private ArrayList<Fumetto> listaFumetti;
    private ArrayList<Videogame> listaVideogame;
    Scanner input=new Scanner(System.in);

    public Negozio(String percorsoFumetti, String percorsoVideogame) throws FileNotFoundException {
        listaFumetti = new ArrayList<>();
        String separatore = ",";
        for (String[] riga : Reader.leggiFileESplitta(percorsoFumetti, separatore)) {
            Fumetto f = new Fumetto(Integer.parseInt(riga[0]), riga[1], riga[2], riga[3], riga[4], Integer.parseInt(riga[5]), Double.parseDouble(riga[6]));
            listaFumetti.add(f);
        }
        listaVideogame = new ArrayList<>();
        for (String[] riga : Reader.leggiFileESplitta(percorsoVideogame, separatore)) {
            Videogame f = new Videogame(Integer.parseInt(riga[0]), riga[1], riga[2], riga[3], riga[4], Integer.parseInt(riga[5]));
            listaVideogame.add(f);
        }
    }

    public String stampaListaFumetti() {
        String ris = "";
        for (Fumetto f : listaFumetti) {
            ris += f.toString() + "\n";
        }
        return ris;
    }

    public String stampaListaVideogame() {
        String ris = "";
        for (Videogame f : listaVideogame) {
            ris += f.toString() + "\n";
        }
        return ris;
    }

    public String stampaCompleta() {


        return stampaListaFumetti() + "\n" + stampaListaVideogame();
    }

    public void stampaGiocoStudio(String studio) {
        String ris = "Elencio giochi di " + studio + ":\n";
        for (Videogame v : listaVideogame) {
            if (v.getStudio_sviluppo().equalsIgnoreCase(studio)) {
                ris += v + "\n";
            }
        }
        System.out.println(ris);
    }

    private void stampaFumettiAutore(String autore) {
        String ris = "Elencio fumetti di " + autore + ":\n";
        for (Fumetto v : listaFumetti) {
            if (v.getScrittore().equalsIgnoreCase(autore)) {
                ris += v + "\n";
            }
        }
        System.out.println(ris);
    }
    private void stampaCEditUtente(String casaEdit){
        String ris = "Elencio fumetti della casa editrice " +casaEdit + ":\n";
        boolean vuoto=true;
        for (Fumetto v : listaFumetti) {
            if (v.getCasa_editrice().equalsIgnoreCase(casaEdit)) {
                ris += v + "\n";
                vuoto=false;

            }

        }
        if(vuoto)
            ris="non presente";
        System.out.println(ris);
    }
    private void stampaTitoloPChiave(String pKey){
        String ris="Elenco fumetti e videogiochi :\n";

        for(Fumetto f:listaFumetti){

            if(f.getTitolo().toLowerCase().contains(pKey.toLowerCase())){
                ris+=f+"\n";
            }


        }
        for(Videogame f:listaVideogame){
            if(f.getTitolo().toLowerCase().contains(pKey.toLowerCase())){
                ris+=f+"\n";
            }

        }
        System.out.println(ris);
    }
 public void votoVideoShowComp(int rangeMin,int rangeMax){
     String ris = "Elencio giochi con range " + rangeMin +"-"+rangeMax+ ":\n";;
     for (Videogame v : listaVideogame) {
         if (v.getVoto_critica()>rangeMin&&v.getVoto_critica()<rangeMax) {
             ris += v + "\n";
         }
     }
     System.out.println(ris);
 }
    public void votoVideoMax(int Max){
        String ris = "Elencio giochi con voto " +Max+ " e superiore"+":\n";;
        for (Videogame v : listaVideogame) {
            if (v.getVoto_critica()>Max) {
                ris += v + "\n";
            }
        }
        System.out.println(ris);
    }
    public void aggiungiGioco(){
        System.out.println("Inserisci id,titolo,console,genere,studio di sviluppo,voto critica(campi separati con la virgola)");
        String[] riga=input.nextLine().split(",");
        Videogame f = new Videogame(Integer.parseInt(riga[0]), riga[1], riga[2], riga[3], riga[4], Integer.parseInt(riga[5]));
        listaVideogame.add(f);
        System.out.println("Videogame"+f.getTitolo()+" aggiunto!");

    }
    public void aggiorboGioco(){

        boolean there=false;

        System.out.println("Inserisci un gioco da aggiornare");
String r=input.nextLine();
        for(Videogame v:listaVideogame){
            if(v.getTitolo().equalsIgnoreCase(r)){
                System.out.println("Inserisci CAMPI  da aggiornare--> id,titolo,console,genere,studio di sviluppo,voto critica(campi separati con la virgola)");
                String[] riga=input.nextLine().split(",");
                v.aggiornoVideogame(Integer.parseInt(riga[0]), riga[1], riga[2], riga[3], riga[4], Integer.parseInt(riga[5]));
                there=true;
            }
        }
        if(!there)
            System.out.println("Gioco non presente,addio");
    }

    public void eliminoGioco(){
Scanner input1=new Scanner(System.in);
        System.out.println("Scegli gioco da eliminare");
        String delete=input1.nextLine();
        boolean there=false;
        int c=0;

        for(int i=0;i<listaVideogame.size();i++){
            Videogame v=listaVideogame.get(i);
            if(v.getTitolo().equalsIgnoreCase(delete)){
                listaVideogame.remove(i);
                there=true;
            }

        }
        if(!there)
            System.out.println("Gioco non presente,addio");
    }
    public void interfaccia() {

       /* 1- Vedere tutti i videogames
        2- Vedere tutti i fumetti
        3- Vedere tutti i videogames sviluppati da Nintendo
        4- Vedere tutti i fumetti scritti da Alan Moore
        5- Vedere tutti i videogames di una console scelta dall'utente
        6- Vedere tutti i fumetti di una casa editrice scelta dall'utente
        5- Vedere tutti i videogiochi e i fumetti che presentano nel titolo una parola chiave fornita dall'utente
        6- Vedere tutti i videogiochi con un voto compreso fra 60 e 90
        7- Vedere tutti i videogiochi con un voto oltre i 90
        8- Vedere la media delle pagine di tutti i fumetti
        9- Vedere la media delle pagine dei fumetti per una disegnatore scelto dall'utente
        10- Vedere i videogiochi con il voto più alto
        11- Vedere i videogiochi con il voto più basso
        12- Vedere i fumetti ordinati da quello con meno pagine a quello con più pagine
        13- Vedere i videogiochi ordinati per voto dal migliore al peggiore
        14- Aggiungere un nuovo gioco
        15- Aggiungere un nuovo fumetto
        16- Aggiornare un gioco già esistente
        16- Cancellare un fumetto*/
        boolean isexit=false;
do {
    Scanner tastiera = new Scanner(System.in);
    switch (Integer.parseInt(tastiera.nextLine())) {
        case 1:
            System.out.println(stampaListaVideogame());
            break;
        case 2:
            System.out.println(stampaListaFumetti());
            break;
        case 3:
            stampaGiocoStudio("Nintendo");
            break;
        case 4:
            stampaFumettiAutore("Hajime Isayama");
            break;
        case 5:
            System.out.println("Inserisic casa editrice");
            stampaCEditUtente(tastiera.nextLine());
            break;
        case 6:
            //5- Vedere tutti i videogiochi e i fumetti che presentano nel titolo una parola chiave fornita dall'utente
            System.out.println("Inserisic una parola chiave");
            stampaTitoloPChiave(tastiera.nextLine());
            break;
        case 7:
            votoVideoShowComp(60, 90);
            break;
        case 8:
            votoVideoMax(90);
            break;

        case 15:

            aggiungiGioco();
            break;
        case 16:
            aggiorboGioco();
            break;
        case 17:
            eliminoGioco();
            break;
        case 0:
            isexit=true;
            break;
    }
}while(!isexit);

    }

}
