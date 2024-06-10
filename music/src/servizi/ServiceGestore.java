package servizi;

import dao.DaoAlbum;
import dao.DaoSong;
import interfaces.IDao;
import interfaces.IServiceGestore;
import models.Entity;

public class ServiceGestore implements IServiceGestore {

    private IDao daoAlbum;
    private IDao daoSong;

    public ServiceGestore(){
        this.daoAlbum = DaoAlbum.getInstance();
        this.daoSong = DaoSong.getInstance();
    }
    @Override
    public void addAlbum(Entity e) {
        daoAlbum.add(e);
    }

    @Override
    public void sostituteAlbum(Entity e) {
         daoAlbum.update(e);
    }

    @Override
    public void deleteAlbum(int id) {
        daoAlbum.delete(id);
    }

    @Override
    public void updateAlbumPerId(int id, String colonna, String valore) {
        daoAlbum.update(id,colonna,valore);
    }

    @Override
    public void addSong(Entity e) {
        daoSong.add(e);
    }
    public void deleteSong(int id){
        daoSong.delete(id);
    }


}
