package models;

public class Prodotto {
    private int codiceId;
    private String titolo;

    // Costruttore
    public Prodotto(int codiceId, String titolo) {
        this.codiceId = codiceId;
        this.titolo = titolo;
    }

    // Metodi getter e setter
    public int getCodiceId() {
        return codiceId;
    }

    public void setCodiceId(int codiceId) {
        this.codiceId = codiceId;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "codiceId=" + codiceId +
                ", titolo='" + titolo + '\'' +
                '}';
    }
}
