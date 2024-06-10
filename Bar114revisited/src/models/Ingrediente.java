package models;

public class Ingrediente extends Entity{
    private String nome;
    private double prezzo,gradazioneAlcolica;



    public Ingrediente(){}
    public Ingrediente(int id, String nome, double prezzo, double gradazioneAlcolica) {
        super(id);
        this.nome = nome;
        this.prezzo = prezzo;
        this.gradazioneAlcolica = gradazioneAlcolica;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public double getGradazioneAlcolica() {
        return gradazioneAlcolica;
    }

    public void setGradazioneAlcolica(double gradazioneAlcolica) {
        this.gradazioneAlcolica = gradazioneAlcolica;
    }

    @Override
    public String toString() {
        return "Ingrediente "+super.toString() +
                "nome='" + nome + '\'' +
                ", prezzo=" + prezzo +
                ", gradazioneAlcolica=" + gradazioneAlcolica +"%";
    }
}
