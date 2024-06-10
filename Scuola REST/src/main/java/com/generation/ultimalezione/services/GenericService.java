package com.generation.ultimalezione.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.generation.ultimalezione.dao.IDao;
import com.generation.ultimalezione.models.Entity;

import lombok.Data;

@Data
public abstract class GenericService<TipoID, //il tipo dell'id
                                    E extends Entity,//una entity che estende entity
                                    D extends IDao<TipoID,E>> 
                                //un dao che implementa idao con tipo id e entity
                                //usiamo extends perché vale anche per le interfacce   
                                {

    @Autowired
    private D dao;

    @Autowired
    private ApplicationContext context;

    //scrivo un findAll generico quindi invece di
    //usare i tipo specifici uso i tipi generici
    public List<E> findAll(){
        //restiruisco tutti gli elementi presenti nel database
        //che verranno poi recuperati dallo specifico dao
        Map<TipoID,E> result = dao.read();
        List<E> list = new ArrayList<>();
        for(E e : result.values()){
            list.add((E)e);
        }
        return list;
    }

    public E findById(TipoID id){
        E e = dao.readById(id);  
        return e;
    }

    public boolean update(Map<String,String> map){
        //devo trasformare la mappa in un oggetto di tipo E
        E e = createEntity(map);
        getDao().update(e);
        return true;
    }

    public abstract E createEntity(Map<String,String> map);

    public boolean delete(TipoID id){
        dao.delete(id);
        return true;
    }





    
}
