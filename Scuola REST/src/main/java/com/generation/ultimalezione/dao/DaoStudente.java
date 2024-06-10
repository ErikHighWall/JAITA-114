package com.generation.ultimalezione.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.generation.ultimalezione.models.Entity;
import com.generation.ultimalezione.models.Studente;

import lombok.Data;

@Service
@Data //genera i getter e setter,RequiredArgsConstructor
public class DaoStudente implements IDao<Long,Studente>{

    //per avere una dipendenza posso iniettarla con @Autowired
    //@Autowired
    //private DatabaseMySql databaseMySql;
    private final Database database;

    private final ApplicationContext context;


    @Override
    public Long create(Studente e) {
        //prima devo inserire un record nella tabella persona vista la relazione
        //1 a 1 tra persona e studente e poi inserirò quella persona
        //nella tabella studente
        String query="INSERT INTO persone(nome,cognome,data_nascita) VALUES(?,?,?)";
        //per eseguire una query devo avere una connessione e richiamare
        //il metodo executUpdate 
        Long id = null;
        if(e != null && e instanceof Studente){
            //databaseMySql.executeDML(query,e.getNome(),e.getCognome(),e.getDataNascita());
            id = database.executeDML(query, 
            ((Studente)e).getNome(),
            ((Studente)e).getCognome(),
            String.valueOf(((Studente)e).getData_nascita()));
              //ora inserisco il record nella tabella studenti
            String queryStud="INSERT INTO studenti(id,id_classe) VALUES(?,?)";
            database.executeDML(queryStud, String.valueOf(id),
            String.valueOf(((Studente)e).getClasse().getId()));
        }
        return id;
    }

    @Override
    public Map<Long, Studente> read() {
        String query="select p.*,s.id_classe from persone p join studenti s on p.id = s.id";
        Map<Long,Map<String,String>> studenti = database.executeDQL(query);
        //ora devo trasformare ogni mappa in un oggetto di tipo studente
        //per fare questo devo creare un oggetto di tipo studente
        Studente s=null;
        Map<Long,Studente> studentiMap = new HashMap<>();
        for(Map<String,String> map :studenti.values()){
            s=context.getBean(Studente.class,map);
            studentiMap.put(s.getId(), s);
        }
        return studentiMap;
    }

    @Override
    public void update(Studente e) {
        String query="UPDATE persone SET nome=?,cognome=?,data_nascita=? WHERE id=?";
        database.executeDML(query, e.getNome(),e.getCognome(),
        String.valueOf(e.getData_nascita()),
        String.valueOf(e.getId()));
        //aggiorno anche la tabella studenti
        String queryStud="UPDATE studenti SET id_classe=? WHERE id=?";
        database.executeDML(queryStud, String.valueOf(e.getClasse().getId()),
        String.valueOf(e.getId()));
    }


    @Override
    public void delete(Long id) {
        //grazie all'integrità referenziale settata a cascade
        //posso eliminare un record dalla tabella persone e automaticamente
        //verrà eliminato anche dalla tabella studenti
        String query="DELETE FROM persone WHERE id=?";
        database.executeDML(query, String.valueOf(id));
    }

    @Override
    public Studente readById(Long id) {
        String query="SELECT p.*, s.id_classe from persone p join studenti s on p.id ="+
        "s.id where s.id = ?";
        Map<Long,Map<String,String>> studenti = database.executeDQL(query, String.valueOf(id));
        Studente s=null;
        for(Map<String,String> map :studenti.values()){
            s=context.getBean(Studente.class,map);
        }
       return s;
    }

    public Map<Long, Entity> readByName(String nome){
        String query = "select p.*, s.id_classe from persone p join studenti s on p.id = s.id where p.nome like(concat(concat('%', ?), '%'))";
        Map<Long, Map<String, String>> result = database.executeDQL(query, nome);
        Map<Long, Entity> ris = new HashMap<>();

        for(Map<String, String> params : result.values()){
            Studente s = context.getBean(Studente.class, params);
            ris.put(Long.parseLong(params.get("id")), s);
        }

        return ris;

    }
    
}