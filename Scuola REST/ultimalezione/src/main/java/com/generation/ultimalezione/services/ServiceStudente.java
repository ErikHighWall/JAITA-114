package com.generation.ultimalezione.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.ultimalezione.dao.DaoStudente;
import com.generation.ultimalezione.dao.DaoUser;
import com.generation.ultimalezione.models.Entity;
import com.generation.ultimalezione.models.Studente;

//Questo service va a specializzare il GenericService definendo i tipi necessari, (TipoID, E e D che in questo caso
//diventano rispettivamente Integer, Studente, StudenteDAO). in questo service inoltre andiamo a definire metodi più specifici
//che non possono essere generalizzati per vari motivi
@Service
public class ServiceStudente extends GenericService<Long, Studente, DaoStudente>{

    @Autowired
    private DaoUser daoUser;

    //Questo metodo lo utilizziamo per dare una definizione di come istanziare l'Entity che andiamo ad utilizzare nei metodi generici
    //come il metodo update(). Sulla classe padre constructuEntity() e' astratto pertanto sono obbligato a fornire un'implementazione
    //in questao service specifico dato che estendo la classe astratta GenericService
    @Override
    public Studente createEntity(Map<String, String> map) {
        //servicestudente eredita da genericservice il get del contenxt
        //essendo context proprietà di genericservice
       Studente s = getContext().getBean(Studente.class,map);
       return s;
    }

    public Studente insert(Map<String,String> map){
        Studente s = createEntity(map);
        Long id = getDao().create(s);
        s.setId(id);
        boolean userOk = daoUser.createUser(s.getUsername(), s.getPassword(), s.getId());
        
        //Controllo se uno dei due ritorni è in errore, e se lo è allora ritorno null
        if(id < 1 || !userOk){
            return null;
        }
        return s;
    }

    public List<Studente> findAll(){
       // List<Studente> studenti = super.findAll();
            // for(Entity e : result.values()){
        //     lista.add((Studente)e);
        // }
        
        // return lista;

        // getDao().read().values().forEach(
        //     x -> {
        //         lista.add((Studente)x);
        //     }
        // );
        // return lista;
        return getDao().read() ///ottengo una collection di entity: Map<Long, Studente>
        .values()//di quella collezione prendo solo i valori e non le chiavi: Collection<Studente>  
        .stream()//trasformo la collection in uno stream, cioè ottengo un flusso di elementi
        //su questo posso il foreach 
        .map(//ciclo ogni elemento dello stream e associo ad ogni elemento x che è una Entity
            x ->{
                return (Studente)x;//il cast Studene, cioè casto ogni elemento x, Entity, in Studente
            }
        )
        .toList();//raccolgo quello che ho ho ottenuto in una lista di studenti
    }

     public List<Studente> findByName(String nome){
        Map<Long, Entity> result = getDao().readByName(nome);
        List<Studente> lista = new ArrayList<>();
        for(Entity e : result.values()){
            if(e instanceof Studente){
                lista.add((Studente)e);
            }
        }

        return lista;
    }
    
}
