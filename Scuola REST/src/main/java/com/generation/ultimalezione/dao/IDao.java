package com.generation.ultimalezione.dao;

import java.util.Map;

import com.generation.ultimalezione.models.Entity;

public interface IDao<TipoID,E extends Entity>{
    
    Long create(E e);
    Map<TipoID, E> read();
    void update(E e);
    void delete(TipoID id);
    E readById(TipoID id);
}
