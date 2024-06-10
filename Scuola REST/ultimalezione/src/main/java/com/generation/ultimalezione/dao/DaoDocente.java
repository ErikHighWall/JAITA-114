package com.generation.ultimalezione.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.generation.ultimalezione.models.Classe;
import com.generation.ultimalezione.models.Docente;

import lombok.Data;

@Service
@Data
public class DaoDocente implements IDao<Long, Docente>{
    
    
    private final Database database;

    private final ApplicationContext context;
    
    @Override
    public Long create(Docente e) {
        String query = "insert into persone(nome, cognome, data_nascita) values(?, ?, ?)";
        Long id = database.executeDML(query, e.getNome(), e.getCognome(), String.valueOf(query));
        query = "insert into docenti(id) values (?)";
        database.executeDML(query, String.valueOf(id));

        query = "insert into classi_docenti(id_docente, id_classe) values(?, ?)";
        for(Classe c : e.getClassi()){
            database.executeDML(query, String.valueOf(id), String.valueOf(c.getId()));
        }
        return id;
    }

    @Override
    public Map<Long, Docente> read() {
        String query = "select p.* from persone p join docenti d on p.id = d.id";
        Map<Long, Map<String, String>> result = database.executeDQL(query);
        Map<Long, Docente> ris = new HashMap<>();

        for(Map<String, String> params : result.values()){
            Docente d = context.getBean(Docente.class, params);
            ris.put(Long.parseLong(params.get("id")), d);
        }
        return ris;
    }

    @Override
    public void update(Docente e) {
        String query="UPDATE persone SET nome=?,cognome=?,data_nascita=? WHERE id=?";
        database.executeDML(query, e.getNome(),e.getCognome(),
        String.valueOf(e.getData_nascita()),
        String.valueOf(e.getId()));
    }

    @Override
    public void delete(Long id) {
        String query="DELETE FROM persone WHERE id=?";
        database.executeDML(query, String.valueOf(id));
    }

    @Override
    public Docente readById(Long id) {
        String query = "select p.* from persone p join docenti d on p.id = d.id where d.id=?";
        Map<Long, Map<String, String>> result = database.executeDQL(query, String.valueOf(id)) ;
        Docente d = null;
        for(Map<String, String> params : result.values()){
            d = context.getBean(Docente.class, params);
        }
        return d;
    }
    
}
