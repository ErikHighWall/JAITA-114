package com.generation.ultimalezione.dao;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.generation.ultimalezione.models.Dirigente;
import com.generation.ultimalezione.models.Docente;
import com.generation.ultimalezione.models.Persona;
import com.generation.ultimalezione.models.Studente;

import lombok.Data;

@Service
@Data
public class DaoUser{

    private final Database database;

    private final DaoStudente daoStudente;
    private final DaoClasse daoClasse;
    private final DaoDocente daoDocente;
    private final DaoDirigente daoDirigente;

    private final ApplicationContext context;


    //inserisco uno user
    public boolean createUser(String username, String password, Long idPersona){
        String query="update persone set username=?, password=? where id=?";
        Long check=database.executeDML(query, username, password, String.valueOf(idPersona));
        //se il valore di check è -2 allora l'update non è andato a buon fine
        if(check==-2){
            return false;
        }
        return true;
    }

    //controllo se esiste uno user
    //cioè controllo se esiste una persone con lo stesso username e password
    public Persona readUsernameAndPassword(String username,String password){
        String query="select * from persone where username=? and password=?";
        //la mappa conterrà una sola riga
        //Long sarà l'id della persona con quello username e password
        //Map<String,String> sarà la mappa con i valori della persona
        Map<Long,Map<String,String>> result = database.executeDQL(query,username,password);
        Long idPersona = 0L;
        for(Long key:result.keySet()){
            idPersona=key;
        }
        Persona persona=null;
        if(idPersona >0){
            Studente s = daoStudente.readById(idPersona);
            Docente d = daoDocente.readById(idPersona);
            Dirigente dir = daoDirigente.readById(idPersona);
            if(s!=null){
                persona=s;
            }
            else if(d!=null){
                persona=d;
            }else if(dir!=null){
                persona=dir;
            }
        }
        return persona;
    }

    //controllo se esiste uno user dall'id
    public Persona readById(Long id){
        String query="select * from persone where id=?";
        //la mappa conterrà una sola riga
        //Long sarà l'id della persona con quello username e password
        //Map<String,String> sarà la mappa con i valori della persona
        Map<Long,Map<String,String>> result = database.executeDQL(query,String.valueOf(id));
        Long idPersona = 0L;
        for(Long key:result.keySet()){
            idPersona=key;
        }
        Persona persona=null;
        if(idPersona >0){
            Studente s = daoStudente.readById(idPersona);
            Docente d = daoDocente.readById(idPersona);
            Dirigente dir = daoDirigente.readById(idPersona);
            if(s!=null){
                persona=s;
            }
            else if(d!=null){
                persona=d;
            }else if(dir!=null){
                persona=dir;
            }
        }
        return persona;
    }

}



