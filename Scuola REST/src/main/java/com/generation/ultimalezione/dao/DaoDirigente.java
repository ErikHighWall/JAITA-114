package com.generation.ultimalezione.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.generation.ultimalezione.models.Dirigente;

import lombok.Data;

@Service
@Data
public class DaoDirigente implements IDao<Long, Dirigente>{
    
    
    private final Database database;
    private final ApplicationContext context;
    
    @Override
    public Long create(Dirigente e) {
        String query = "insert into persone(nome, cognome, data_nascita) values(?, ?, ?)";
        Long id = database.executeDML(query, e.getNome(), e.getCognome(), String.valueOf(query));
        query = "insert into dirigenti(id) values (?)";
        database.executeDML(query, String.valueOf(id));
        return id;
    }

    @Override
    public Map<Long, Dirigente> read() {
        String query = "select p.* from persone p join dirigenti d on p.id = d.id";
        Map<Long, Map<String, String>> result = database.executeDQL(query);
        Map<Long, Dirigente> ris = new HashMap<>();

        for(Map<String, String> params : result.values()){
            Dirigente d = context.getBean(Dirigente.class, params);
            ris.put(Long.parseLong(params.get("id")), d);
        }
        return ris;
    }

    @Override
    public void update(Dirigente e) {
        String query = "update persone set nome=?, cognome=?, data_nascita=? where id=?";
        database.executeDML(query, e.getNome(), e.getCognome(), String.valueOf(e.getData_nascita()), String.valueOf(e.getId()));
    }

    @Override
    public void delete(Long id) {
        String query = "delete from persone where id=?";
        database.executeDML(query, String.valueOf(id));
    }

    @Override
    public Dirigente readById(Long id) {
        String query = "select p.* from persone p join dirigenti d on p.id = d.id where p.id = ?";
        Map<Long, Map<String, String>> result = database.executeDQL(query, String.valueOf(id));
        Dirigente ris = null;
        for(Map<String, String> params : result.values()){
            ris = context.getBean(Dirigente.class, params);
        }
        return ris;
    }


    


    
}
