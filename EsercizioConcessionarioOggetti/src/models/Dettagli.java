package models;

public class Dettagli {
   private String id;
private String numero;
private String marca;
private String  nome;
private String modello;
private int  numeroRuote;
private double prezzoBase;

    public Dettagli(String id, String numero, String marca, String nome, String modello, int numeroRuote, double prezzoBase) {
        this.id = id;
        this.numero = numero;
        this.marca = marca;
        this.nome = nome;
        this.modello = modello;
        this.numeroRuote = numeroRuote;
       this.prezzoBase=prezzoBase;
    }



   /* public static String Mezzo(String mezzo) {

        return mezzo;
    }*/
    /*public void setMezzo(String mezzo){
        this.mezzo=mezzo;
    }*/


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public int getNumeroRuote() {
        return numeroRuote;
    }

    public void setNumeroRuote(int numeroRuote) {
        this.numeroRuote = numeroRuote;
    }

    public double getPrezzoBase() {
        return prezzoBase;
    }

    public void setPrezzoBase(double prezzoBase) {

        this.prezzoBase = prezzoBase;
    }
    public void prezzo(String mezzo) {
        double prezzo = 0;
        switch (mezzo) {
            case "auto":
                if (Auto.getNumeroPorte() > 3)
                    prezzoBase += 200;
                break;
            case "bici":
                if(Bicicletta.isAmmortizzatori())
                    prezzoBase+=278;
                break;
            default:

            }
        }



    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", numero=" + numero +
                ", marca='" + marca + '\'' +
                ", nome='" + nome + '\'' +
                ", modello='" + modello + '\'' +
                ", numeroRuote=" + numeroRuote +
                ", prezzoBase=" + prezzoBase +
                '}';
    }
}
