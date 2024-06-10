package interfaces;

import models.Entity;

import java.util.List;

//incapsula i service del gestore
public interface IServiceGestore {


    List<Entity> bevande();
    List<Entity> ingredienti();
    List<Entity> listaCompleta();
    void addBevanda(Entity e);
    void eliminaBevanda(int id);
    void addIngrediente(Entity e);
    void eliminaIngrediente(int id);
    void updateBevanda(Entity e);
    void aggiornaBevanda(Entity e);

    public default String stampaLista(){
        String ris="";


       /* for(Entity e:listaCompleta()){
            for(int i=0;((Bevanda)e).getListaIngredienti().size()>i;i++) {

                ris += ((Bevanda) e).getListaIngredienti().get(i).getNome();


            }
            System.out.println(ris);
           // ris2+=ris+"\n";
            ris="";

        }*/
        for(Entity e:listaCompleta()){
            ris+=e.toString()+"\n";
        }

        return ris;
    }
}
