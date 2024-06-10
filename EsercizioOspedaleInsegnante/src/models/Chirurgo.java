package models;

public class Chirurgo extends Dottore{
    private int nInterventi;
    private int interventiRiusciti;

    public Chirurgo(String nome, String cognome, String ddn, String residenza, String specializzazione, int anniLavoro,
                    String si_no, int nInterventi, int interventiRiusciti) {
        super(nome, cognome, ddn, residenza, specializzazione, anniLavoro, si_no);
        this.nInterventi = nInterventi;
        this.interventiRiusciti = interventiRiusciti;
    }


    // siccome ho già un metodo stipendio() nella classe padre Dottore,
    // implementandone uno qui con la stessa firma vado a sovrascrivere quello ereditato!!
    public double stipendio(){
        double stipendio = 2000 + 0.2*2000 + 20*interventiRiusciti -(nInterventi-interventiRiusciti)*10 +15*(nInterventi/20);
        return stipendio;
    }

    // siccome ho già un metodo buonMedico() nella classe padre Dottore,
    // implementandone uno qui con la stessa firma vado a sovrascrivere quello ereditato!!
    public boolean buonMedico(){
        return ((getAnniLavoro()>=3
                 || getSpecializzazione().equalsIgnoreCase("Oculista"))
                                                                                    && interventiRiusciti >= nInterventi/3);
    }

    public int getnInterventi() {
        return nInterventi;
    }

    public void setnInterventi(int nInterventi) {
        this.nInterventi = nInterventi;
    }

    public int getInterventiRiusciti() {
        return interventiRiusciti;
    }

    public void setInterventiRiusciti(int interventiRiusciti) {
        this.interventiRiusciti = interventiRiusciti;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", nInterventi=" + nInterventi +
                ", interventiRiusciti=" + interventiRiusciti +
                '}';
    }
}
