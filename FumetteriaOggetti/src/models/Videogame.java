package models;

public class Videogame extends Prodotto{
    private String console;
    private String genere;
    private String studio_sviluppo;
    private int voto_critica;

    // Costruttore


    public Videogame(int codiceId, String titolo, String console, String genere, String studio_sviluppo, int voto_critica) {
        super(codiceId, titolo);
        this.console = console;
        this.genere = genere;
        this.studio_sviluppo = studio_sviluppo;
        this.voto_critica = voto_critica;
    }
public void aggiornoVideogame(int codiceId, String titolo, String console, String genere, String studio_sviluppo, int voto_critica){
        setCodiceId(codiceId);
        setTitolo(titolo);
        setConsole(console);
        setGenere(getConsole());
        setStudio_sviluppo(studio_sviluppo);
        setVoto_critica(voto_critica);
}
    // Metodi getter e setter
    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getStudio_sviluppo() {
        return studio_sviluppo;
    }

    public void setStudio_sviluppo(String studio_sviluppo) {
        this.studio_sviluppo = studio_sviluppo;
    }

    public int getVoto_critica() {
        return voto_critica;
    }

    public void setVoto_critica(int voto_critica) {
        this.voto_critica = voto_critica;
    }

    @Override
    public String toString() {
        return super.toString() +
                "console='" + console + '\'' +
                ", genere='" + genere + '\'' +
                ", studio_sviluppo='" + studio_sviluppo + '\'' +
                ", voto_critica=" + voto_critica +
                '}';
    }
}
