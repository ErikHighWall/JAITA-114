package Interfaces;

import models.Entity;

import java.util.List;
import java.util.Map;

public interface IDao {
    void add(Entity e);
    List<Map<String,String>> read (String query, String...params);
    List<Map<String,String>> read ();

    void update(Entity e);
    void update(int id,String proprieta,String valoreNuovo);
    void delete(int id);
    List<Entity> readAll();
    Map<String,String> cercaPerId(int id);
    public List<Entity> listOggetti( List<Map<String,String>> listaMappe);
    static String queryRead(String nomeTab){

        String ris="select*from [tabella]";
       ris=ris.replace("[tabella]",nomeTab);
       return ris;

    }
    static String queryReadOne(String nomeTab){

        String ris="select*from [tabella] where id=?";
        ris=ris.replace("[tabella]",nomeTab);
        return ris;

    }


    static String queryDelete(String nomeTab){
        String ris="delete from [tabella] where id=?";
        ris=ris.replace("[tabella]",nomeTab);
        return ris;
    }
    default String stampaMappa( List<Map<String, String>> listaMappa){
        String ris="";

        for(Map<String,String>m:listaMappa){
            ris+=m+"\n";
        }
        return ris;
    }


}
