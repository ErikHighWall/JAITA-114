package services;

import dao.DaoBevande;
import dao.DaoIngredienti;
import interfaces.IServiceGestore;
import models.Bevanda;
import models.Entity;
import models.Ingrediente;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceGestore implements IServiceGestore {
// un service deve poter accedere alle info delle bevande -> DaoBevande
    //anche agli ingredeienti --> DaoIngredienti

    private DaoIngredienti daoIngredienti;
    private DaoBevande daoBevande;



    public ServiceGestore(String nomeDB){
        daoIngredienti= new DaoIngredienti(nomeDB);
        daoBevande = new DaoBevande(nomeDB);
    }


    @Override
    public List<Entity> bevande() {
        List<Entity> ris = new ArrayList<>();
        Entity e= new Bevanda();
        for(int i=0;i<daoBevande.read().size();i++) {
            e.fromMap(daoBevande.read().get(i));

            ris.add(e);
        }
       return ris;
    }

    @Override
    public List<Entity> ingredienti() {


        return null;
    }

    //questo metodo ha il compito di associare ad ogni bevanda la lista
    //dei suoi ingredienti
    //READiNGREDIENTE -> select* from contiene where idBevanda=2;
    //[id:1,  idBevanda:2, idIngrediente: 2, quantità:110
    //id:2 , idBevanda=2,idIngrediente:3,QUANTITà: 110;
    //id:3 , idBevanda=2,idIngrediente:4,QUANTITà: 110;
    //ingredienti associati: 2,3,4
    //cheido al daoIngredienti se esiste la chiave


   public List<Ingrediente> ingredientiBevanda(int idBevanda){
      /* List<Ingrediente> listaIngredienti = new ArrayList<>();
       Entity entita= null;
       for(Map<String,String> rigaContiene:daoBevande.readIngredienti(idBevanda))
       {
           if(rigaContiene.containsKey("idIngrediente")){
               //daoIngredienti.cercaperID(id) select*from ingrediente where id= 2;
             entita=  daoIngredienti.cercaPerId(Integer.parseInt(rigaContiene.get("idIngrediente")));
               if(entita!=null && entita instanceof  Ingrediente)
                   listaIngredienti.add((Ingrediente) entita);
          }
       }*/

       return null;
    }
    //tramite il setListaIngredienti posso settare la lista degli ingredienti
    //di ciascuna bevanda
    //prenderò la lista delle bevande e per ognibevanda invocherò il setLista
    //a cui passerò la lista di ingredienti in basse all'id della bevanda,tramite il metodo
    //ingredienti bevanda
    @Override
    public List<Entity> listaCompleta() {

        List<Entity> ris= new ArrayList<>();
        Entity e=null;

        for(Entity bevanda:bevande())
        {
            List<Ingrediente> ingredienti = ingredientiBevanda(bevanda.getId());
            Bevanda b= (Bevanda)bevanda;
            b.setListaIngredienti(ingredienti);
      ris.add(bevanda);
        }

        return ris;
    }

    @Override
    public void addBevanda(Entity e) {
        daoBevande.add(e);

    }

    @Override
    public void eliminaBevanda(int id) {
 daoBevande.delete(id);
    }

    @Override
    public void addIngrediente(Entity e) {

    }

    @Override
    public void eliminaIngrediente(int id) {

    }

    @Override
    public void updateBevanda(Entity e) {

    }

    @Override
    public void aggiornaBevanda(Entity e) {

    }
}
