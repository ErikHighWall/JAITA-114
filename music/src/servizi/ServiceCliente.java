package servizi;

import Interfaces.IDao;
import Interfaces.IServiceCliente;
import dao.DaoAlbum;
import dao.DaoArtist;
import dao.DaoRecordLabel;
import dao.DaoSong;
import interfaces.IDao;
import interfaces.IServiceCliente;
import models.Entity;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ServiceCliente implements IServiceCliente {



    private IDao daoAlbum;
    private IDao daoArtist;
    private IDao daoRecordLabel;
    private IDao daoSong;

    public ServiceCliente (){
        this.daoAlbum = DaoAlbum.getIstance();
        this.daoArtist = DaoArtist.getIstance();
        this.daoRecordLabel = DaoRecordLabel.getIstance();
        this.daoSong = DaoSong.getIstance();

    }

    @Override
    public List<Entity> leggiAlbum() throws InvocationTargetException, IllegalAccessException {

        return daoAlbum.readAll();
    }

    @Override
    public List<Entity> cercaArtisti() throws InvocationTargetException, IllegalAccessException {
        return daoArtist.readAll();
    }

    @Override
    public List<Entity> leggiRecordLabel() throws InvocationTargetException, IllegalAccessException {
        return daoRecordLabel.readAll();
    }

    @Override
    public List<Entity> listaSong() throws InvocationTargetException, IllegalAccessException {
        return daoSong.readAll();
    }

    @Override
    public Entity readOneAlbum(int id) throws InvocationTargetException, IllegalAccessException {
        return daoAlbum.cercaPerIdEntita(id);
    }

    @Override
    public Entity readOneArtist(int id) throws InvocationTargetException, IllegalAccessException {
        return daoArtist.cercaPerIdEntita(id);
    }

    @Override
    public Entity readOneSong(int id) throws InvocationTargetException, IllegalAccessException {
        return daoSong.cercaPerIdEntita(id);
    }

    @Override
    public Entity readOneRecordLable(int id) throws InvocationTargetException, IllegalAccessException {
        return daoRecordLabel.cercaPerIdEntita(id);
    }
}
