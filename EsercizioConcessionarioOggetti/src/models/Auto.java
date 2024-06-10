package models;

public class Auto extends Dettagli {


    private int numeroPasseggeri;
    private static int numeroPorte;


    public Auto(String id, String numero, String marca, String nome, String modello, int numeroRuote, double prezzoBase, int numeroPasseggeri, int numeroPorte) {
        super(id, numero, marca, nome, modello, numeroRuote, prezzoBase);
        this.numeroPasseggeri = numeroPasseggeri;
        this.numeroPorte = numeroPorte;
    }

    /*public void  prezzoFinale() {
        double ris=0;
    if(numeroPorte>3) {
        super.setPrezzoBase(super.getPrezzoBase()+200);

    }
    }*/


    public int getNumeroPasseggeri() {
        return numeroPasseggeri;
    }

    public void setNumeroPasseggeri(int numeroPasseggeri) {
        this.numeroPasseggeri = numeroPasseggeri;
    }

    public static int getNumeroPorte() {
        return numeroPorte;
    }

    public void setNumeroPorte(int numeroPorte) {
        this.numeroPorte = numeroPorte;
    }

    @Override
    public String toString() {
        return super.toString() +
                "numeroPasseggeri=" + numeroPasseggeri +
                ", numeroPorte=" + numeroPorte +
                '}';
    }
}
