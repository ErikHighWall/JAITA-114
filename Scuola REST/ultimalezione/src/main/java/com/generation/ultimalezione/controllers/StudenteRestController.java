package com.generation.ultimalezione.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generation.ultimalezione.models.Studente;
import com.generation.ultimalezione.services.ServiceStudente;

import lombok.Data;

/*i restController */
@RestController
@Data
@RequestMapping("/api/studente")
public class StudenteRestController{

    private final ServiceStudente studenteService;

     // http://localhost:8080/api/studente/all
     @GetMapping("/all")
     public ResponseEntity<List<Studente>> getAll(@RequestHeader("token") String token){
 
 
         String ruolo = token.split("-")[0];
         Long idPersona = Long.parseLong(token.split("-")[1]);
 
 
         if(ruolo.equalsIgnoreCase("NONE") || idPersona == -1){
             return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
         }
         else{
             //VIA LIBERA
             return ResponseEntity.status(HttpStatus.OK)
             .body(studenteService.findAll());
             
         }
    }
       
    //cerco lo studente per id
    //http://localhost:8080/api/studente/byId?idStudente=[VALORE]
    @RequestMapping(path="/byId", method = RequestMethod.GET)
    public Studente getById(
    @RequestParam(name="idStudente", defaultValue = "0")
    Long id,
    @RequestHeader("token")String token){
        if(id == 0){
            return null;
        }
        if((token.split("-")[0].equals("studente") && 
        Long.parseLong(token.split("-")[1])==id) ||
        token.split("-")[0].equals("dirigente")||
        token.split("-")[0].equals("docente")
        ){
            return studenteService.findById(id);
        }else{
            return null;
        } 
    }


    //inserisco uno studente
    //@RequestMapping(path="/insert", method = RequestMethod.POST)
    @PostMapping("/insert")
    public ResponseEntity<Studente> insert(@RequestBody Map<String,String> map,
    @RequestHeader("token") String token){
        String ruolo = token.split("-")[0];
        Long idPersona = Long.parseLong(token.split("-")[1]);
        
        if(ruolo.equalsIgnoreCase("NONE") || idPersona == -1){
            return null;
        }
        else if(token.split("-")[0].equals("dirigente")){
        return ResponseEntity.status(HttpStatus.OK).body(studenteService.insert(map));   
        } 
        return null;
    }

    @GetMapping("/delete")
    public boolean delete(@RequestParam(name = "idStudente", defaultValue = "0") Long id, @RequestHeader("token") String token){
        String ruolo = token.split("-")[0];
        Long idPersona = Long.parseLong(token.split("-")[1]);

        if(id == 0){
            return false;
        }

        if(ruolo.equalsIgnoreCase("NONE") || idPersona == -1){
            return false;
        }
        else if(token.split("-")[0].equals("dirigente")){
            //Eventuali controlli in più sul token
            //VIA LIBERA
            return studenteService.delete(id);
        }
        return false;
    }

    @PostMapping("/update")
    public boolean update(@RequestBody Map<String, String> params, @RequestHeader("token") String token){
        String ruolo = token.split("-")[0];
        Long idPersona = Long.parseLong(token.split("-")[1]);
        if(ruolo.equalsIgnoreCase("NONE") || idPersona == -1){
            return false;
        }
        else if(token.split("-")[0].equals("dirigente")){
            //VIA LIBERA
            // return personaService.updateStudente(params);
            return studenteService.update(params);
        }
        return false;
    }


     @GetMapping("/searchByName")
    public List<Studente> searchByName(@RequestParam(name = "nome", defaultValue = "") String nome, @RequestHeader("token") String token){
        String ruolo = token.split("-")[0];
        Long idPersona = Long.parseLong(token.split("-")[1]);
        if(ruolo.equalsIgnoreCase("NONE") || idPersona == -1){
            return new ArrayList<>();
        }
        else if(token.split("-")[0].equals("dirigente") ||
                token.split("-")[0].equals("docente")||
                (token.split("-")[0].equals("studente")&&
                studenteService.findById(idPersona).getNome().equals(nome))){
            //VIA LIBERA
            return studenteService.findByName(nome);
        }
        return null;
    }
}





