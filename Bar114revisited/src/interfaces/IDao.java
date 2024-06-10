package interfaces;

import models.Entity;

import java.util.List;
import java.util.Map;

public interface IDao {
//deve injcapsulare i dao e conterra solo le firme dei metodi crud
    void add(Entity e);
    List<Map<String,String>> read (String query,String...params);
    List<Map<String,String>> read ();

    void update(Entity e);
    void update(int id,String proprieta,String valoreNuovo);
    void delete(int id);

    Map<String,String> cercaPerId(int id);

}
