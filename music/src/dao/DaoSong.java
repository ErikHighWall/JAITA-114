package dao;

import Interfaces.IDao;
import Interfaces.IDatabase;
import database.Database;
import models.Entity;
import models.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DaoSong implements IDao {



    private IDatabase database;
    private String tabella="song";

    public DaoSong(String nomeDB) {
        this.database =new Database(nomeDB);
    }

    public synchronized static IDao getIstance(){
        if(istance==null)
            istance= new DaoAlbum();
        return istance;
    }

    @Override
    public void add(Entity e) {
    String query = "insert into song (name,duration,album_id) values (?,?,?);";
        if (e instanceof Song) {
            Song s = (Song) e;
            database.executeUpdate(query,s.getName(),
                    String.valueOf(s.getDuration()),
                    String.valueOf(s.getAlbum_id()));
        }

    }

    @Override
    public List<Map<String, String>> read(String query, String... params) {


        return database.executeQuery(query,params);
    }

    @Override
    public List<Map<String, String>> read() {
        String query= IDao.queryRead(tabella);
        System.out.println(query);
        return database.executeQuery(query);
    }

    @Override
    public void update(Entity e) {
        String query="update song set name=?,duration=?,album_id=? where id=?";
        if (e instanceof Song) {
            Song s = (Song) e;
            database.executeUpdate(query, s.getName(),
                    String.valueOf(s.getDuration()),String.valueOf(s.getAlbum_id()), String.valueOf(e.getId()));
        }

    }

    @Override
    public void update(int id, String proprieta, String valoreNuovo) {
        String query= "update [tabella] set" +proprieta + "=? where id=?";


        query = query.replace("[tabella]", tabella);

        database.executeUpdate(query, valoreNuovo, String.valueOf(id));

    }

    @Override
    public void delete(int id) {

        database.executeUpdate(IDao.queryDelete(tabella),String.valueOf(id));

    }

    @Override
    public Map<String, String> cercaPerId(int id) {
        Map<String,String> ris= database.executeQuery(IDao.queryReadOne(tabella),String.valueOf(id)).get(0);
        return ris;
    }

    @Override
    public List<Entity> listOggetti(List<Map<String,String>> listaMappe) {
        List<Entity> ris= new ArrayList<>();
        Song s;
        for(Map<String,String>m:read()){
            s= new Song();
            s.fromMap(m);
            ris.add(s);
        }

        return ris;
    }



    public List<Map<String, String>> readRelazioneAlbum() {
        String query= "select *from song s join album a on s.album_id=a.id";
        return database.executeQuery(query);

    }

    public String stampaMappa(){

        String ris="";

        for(Map<String,String>m:readRelazioneAlbum()){
            ris+=m+"\n";
        }
        return ris;
    }


}
