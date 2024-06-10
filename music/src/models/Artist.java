package models;

public class Artist extends  Entity{
    private String name;
    private int record_label_id;

    public Artist() {

    }

    public Artist(int id, String name, int record_label_id) {
        super(id);
        this.name = name;
        this.record_label_id = record_label_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRecord_label_id() {
        return record_label_id;
    }

    public void setRecord_label_id(int record_label_id) {
        this.record_label_id = record_label_id;
    }

    @Override
    public String toString() {
        return super.toString()+
                "name='" + name + '\'' +
                ", record_label_id=" + record_label_id;

    }
}
