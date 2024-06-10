package com.generation.negozio.models;

public class Prodotto extends Entity{


    private String nome;
    private double prezzo;
   
    public Prodotto() {
    }

    public Prodotto(Long id, String nome, double prezzo) {
        super(id);
        this.nome = nome;
        this.prezzo = prezzo;
    }

    //get
    public String getNome() {

    

        return nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    //set
    public void setNome(String nome) {


        this.nome = nome;
    }
    
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }


    @Override
    public String toString() {
        return "Prodotto [id=" + getId() + ", nome=" + nome + ", prezzo=" + prezzo + "]";
    }

    

}
