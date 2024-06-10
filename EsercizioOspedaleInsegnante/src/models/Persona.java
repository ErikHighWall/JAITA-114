package models;

public class Persona {
    private String nome;
    private String cognome;
    private int eta;
    private String residenza;

    public Persona(String nome, String cognome, String ddn, String residenza){
        this.nome=nome;
        this.cognome=cognome;
        setEta(ddn);
        this.residenza=residenza;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(String ddn) {
        String[] dataVettore = ddn.split("-");
        this.eta = 2024 - Integer.parseInt(dataVettore[0]);
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    @Override
    public String toString() {
        return "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", eta=" + eta +
                ", residenza='" + residenza + '\'';
    }
}
