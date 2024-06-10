import Interfaces.IServiceCliente;
import Interfaces.IServiceGestore;
import servizi.ServiceCliente;
import servizi.ServiceGestore;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {


        IServiceCliente provaCliente = new ServiceCliente();
        IServiceGestore provaGestore = new ServiceGestore();

        Scanner tastiera = new Scanner(System.in);
        boolean richiesta = false;
        int sceltaUtente;
        int sceltaCliente;
        String risposta = "";
        int sceltaDatoClienteAlbum;
        int sceltaDatoClienteRL;
        int sceltaDatoClienteCanzone;
        int idAlbum;
        int idRL;
        int sceltaDatoClienteArtisti;
        int idA;
        int idS;

        do {
            System.out.println("Benvenuto! Sei un gestore o un cliente? Fai la tua scelta!" +
                    "\n1.Sono un Gestore" +
                    "\n2.Sono un Cliente" +
                    "\n3.Esci" +
                    "\nScegli pure!");
            sceltaUtente = Integer.parseInt(tastiera.nextLine());

            switch (sceltaUtente){
                case 1: //GESTORE
                    break;
                case 2: //CLIENTE
                    System.out.println("Hai scelto il Cliente, ecco le tue opzioni:" + "\n" +
                            "\n1.Visualizzare gli Album" +
                            "\n2.Leggere i Record Label" +
                            "\n3.Cercare gli Artisti" +
                            "\n4.Visualizzare la lista delle canzoni" +
                            //"\n5.Esci" +
                            "\nDi seguito dimmi la tua risposta");
                    sceltaCliente = Integer.parseInt(tastiera.nextLine());

                    switch(sceltaCliente){
                        case 1: //VISUALIZZARE ALBUM
                            risposta = "visualizzare Album";

                            System.out.println("Che cosa vuoi selezionare?" +
                                    "\n1.Tutti gli album disponibili" +
                                    "\n2.Scegliere un album" +
                                    "\n3.Esci");
                            sceltaDatoClienteAlbum = Integer.parseInt(tastiera.nextLine());

                            switch (sceltaDatoClienteAlbum) {
                                case 1:
                                    System.out.println("Ecco tutti gli album disponibili");
                                    System.out.println(provaCliente.stampaLista(provaCliente.listaSong()));
                                    break;
                                case 2:
                                    System.out.println("Di seguito l'album da te scelto:");
                                    idAlbum = Integer.parseInt(tastiera.nextLine());
                                    System.out.println(provaCliente.readOneAlbum(idAlbum));
                                    break;
                                default:
                                    System.out.println("Scelta non valida, riprova");
                            }
                            break;

                        case 2:
                            risposta = "leggere Record Label";

                            System.out.println("Che cosa vuoi selezionare?" +
                                    "\n1.Tutti i record label disponibili" +
                                    "\n2.Scegliere un record label" +
                                    "\n3.Esci");
                            sceltaDatoClienteRL = Integer.parseInt(tastiera.nextLine());

                            switch (sceltaDatoClienteRL) {
                                case 1:
                                    System.out.println("Ecco tutti i record label disponibili!");
                                    System.out.println(provaCliente.stampaLista(provaCliente.leggiRecordLabel()));
                                    break;
                                case 2:
                                    System.out.println("Di seguito il record label che hai cercato:");
                                    idRL = Integer.parseInt(tastiera.nextLine());
                                    System.out.println(provaCliente.readOneAlbum(idRL));
                                    break;
                                default:
                                    System.out.println("Scelta non valida, riprova");
                            }
                        case 3:
                            risposta = "cercare Artisti";

                            System.out.println("Che cosa vuoi selezionare?" +
                                    "\n1.Tutti gli artisti disponibili" +
                                    "\n2.Scegliere un artista" +
                                    "\n3.Esci");
                            sceltaDatoClienteArtisti = Integer.parseInt(tastiera.nextLine());

                            switch (sceltaDatoClienteArtisti) {
                                case 1:
                                    System.out.println("Ecco tutti gli artisti disponibili!");
                                    System.out.println(provaCliente.stampaLista(provaCliente.cercaArtisti()));
                                    break;
                                case 2:
                                    System.out.println("Di seguito gli artisti che hai cercato che hai cercato:");
                                    idA = Integer.parseInt(tastiera.nextLine());
                                    System.out.println(provaCliente.readOneArtist(idA));
                                    break;
                                default:
                                    System.out.println("Scelta non valida, riprova");
                            }
                            break;
                        case 4:
                            risposta = "lista Canzoni";

                            System.out.println("Che cosa vuoi selezionare?" +
                                    "\n1.Tutti gli artisti disponibili" +
                                    "\n2.Scegliere un artista" +
                                    "\n3.Esci");
                            sceltaDatoClienteCanzone = Integer.parseInt(tastiera.nextLine());

                            switch {
                            case 1:
                                System.out.println("Ecco tutte le canzoni disponibili");
                                System.out.println(provaCliente.stampaLista(provaCliente.listaSong()));
                                break;
                            case 2:
                                System.out.println("Di seguito la canzone da te scelto:");
                                idS = Integer.parseInt(tastiera.nextLine());
                                System.out.println(provaCliente.readOneSong(idS));
                                break;
                            default:
                                System.out.println("Scelta non valida, riprova");
                        }
                        break;
                    }

                case 3:
                    richiesta = true;
                    break;


                default:;
                    System.out.println("Scelta non valida, riprova");
                    richiesta = false;
            }

        } while (richiesta == false);

        tastiera.close();

    }








}
}