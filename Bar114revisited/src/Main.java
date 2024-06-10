import interfaces.IServiceGestore;
import services.ServiceGestore;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ServiceGestore gestore= new ServiceGestore("barjaita114");

       // System.out.println( gestore.bevande());

        LocalDate data = java.time.LocalDate.parse("2024-04-16");
        System.out.println(data);

    }
}