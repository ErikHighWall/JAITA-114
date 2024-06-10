package models;

public class RecordLabel extends Entity{
    private String name;

    public RecordLabel() {

    }

    public RecordLabel(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString()+
                "name= " + name;
    }
}
