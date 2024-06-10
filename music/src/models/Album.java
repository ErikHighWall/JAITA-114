package models;

import java.util.Date;

public class Album extends Entity {

    private String name;
     private Date date_release;
    private int artist_id ;

    public Album() {

    }

    public Album(int id, String name, Date date_release, int artist_id) {
        super(id);
        this.name = name;
        this.date_release = date_release;
        this.artist_id = artist_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate_release() {
        return date_release;
    }

    public void setDate_release(Date date_release) {
        this.date_release = date_release;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    @Override
    public String toString() {
        return  super.toString()+
                "name='" + name + '\'' +
                ", date_release=" + date_release +
                ", artist_id=" + artist_id;
    }
}
