package dao;

import Interfaces.IDao;
import Interfaces.IDatabase;
import database.Database;

import models.Entity;
import models.RecordLabel;
import models. RecordLabel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DaoRecordLabel implements IDao {
    private IDatabase database;
    private String queryInsert = "insert into record_label (nome) values (?,?)";
    private String queryRead = "select * from record_label";
    private String queryUpdate = "update record_label set nome = ? where id = ?";
    private String queryDelete = "delete from record_label where id = ?";
    private String queryReadOne = "select * from record_label where id = ?";
    public DaoRecordLabel(String nomeDB) {
        database = new Database(nomeDB);
    }
    public synchronized static IDao getIstance(){
        if(istance==null)
            istance= new DaoAlbum();
        return istance;
    }

    @Override
    public void add(Entity e) {
        if(e instanceof  RecordLabel){
            RecordLabel rl = ( RecordLabel) e;
            database.executeUpdate(queryInsert,rl.getName());
        }

    }

    @Override
    public List<Map<String, String>> read(String query, String... params) {
        return database.executeQuery(query);
    }

    @Override
    public List<Map<String, String>> read() {
        return database.executeQuery(queryRead);
    }

    @Override
    public void update(Entity e) {
        if(e instanceof  RecordLabel){
            RecordLabel rl = ( RecordLabel) e;
            database.executeUpdate(queryUpdate, rl.getName());
        }

    }

    @Override
    public void update(int id, String colonna, String valoreNuovo) {
        String queryUpdateValore = "update [tabella] set " + colonna + " = ? where id = ?";
        queryUpdateValore = queryUpdateValore.replace("[tabella]","Record_label");
        database.executeUpdate(queryUpdateValore,valoreNuovo,id+"");
    }

    @Override
    public void delete(int id) {
        database.executeUpdate(queryDelete,String.valueOf(id));
    }

    @Override
    public Map<String, String> cercaPerId(int id) {
        List<Map<String,String>> coppia = database.executeQuery(queryReadOne,String.valueOf(id));
        Map<String,String> Record_label = coppia.get(0);
        return Record_label;
    }

    @Override
    public List<Entity> listOggetti(List<Map<String, String>> listaMappe) {
        return null;
    }

    @Override
    public String stampaMappa(List<Map<String, String>> listaMappa) {
        return IDao.super.stampaMappa(listaMappa);
    }

    public List<Entity> readAll() {
        List<Entity> ris = new ArrayList<>();
        for (Map<String, String> recordLabel : read()) {
           RecordLabel rl = new  RecordLabel();
            rl.fromMap(recordLabel);
            ris.add(rl);
        }
        return ris;
    }

}
