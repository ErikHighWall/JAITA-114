package models;

import javax.swing.*;

public class Dottore extends Persona{
    private String specializzazione;
    private int anniLavoro;
    private boolean tirocinante;

    public Dottore(String nome, String cognome, String ddn, String residenza, String specializzazione, int anniLavoro, String si_no) {
        super(nome, cognome, ddn, residenza);
        this.specializzazione = specializzazione;
        this.anniLavoro = anniLavoro;
        setTirocinante(si_no);
    }

    /*
    -- stipendio dottori:
    ogni reparto ha uno stipendio diverso e un bonus per ogni anno di esperienza differente:
    Cardiologia: 1500 -> bonus 120
    ortopedia: 1300 -> bonus 100
    chirurgia: 2000 -> bonus 200
    Altri: 1350 -> bonus 110
   -- buonMedico() => Se ha almeno 3 anni di esperienza, ma se è del reparto pediatria è un buon medico a prescindere
    dagli anni di esperienza
    */

    public double stipendio(){
        double stipendio;
        if (specializzazione.equalsIgnoreCase("Cardiologo"))
            stipendio = 1500 + 120*anniLavoro;
        else if(specializzazione.equalsIgnoreCase("ortopedico"))
            stipendio = 1300 + 100*anniLavoro;
        else if (specializzazione.equalsIgnoreCase("chirurgia")) {
            stipendio = 2000 + 200 * anniLavoro;
        }
        else
            stipendio = 1350 + 110*anniLavoro;
        return stipendio;
    }


    public boolean buonMedico(){
        return (anniLavoro>=3 || specializzazione.equalsIgnoreCase("Oculista"));
    }


    public String getSpecializzazione() {
        return specializzazione;
    }

    public void setSpecializzazione(String specializzazione) {
        this.specializzazione = specializzazione;
    }

    public int getAnniLavoro() {
        return anniLavoro;
    }

    public void setAnniLavoro(int anniLavoro) {
        this.anniLavoro = anniLavoro;
    }

    public boolean isTirocinante() {
        return tirocinante;
    }

    public void setTirocinante(String si_no) {
//        if(si_no.equalsIgnoreCase("si"))
//            this.tirocinante = true;
//        else
//            this.tirocinante = false;

        this.tirocinante = (si_no.equalsIgnoreCase("si"));
    }

    @Override
    public String toString() {
        return "Dottore{" +
                super.toString() +
                ", specializzazione='" + specializzazione + '\'' +
                ", anniLavoro=" + anniLavoro +
                ", tirocinante=" + tirocinante +
                '}';
    }
}
