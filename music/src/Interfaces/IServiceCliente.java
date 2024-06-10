package Interfaces;

import models.Entity;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IServiceCliente {

    List<Entity> leggiAlbum() throws InvocationTargetException, IllegalAccessException;
    Entity readOneAlbum(int id) throws InvocationTargetException, IllegalAccessException;


    List<Entity> leggiRecordLabel() throws InvocationTargetException, IllegalAccessException;
    Entity readOneRecordLable(int id) throws InvocationTargetException, IllegalAccessException;

    List<Entity> cercaArtisti() throws InvocationTargetException, IllegalAccessException;
    Entity readOneArtist(int id) throws InvocationTargetException, IllegalAccessException;


    List<Entity> listaSong() throws InvocationTargetException, IllegalAccessException;
    Entity readOneSong(int id) throws InvocationTargetException, IllegalAccessException;

    public default String stampaLista (List<Entity> listaEntita){
        String ris = "";
        for (Entity e : listaEntita){
            ris += e.toString() + "\n";
        }
        return ris;
    }

}
