package dao;


import Interfaces.IDao;
import database.Database;
import models.Artist;
import models.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DaoArtist implements IDao {

    //    private String query = "select * from artist";
    private Database db;

    public DaoArtist(String nomeDB){
        db = new Database(nomeDB);
    }
    public synchronized static IDao getIstance(){
        if(istance==null)
            istance= new DaoAlbum();
        return istance;
    }

    @Override
    public void add(Entity e) {
        String query = "insert into artist (record_label_id, name) values (?,?)";
        if(e instanceof Artist){
            db.executeUpdate(query, ((Artist)e).getRecord_label_id()+"", ((Artist)e).getName());
        }
    }

    @Override
    public List<Map<String, String>> read(String query, String... params) {
        List<Map<String,String>> ris;
        ris = db.executeQuery(IDao.queryReadOne("artist"), String.valueOf(params));
        return ris;
    }

    // ritorna lista di mappe
    @Override
    public List<Map<String, String>> read() {
        // questo metodo e' read generico e va a leggere tutta la tabella
        // con il metodo statico queryRead di IDao
        List<Map<String, String>> ris;
        ris = db.executeQuery(IDao.queryRead("artist"));
        return ris;
    }

    // ritorna lista di entita'
    @Override
    public List<Entity> readAll() {
        List<Entity> ris = new ArrayList<>();
        Entity e;
        for(Map<String,String> artist : read()){
            e = new Artist(
                    Integer.parseInt((artist).get("id")),
                    artist.get("name"),
                    Integer.parseInt((artist).get("record_label_id")));
            ris.add(e);
        }
        return ris;
    }

    @Override
    public void update(Entity e) {
        // update quindi executeUpdate
        // UPDATE artist
        // SET column1 = value1, column2 = value2, ...
        // WHERE condition;
        String queryUpdate = "update artist set nome = ?, record_label_id = ? where id = ?";
        if(e instanceof Artist){
            db.executeUpdate(queryUpdate, (((Artist) e).getName()),
                    String.valueOf(((Artist) e).getRecord_label_id()),
                    String.valueOf(e.getId()));
        }
    }

    @Override
    public void update(int id, String proprieta, String valoreNuovo) {
        // execureUpdate
        String queryUpdate = "update artist set " + proprieta + " = ? where id = ?";
        db.executeUpdate(queryUpdate, valoreNuovo, id+"");
    }

    @Override
    public void delete(int id) {
        // dml = executeUpdate(metodo fatto in idao,id)
        db.executeUpdate(IDao.queryDelete("artist"), String.valueOf(id));
    }

    @Override
    public Map<String, String> cercaPerId(int id) {
        String queryRicerca = "select * from artist where id = ?";
        List<Map<String, String>> mappa = db.executeQuery(queryRicerca,
                String.valueOf(id));
        return mappa.get(0);
    }

    @Override
    public List<Entity> listOggetti(List<Map<String, String>> listaMappe) {
        return null;
    }

    @Override
    public String stampaMappa(List<Map<String, String>> listaMappa) {
        return IDao.super.stampaMappa(listaMappa);
    }
}