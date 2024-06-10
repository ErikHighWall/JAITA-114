package models;

public class Song extends Entity{

    private String name;
    private double duration;
    private int album_id;

    public Song() {

    }

    public Song(int id, String name, double duration, int album_id) {
        super(id);
        this.name = name;
        this.duration = duration;
        this.album_id = album_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    @Override
    public String toString() {
        return super.toString()+
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", album_id=" + album_id;
    }
}
