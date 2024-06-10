import models.Ospedale;
import models.Paziente;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main_Menu {

    /*
    proprietà dei pazienti: nome,cognome,eta,residenza,reparto,giorniRicovero,costoGiornaliero
    proprietà dei dottori: nome,cognome,eta,residenza, specializzazione, anniLavoro,tirocinante;
    proprietà dei chirurghi: nome,cognome,eta,residenza, specializzazione, anniLavoro,tirocinante,numeroInterventi,interventiRiusciti;
    chi gestirà i pazienti,i dottori e i chirurghi sarà un ospedale e dovrà poter permettere la visualizzazione di questi dati:
            "1  - Lista pazienti\n" +
            "2  - Lista Dottori\n" +
            "3  - Lista Completa\n" +
            "4  - Dottori con almeno 10 anni di lavoro\n" +
            "5  - Dottori per città\n" +
            "6  - Dottore con stipendio massimo\n" +
            "7  - Dottore con stipendio minimo\n" +
            "8  - Media stipendi dottori\n" +
            "9  - Media stipendi per specializzazione\n" +
            "10 - Lista buon dottore\n" +
            "11 - Spesa totale\n" +
            "12 - Reparto con più pazienti");
*/
    public static void main(String[] args) throws FileNotFoundException {
        String percorsoPazienti ="res/pazienti.txt";
        String percorsoDottori ="res/dottori.txt";
        String percorsoChirurghi ="res/chirurghi.txt";
        Ospedale sacroQuore = new Ospedale(percorsoPazienti, percorsoDottori, percorsoChirurghi);

        Scanner tastiera = new Scanner(System.in);

        do {
            System.out.println("\nScegli una delle seguenti opzioni: \n" +
                    "1  - Lista pazienti\n" +
                    "2  - Lista Dottori\n" +
                    "3  - Lista Completa\n" +
                    "4  - Dottori con almeno 10 anni di lavoro\n" +
                    "5  - Dottori per città\n" +
                    "6  - Dottore con stipendio massimo\n" +
                    "7  - Dottore con stipendio minimo\n" +
                    "8  - Media stipendi dottori\n" +
                    "9  - Media stipendi per specializzazione\n" +
                    "10 - Lista buon dottore\n" +
                    "11 - Spesa totale\n" +
                    "12 - Reparto con più pazienti\n" +
                    "0  - ESCI\n");

            ArrayList<Paziente> pazienti = sacroQuore.getListaPazienti();
            System.out.println(sacroQuore.toString());
            switch(Integer.parseInt(tastiera.nextLine())) {
                case 1:
                    break;
                case 12:
                    String reparto;
                    String repartoFinale = "";
                    int contatore;
                    int contatoreFinale = 0,c=0;
                    ArrayList<Paziente> pazientiRimouovere=new ArrayList<>();
                    System.out.println(pazienti);
                    for(Paziente paziente : pazienti) {
                        // System.out.println("--------" + paziente.toString());
                       // if (!pazientiRimouovere.contains(paziente)) {
                            reparto = paziente.getReparto();
                            contatore = 0;
                            c = 0;
                            ArrayList<Integer> i = new ArrayList<>();
                            for (Paziente p : pazienti) {
                                if (p.getReparto().equalsIgnoreCase(reparto)) {
                                    contatore++;
                                  //  pazientiRimouovere.add(p);

                                i.add(c);


                                }
                                c++;
                                // System.out.println(p);

                            }
                      //  System.out.println(i);
                        /*for(Integer b:i)
                        {
                            System.out.println(b);
                            pazienti.remove(b);
                        }*/

                     System.out.println(pazienti);

                            if (contatore > contatoreFinale) {
                                contatoreFinale = contatore;
                                repartoFinale = reparto;
                            }
                        //}
                   }
                    //System.out.println(pazienti);
                    System.out.println("il reparto con piu pazienti è: " + repartoFinale +
                            " con " + contatoreFinale + " pazienti.");
                    break;
                case 0:
                    break;
            } // fine switch

        }while(2==2);

    }
}
