import models.Chirurgo;
import models.Dottore;
import models.Ospedale;

import java.io.FileNotFoundException;
import java.sql.SQLOutput;

public class Main {
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

    Dottori
-- stipendio dottori:
    ogni reparto ha uno stipendio diverso e un bonus per ogni anno di esperienza differente:
    Cardiologia: 1500 -> bonus 120
    Psichiatria: 1300 -> bonus 100
    Pediatria: 2000 -> bonus 200
    Altri: 1350 -> bonus 110
 -- buonMedico() => Se ha almeno 3 anni di esperienza, ma se è del reparto pediatria è un buon medico a prescindere
    dagli anni di esperienza

    Chirurghi(il chirurgo è un dottore specializzato)
-- stipendio: Allo stipendio calcolato dal medico aggiungiamo il 20%
    Per ogni intervento riuscito aggiungere 20
    Per ogni intervento fallito togliamo 10
    Ogni 20 interventi effettuati aggiungiamo 15
  -- buonMedico() oltre alle condizioni già imposte non può avere
    più di 1/3 di interventi falliti rispetto al totale
    */

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello world!");
        String percorsoPazienti ="res/pazienti.txt";
        String percorsoDottori ="res/dottori.txt";
        String percorsoChirurghi ="res/chirurghi.txt";
        Ospedale sacroQuore = new Ospedale(percorsoPazienti, percorsoDottori, percorsoChirurghi);

        System.out.println("lista pazienti:\n" + sacroQuore.getListaPazienti());
        System.out.println("lista dottori:\n" + sacroQuore.getListaDottori());
        System.out.println("lista chirurghi:\n" + sacroQuore.getListaChirurghi());

        // proviamo il metodo stipendio() in Dottore
        System.out.println("stipendio del primo dotttore della lista: "
                + sacroQuore.getListaDottori().get(0).stipendio());
        // ora proviamo buonMedico()
        System.out.println("buonMedico del terzo dottore della lista: "
                + sacroQuore.getListaDottori().get(2).buonMedico());

        // proviamo ad usare il metodo stipendio dentro Dottore ma chiamato da un chirurgo...
        System.out.println("stipendio del primo chirurgo della lista: "
                + sacroQuore.getListaChirurghi().get(0).stipendio());
        // eureka... funziona!! perchè il metodo viene ereditato dalla classe Chirurgo!!  ===>>> EREDITARIETA'

        // non posso chiamare il metodo stipendio o buonMedico attraverso l'intera lista altrimenti mi viene segnalato ERRORE
        //      per accedere a questi due metodi devo essere un Dottore(o un suo figlio) e non una lista di Dottore!!!
        // System.out.println(sacroQuore.getListaDottori().buonMedico());
        // QUINDI PER USARE IL METODO SWU TUTTA UNA LISTA DEVO USARE UN FOR EACH....:
        String elencoStipendiDottori = "ELENCO DEGLI STIPENDI:\n";
        String elencoStipendiSoloChirurghi = "ELENCO DEGLI STIPENDI DEI SOLI CHIRURGHI:\n";
        for(Dottore d : sacroQuore.getListaDottori()){
            elencoStipendiDottori += d.getCognome() +": " +d.stipendio() + "€\n";
        }
        for(Chirurgo c : sacroQuore.getListaChirurghi()){
            elencoStipendiDottori += c.getCognome() +": " +c.stipendio() + "€\n";
            elencoStipendiSoloChirurghi += c.getCognome() +": " +c.stipendio() + "€\n";
        }
        System.out.println(elencoStipendiDottori);
        System.out.println(elencoStipendiSoloChirurghi);
    }
}