package dao;

import Interfaces.IDao;
import Interfaces.IDatabase;
import database.Database;

import models.Album;
import models.Entity;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DaoAlbum implements IDao {

    private String queryInsert = "insert into album (name, date_release, artist_id) values (?,?,?);";
    private String queryUpdate = "update album set name = ?, date_release = ?, artist_id = ? where id = ?;";
    private IDatabase database;
    private static DaoAlbum istance;

    public DaoAlbum() {
        this.database =  Database.getIstance();
    }

    public synchronized static IDao getIstance(){
        if(istance==null)
            istance= new DaoAlbum();
        return istance;
    }
    @Override
    public void add(Entity e) {
        if (e instanceof Album) {
            Album a = (Album) e;
            database.executeUpdate(queryInsert, a.getName(), a.getDate_release() +"", a.getArtist_id() + "");
        }
    }

    @Override
    public List<Map<String, String>> read(String query, String... params) {
        return database.executeQuery(query);
    }

    @Override
    public List<Map<String, String>> read() {
        String query = IDao.queryRead("album"); // artist sostituisce tabella nell'IDao
        return database.executeQuery(query);

    }

    @Override
    public List<Entity> readAll() {
        List<Entity> ris = new ArrayList<>();
        // select * from bevanda;
        for( Map<String,String> album : read()) {
            Album a = new Album();
            a.fromMap(album);
            ris.add(a);
        }
        return ris;
    }

    @Override
    public void update(Entity e) {
        if (e instanceof Album)
            database.executeUpdate(queryUpdate,((Album)e).getName(),((Album)e).getDate_release() + "", ((Album)e).getArtist_id() + "");

    }

    @Override
    public void update(int id, String proprieta, String valoreNuovo) {
        database.executeUpdate(queryUpdate);
    }

    @Override
    public void delete(int id) {
        String query = IDao.queryDelete("album");
         database.executeUpdate(query);
    }

    @Override
    public Map<String, String> cercaPerId(int id) {
        List<Map<String,String>> coppia = database.executeQuery(IDao.queryReadOne("album"), id+"");
        Map<String,String> Album = coppia.get(0);
        return Album;
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
