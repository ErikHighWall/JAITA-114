package com.generation.ultimalezione.controllers;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.ultimalezione.dao.DaoUser;
import com.generation.ultimalezione.dto.LoginStatus;
import com.generation.ultimalezione.models.Dirigente;
import com.generation.ultimalezione.models.Docente;
import com.generation.ultimalezione.models.Persona;
import com.generation.ultimalezione.models.Studente;

import lombok.Data;


@Data
@RequestMapping("api/login")
@RestController
public class LoginRestController {

    private final DaoUser daoUser;
    private final ApplicationContext context;

     /*
         * Per gestiere il login, questa richiesta POST recupera i dati dal corpo della richiesta sottoforma di Map<String, String>
         * e chiede allo UserDAO di cercare una Persona che abbia queste credenziali.
         * In base al tipo concreto dell'oggetto Persona ritornato genero un token con un ruolo e l'id di chi ha fatto l'accesspo
         * Normalmente i token vengono poi cifrati e mandati in frontend, in questo caso bypassiamo la fase di cifratura e lo mandiamo
         * in chiaro.
         * Il token servirà per tutte le richieste che chidono un'autorizzazione (ad esempio l'endpoint che mi da la lista di tutti gli studenti lo può chiamare solo chi ha fatto login da Dirigente)
     */

     @PostMapping("/signin")
    public LoginStatus signin(@RequestBody Map<String, String> params){
        Persona p = daoUser.readUsernameAndPassword(params.get("username"),
        params.get("password"));
        LoginStatus ls;
        // ls.setToken("NONE", -1);
        ls = null;

        if(p instanceof Studente){
            // ls.setToken("STUDENTE", p.getId());
            ls = context.getBean(LoginStatus.class, "studente", p.getId());
            return ls; 
        }
        else if(p instanceof Docente){
            // ls.setToken("DOCENTE", p.getId());
            ls = context.getBean(LoginStatus.class, "docente", p.getId());
            return ls;
        }
        else if(p instanceof Dirigente){
            // ls.setToken("DIRIGENTE", p.getId());
            ls = context.getBean(LoginStatus.class, "dirigente", p.getId());
            return ls;
        }

        return ls;
    }

    //questo endpoint verrà utilizzato per controllare la validita' del token che arriva da frontend.
    //Il token viene passato alla richiesta come intestazione (header), pertanto chiediamo a spring di recuperarlo
    //con l'utilizzo di @RequestHeader.
    //Con l'header 'role' faccio un controllo in base a quale pagina voglio visualzzare, cioè se sto accedendo all'areadirigenti
    //controllo che il ruolo nel token sia DIRIGENTE e nell'header 'role' verrà inserito dal frontend che si sta cercando il ruolo DIRIGENTE
    @GetMapping("/checklogin")
    public boolean checkLogin(@RequestHeader("token") String token, @RequestHeader("role") String ruoloRichiesto){
        String ruolo = token.split("-")[0];
        Long idPersona = Long.parseLong(token.split("-")[1]);

        if(ruolo.equalsIgnoreCase("NONE") || idPersona == 0){
            return false;
        }
        else{
            if(ruolo.equals(ruoloRichiesto)){
                //potenzialmente: Controllo ulteriore se l'id che c'è nel token 
                //è coerente con una persona che ha il ruolo che c'è nel token
                Persona p = daoUser.readById(idPersona);
                if(p instanceof Studente && ruolo.equals("studente")){
                    return true;
                }
                else if(p instanceof Docente && ruolo.equals("docente")){
                    return true;
                }
                else if(p instanceof Dirigente && ruolo.equals("dirigente")){
                    return true;
                }
                return true;
            }
        }

        return false;
    }

    //endpoint per aggiungere uno user
    @PostMapping("/signup")
    public Persona signup(@RequestBody Map<String, String> params){
        boolean check=daoUser.createUser(params.get("username"),
         params.get("password"), Long.parseLong(params.get("idPersona")));
        if(check){
            return daoUser.readById(Long.parseLong(params.get("idPersona")));
        }
        return null;
    }

    
}
