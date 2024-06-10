package dao;

import database.Database;
import interfaces.IDao;
import interfaces.IDatabase;
import models.Entity;
import models.Ingrediente;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DaoIngredienti implements IDao {

    private IDatabase database;
    private String queryInserimento ="insert into ingrediente (nome,prezzo,gradazioneAlcolica)"+
            "values(?,?,?)";
    private String queryRead =" select*from ingrediente";
    private String queryUpdate="";
    private String queryReadOne="select*from ingrediente where id = ?";

    public DaoIngredienti(String nomeDB){
        database= new Database(nomeDB);
    }
    @Override
    public void add(Entity e) {

        if(e instanceof Ingrediente i)
database.executeUpdate(queryInserimento,i.getNome(),String.valueOf(i.getPrezzo())
,String.valueOf(i.getGradazioneAlcolica()));
    }

    @Override
    public List<Map<String, String>> read(String query, String... params) {
        return database.executeQuery(query);
    }

    @Override
    public List<Map<String, String>> read() {
        return   database.executeQuery(queryRead);
    }

    /*@Override
    public List<Entity> readAll() {

        List<Entity> ris = new ArrayList<>();
        Entity entity;

        for(  Map<String,String> ingr: read())
        {
            entity= new Ingrediente(Integer.parseInt(ingr.get("id")),
                    ingr.get("nome"),
                    Double.parseDouble( ingr.get("prezzo")),
                   Double.parseDouble( ingr.get("gradazioneAlcolica"))
            );
            ris.add(entity);
        }
        return ris;
    }*/

    @Override
    public void update(Entity e) {

    }

    @Override
    public void update(int id, String proprieta, String valoreNuovo) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Map<String,String> cercaPerId(int id) {
        List<Map<String,String>>listaMappa= database.executeQuery(queryReadOne,String.valueOf(id));
        return listaMappa.get(0);


    }
}
