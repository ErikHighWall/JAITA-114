package com.generation.negozio.models;

import java.sql.Date;

public class Alimentare extends Entity{

    private String nome;
    private double prezzo;
    private Date data_scadenza;


    public Alimentare() {
    }

    public Alimentare(Long id, String nome, double prezzo, Date sdata_scadenza) {
        super(id);
        this.nome = nome;
        this.prezzo = prezzo;
        this.data_scadenza = data_scadenza;
    }

   //get
    public String getNome() {


        return nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public Date getScadenza() {
        return data_scadenza;
    }

    //set
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public void setScadenza(Date data_scadenza) {
        this.data_scadenza = data_scadenza;
    }

    @Override
    public String toString() {
        return "Alimentare [id=" + getId() + ", nome=" + nome + ", prezzo=" + prezzo + ", scadenza=" + data_scadenza + "]";
    } 

    
}
