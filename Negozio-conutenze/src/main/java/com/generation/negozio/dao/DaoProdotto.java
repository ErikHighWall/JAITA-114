package com.generation.negozio.dao;

import com.generation.negozio.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class DaoProdotto implements IDao{
    @Autowired
    private IDatabase database;
    private String query;
    @Autowired

    private ApplicationContext applicationContext;
    public DaoProdotto(){
        
    }

    @Override
    public void add(Entity e) {
        query = "insert into prodotti(nome , prezzo) values(?,?)";
        if (e instanceof Prodotto){
            database.executeUpdate(query,
                    ((Prodotto)e).getNome(),
                    String.valueOf(((Prodotto)e).getPrezzo()));
        }
        System.out.println("\u001B[32m Prodotto aggiunto correttamente \u001B[0m");
    }

    @Override
    public List<Map<String, String>> read(String query, String... params) {
        return database.executeQuery(query);
    }

    @Override
    public List<Map<String, String>> read() {
        query="select * from prodotti";
        return database.executeQuery(query);    }

    @Override
    public List<Entity> readAll() {
        List<Entity> ris=new ArrayList<>();
        Entity e;
        for(Map<String,String> m : read()) {
            //avendo messo @PArimary sul metodo che ci intwressa,
            //in automatico prenderà quello
           e= applicationContext.getBean(Prodotto.class,m);
            ris.add(e);
        }
        System.out.println(ris);
        return ris;
    }
    public String printAll(){
        for(Entity e : readAll()) {
            System.out.println(e.toString());
        }return "";
    }

    @Override
    public void update(Entity e) {
        query = "update prodotti set nome = ? , prezzo = ? where id = ?";
        if (e instanceof Prodotto){
            database.executeUpdate(query,
                    ((Prodotto)e).getNome(),
                    String.valueOf(((Prodotto)e).getPrezzo()),
                    String.valueOf(((Prodotto)e).getId()));
        }
        System.out.println("\u001B[32m Prodotto modificato correttamente \u001B[0m");
    }

    @Override
    public void update(int id, String proprieta, String valoreNuovo) {
        query ="update prodotti set " + proprieta + " = ? where id = ?";
        database.executeUpdate(query, valoreNuovo, String.valueOf(id));
        System.out.println("\u001B[32m Prodotto modificato correttamente \u001B[0m");

    }

    @Override
    public void delete(long id) {
        query="delete from prodotti where id=?";
        database.executeUpdate(query, String.valueOf(id));
        System.out.println("\u001B[32m Prodotto eliminato correttamente \u001B[0m");
    }


    @Override
    public Entity cercaPerId(Long id) {
        query="select * from prodotti where id = ?";
        Map<String,String> m;
        m= database.executeQuery(query, String.valueOf(id)).get(0);
      
        return applicationContext.getBean(Prodotto.class,
                "newProdotto", m);
    }
}
