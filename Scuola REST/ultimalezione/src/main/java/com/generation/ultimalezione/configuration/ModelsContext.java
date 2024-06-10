package com.generation.ultimalezione.configuration;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.generation.ultimalezione.dao.DaoClasse;
import com.generation.ultimalezione.dto.LoginStatus;
import com.generation.ultimalezione.models.Classe;
import com.generation.ultimalezione.models.Dirigente;
import com.generation.ultimalezione.models.Docente;
import com.generation.ultimalezione.models.Entity;
import com.generation.ultimalezione.models.Studente;

@Configuration
public class ModelsContext {

    @Autowired
    private DaoClasse daoClasse;

    @Bean
    @Scope("prototype")
    public Studente studente(Map<String,String> map){
        Studente s = new Studente();
        Long id = -1L;
        Long classe = 0L;
        if(map.containsKey("id")) {
            id = Long.parseLong(map.get("id"));
        }
        s.setId(id);
        s.setNome(map.get("nome"));
        s.setCognome(map.get("cognome"));
        s.setData_nascita(Date.valueOf(map.get("data_nascita")));
        s.setUsername(map.get("username"));
        s.setPassword(map.get("password"));
        if(map.containsKey("id_classe")) {
           classe = Long.parseLong(map.get("id_classe"));
        }
        //chiedo al gestore degli oggetti di tipo classe di restituirmi la 
        //classe come oggetto con quell'id -> DaoClasse
        s.setClasse(daoClasse.readById(classe));
        return s;
    }

    @Bean
    @Scope("prototype")
    public Classe classe(Map<String,String> map){
        Classe c = new Classe();
        Long id = -1L;
        if(map.containsKey("id")) {
            id = Long.parseLong(map.get("id"));
        }
        c.setId(id);
        c.setSezione(map.get("sezione"));
        return c;
    }

    @Bean
    @Scope("prototype")
    public Docente newDocente(Map<String, String> params){
        Long id = -1L;
        if(params.containsKey("id")){
            id = Long.parseLong(params.get("id"));}
        String nome = params.get("nome");
        String cognome = params.get("cognome");
        Date dataNascita = Date.valueOf(params.get("data_nascita"));
        String username = params.get("username");
        String password = params.get("password");
        List<Classe> listaClassi = new ArrayList<>();
        Map<Long, Entity> result = daoClasse.readByIdDocente(id);
            for(Entity e : result.values()){
                if(e instanceof Classe){
                    listaClassi.add((Classe)e);
            }
        }
        Docente d = new Docente();
        d.setId(id);
        d.setNome(nome);
        d.setCognome(cognome);
        d.setData_nascita(dataNascita);
        d.setUsername(username);
        d.setPassword(password);
        d.setClassi(listaClassi);
        return d;
    }

    @Bean
    @Scope("prototype")
    public Dirigente newDirigente(Map<String, String> params){
        Long id = -1L;
        if(params.containsKey("id")){
            id = Long.parseLong(params.get("id"));
        }
        String nome = params.get("nome");
        String cognome = params.get("cognome");
        Date dataNascita = Date.valueOf(params.get("data_nascita"));
        String username = params.get("username");
        String password = params.get("password");
        Dirigente d = new Dirigente();
        d.setId(id);
        d.setNome(nome);
        d.setCognome(cognome);
        d.setData_nascita(dataNascita);
        d.setUsername(username);
        d.setPassword(password);
        return d;
    }

    @Bean
    @Scope("prototype")
    public LoginStatus newLoginStatus(String ruolo, Long idPersona){
        LoginStatus ls = new LoginStatus();
        ls.setToken(ruolo, idPersona);
        return ls;
    }
        
    
}
