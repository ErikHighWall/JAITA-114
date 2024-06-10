package models;

public class Fumetto extends Prodotto{
    private String casa_editrice;
    private String scrittore;
    private String disegnatore;
    private int n_pagine;
    private double prezzo;

    // Costruttore


    public Fumetto(int codiceId, String titolo, String casa_editrice, String scrittore, String disegnatore, int n_pagine,double prezzo) {
        super(codiceId, titolo);
        this.casa_editrice = casa_editrice;
        this.scrittore = scrittore;
        this.disegnatore = disegnatore;
        this.n_pagine = n_pagine;
        this.prezzo=prezzo;
    }

    // Metodi getter e setter
    public String getCasa_editrice() {
        return casa_editrice;
    }

    public void setCasa_editrice(String casa_editrice) {
        this.casa_editrice = casa_editrice;
    }

    public String getScrittore() {
        return scrittore;
    }

    public void setScrittore(String scrittore) {
        this.scrittore = scrittore;
    }

    public String getDisegnatore() {
        return disegnatore;
    }

    public void setDisegnatore(String disegnatore) {
        this.disegnatore = disegnatore;
    }

    public int getN_pagine() {
        return n_pagine;
    }

    public void setN_pagine(int n_pagine) {
        this.n_pagine = n_pagine;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return super.toString()+
                "casa_editrice='" + casa_editrice + '\'' +
                ", scrittore='" + scrittore + '\'' +
                ", disegnatore='" + disegnatore + '\'' +
                ", n_pagine=" + n_pagine +"prezzo= "+prezzo+
                '}';
    }
}
