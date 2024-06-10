package com.generation.negozio.dao;




import java.util.List;
import java.util.Map;

import com.generation.negozio.models.Entity;

public interface IDao {

    void add(Entity e);
    List<Map<String,String>> read(String query, String...params);
    List<Map<String,String>> read();
    List<Entity> readAll();
    void update(Entity e);
    void update(int id, String proprieta,String valoreNuovo);
    void delete(long id);
    Entity cercaPerId(Long id);
    String printAll();

}
