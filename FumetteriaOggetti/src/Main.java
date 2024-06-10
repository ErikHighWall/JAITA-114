import models.Negozio;
import models.Prodotto;

import java.io.FileNotFoundException;

public class Main {
    /*definire una classe Prodotto con le seguenti proprietà:
codiceId: int, titolo: str
definire una classe Fumetto con le segueti proprietà
casa_editrice: str, scrittore: str, disegnatore: str, n_pagine: int

definire una classe Videogame con le seguenti proprietà
console: str, genere: str, studio_sviluppo: str, voto_critica: int

vogliamo essere sicuri che a seguito di eventuali modifiche apportare agli oggetti:
  non sia possibile di sostituire i valori assegnati agli attributi con stringhe vuote
  che il numero di pagine dei fumetti sia sempre maggiore di zero
  che il voto dei giochi sia compreso fra 0 e 100

definire una classe negozio con le seguenti proprietà
nome: str, games: lista, fumetti: lista

importare le classi in un main e creare le liste di videogame e fumetti
creare un'istanza della classe negozio

definire un programma con un menù che dia la possibilità all'utente di
 1- Vedere tutti i videogames
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
16- Cancellare un fumetto

Se volete aggiungere complessità all'esercizio inserite metodi di controllo sui valori presi in input.
Inoltre potreste aggiungere anche la data di pubblicazione/rilascio e salvare il valore in formato Date invece di String.
Buon lavoro!*/
    public static void main(String[] args) throws FileNotFoundException {

        String percorsoFumetti="res/elenco_fumetti.txt";
        String percorsoVideogame="res/elenco_games.txt";
        Negozio negozio1=new Negozio(percorsoFumetti,percorsoVideogame);
        negozio1.interfaccia();

    }
}