package models;

public class Paziente extends Persona{
    private String reparto;
    private int giorniRicovero;
    private double costoGiornaliero;

    public Paziente(String nome, String cognome, String ddn, String residenza, String reparto, int giorniRicovero, double costoGiornaliero) {
        super(nome, cognome, ddn, residenza);
        this.reparto = reparto;
        this.giorniRicovero = giorniRicovero;
        this.costoGiornaliero = costoGiornaliero;
    }

    public String getReparto() {
        return reparto;
    }

    public void setReparto(String reparto) {
        this.reparto = reparto;
    }

    public int getGiorniRicovero() {
        return giorniRicovero;
    }

    public void setGiorniRicovero(int giorniRicovero) {
        this.giorniRicovero = giorniRicovero;
    }

    public double getCostoGiornaliero() {
        return costoGiornaliero;
    }

    public void setCostoGiornaliero(double costoGiornaliero) {
        this.costoGiornaliero = costoGiornaliero;
    }

    @Override
    public String toString() {
        return "Paziente{" +
                super.toString() +
                ", reparto='" + reparto + '\'' +
                ", giorniRicovero=" + giorniRicovero +
                ", costoGiornaliero=" + costoGiornaliero +
                '}';
    }
}
