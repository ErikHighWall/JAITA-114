package models;

public class Bicicletta extends  Dettagli{
    private static boolean ammortizzatori;
private boolean isFreni;
private int nMarce;

    public Bicicletta(String id, String numero, String marca, String nome, String modello, int numeroRuote, double prezzoBase, boolean ammortizzatori, boolean isFreni, int nMarce) {
        super(id, numero, marca, nome, modello, numeroRuote, prezzoBase);
        this.ammortizzatori = ammortizzatori;
        this.isFreni = isFreni;
        this.nMarce = nMarce;
    }

    public static boolean isAmmortizzatori() {
        return ammortizzatori;
    }

    public void setAmmortizzatori(boolean ammortizzatori) {
        this.ammortizzatori = ammortizzatori;
    }

    public boolean isFreni() {
        return isFreni;
    }

    public void setFreni(boolean freni) {
        isFreni = freni;
    }

    public int getnMarce() {
        return nMarce;
    }

    public void setnMarce(int nMarce) {
        this.nMarce = nMarce;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "ammortizzatori=" + ammortizzatori +
                ", isFreni=" + isFreni +
                ", nMarce=" + nMarce +
                '}';
    }
}
