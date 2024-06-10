package services;

import dao.DaoBevande;
import dao.DaoIngredienti;
import interfaces.IServiceCliente;
import models.Bevanda;
import models.Entity;
import models.Ingrediente;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceCliente implements IServiceCliente {



    private DaoBevande daoLocaleBevanda;
    private DaoIngredienti daoLocaleIngrediente;
private ServiceGestore listaComp;

    public ServiceCliente(String nomeDB){
        daoLocaleBevanda= new DaoBevande(nomeDB);
        daoLocaleIngrediente= new DaoIngredienti(nomeDB);

    }
    @Override
    public List<Entity> bevande() {
       return null;
    }

    @Override
    public List<Entity> ingredienti() {
        return null;
    }

    @Override
    public List<Entity> listaCompleta() {
        return null;
    }
    /*private List<Ingrediente> ingredientiBevanda(int idBevanda){
        List<Ingrediente> listaIngredienti = new ArrayList<>();
        Entity entita= null;
        for(Map<String,String> rigaContiene:daoLocaleBevanda.readIngredienti(idBevanda))
        {
            if(rigaContiene.containsKey("idIngrediente")){
                //daoIngredienti.cercaperID(id) select*from ingrediente where id= 2;
                entita=  daoLocaleIngrediente.cercaPerId(Integer.parseInt(rigaContiene.get("idIngrediente")));
                if(entita!=null && entita instanceof  Ingrediente)
                    listaIngredienti.add((Ingrediente) entita);
            }
        }

        return listaIngredienti;
    }*/

   /* @Override
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

    }*/


    @Override
    public List<Entity> cercaBevandaPerIngrediente(int id) {
      return daoLocaleBevanda.readBevandeFromIngredientee(id);
    }

    @Override
    public Entity cercaBevandaPerNome(String nome) {
        return daoLocaleBevanda.cercaBevandaNome(nome);
    }
}
