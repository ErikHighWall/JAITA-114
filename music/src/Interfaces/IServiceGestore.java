package Interfaces;

import models.Entity;

import java.util.List;

public interface IServiceGestore {

    void addAlbum(Entity e);
    void sostituteAlbum(Entity e);   //sostituisce una riga con id corrispondente
    void deleteAlbum(int id);
    void updateAlbumPerId(int id, String colonna, String valore);
    void addSong(Entity e);
    void deleteSong(int id);
}
