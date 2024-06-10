package com.generation.ultimalezione.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.generation.ultimalezione.models.Classe;
import com.generation.ultimalezione.models.Entity;

import lombok.Data;


@Service
@Data
public class DaoClasse implements IDao<Long,Classe>{
    
    private final Database database;

    private final ApplicationContext context;

    @Override
    public Long create(Classe e) {
       String query="INSERT INTO classi(sezione) VALUES(?)";
       Long id = database.executeDML(query,e.getSezione());
         return id;
    }

    @Override
    public Map<Long, Classe> read() {
        String query="select * from classi";
        Map<Long,Map<String,String>> classi = database.executeDQL(query);
        Map<Long,Classe> classiMap = new HashMap<>();
        for(Map<String,String> map :classi.values()){
            Classe c=context.getBean(Classe.class,map);
            classiMap.put(c.getId(), c);
        }
        return classiMap;
    }

    @Override
    public void update(Classe e) {
        String query = "UPDATE classi SET sezione = ? WHERE id = ?";
        database.executeDML(query, e.getSezione(), String.valueOf(e.getId()));
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM classi WHERE id = ?";
        database.executeDML(query, String.valueOf(id));
    }

    //cerco una classe per id
    @Override
    public Classe readById(Long id) {
        String query = "SELECT * FROM classi WHERE id = ?";
        Map<Long,Map<String,String>> classi = database.executeDQL(query, String.valueOf(id));
        Classe c = null;
        for(Map<String,String> map :classi.values()){
            c=context.getBean(Classe.class,map);
        }
        return c;
    }


    //metodo che cerca la classe di un determinato studente
    public Classe readByIdStudente(Long id) {
        String query="select c.* from classi c join studenti s on c.id = s.id_classe"
        +" where s.id = ?";
        Map<Long,Map<String,String>> classi = database.executeDQL(query, String.valueOf(id));
        Classe c=null;
        for(Map<String,String> map :classi.values()){
            c=context.getBean(Classe.class,map);
        }
        return c;
    }

     //restituisce tutte le classi di un determinato docente
     public Map<Long, Entity> readByIdDocente(Long idDocente) {
        String query = "select c.* from classi c join classi_docenti cd on c.id = cd.id_classe where cd.id_docente=?";
        Map<Long, Map<String, String>> result = database.executeDQL(query, String.valueOf(idDocente));
        Map<Long, Entity> ris = new HashMap<>();

        for(Map<String, String> params : result.values()){
            Classe c = context.getBean(Classe.class, params);
            ris.put(Long.parseLong(params.get("id")), c);
        }
        return ris;
    }






}
