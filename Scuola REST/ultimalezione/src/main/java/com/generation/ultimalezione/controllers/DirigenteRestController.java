package com.generation.ultimalezione.controllers;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generation.ultimalezione.models.Dirigente;
import com.generation.ultimalezione.services.ServiceDirigente;

import lombok.Data;
@RestController
@Data
@RequestMapping("api/dirigente")
public class DirigenteRestController {

    private final ServiceDirigente dirigenteService;

    @GetMapping("/byId")
    public Dirigente getById(@RequestParam(name = "idDirigente", defaultValue = "0") Long id, 
    @RequestHeader("token") String token){
        String ruolo = token.split("-")[0];
        Long idPersona = Long.parseLong(token.split("-")[1]);


        if(ruolo.equalsIgnoreCase("NONE") || idPersona == -1){
            return null;
        }
        else if((ruolo.equals("dirigente") && idPersona == id)||
                ruolo.equals("docente")){
            //Eventuali controlli in più sul token
            //VIA LIBERA
            return dirigenteService.findById(idPersona);
        }
        return null;
    }
    
}
