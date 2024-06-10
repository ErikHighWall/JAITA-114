import models.Concessionario;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String percorsoAuto="res/automobili.txt";
        String percorsoBici="res/bici.txt";
        Concessionario concessionario1= new Concessionario(percorsoAuto,percorsoBici);
        concessionario1.menu();
    }
}