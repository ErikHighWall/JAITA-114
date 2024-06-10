package com.generation.ultimalezione.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.ultimalezione.models.Classe;
import com.generation.ultimalezione.services.ServiceClasse;

import lombok.Data;

@RestController
@Data
@RequestMapping("api/classe")
public class ClasseRestController {

   private final ServiceClasse classeService;

    @GetMapping("/all")
    public List<Classe> getAll(@RequestHeader("token") String token){
        String ruolo = token.split("-")[0];
        Long idPersona = Long.parseLong(token.split("-")[1]);


        if(ruolo.equalsIgnoreCase("NONE") || idPersona == -1){
            return null;
        }
        else{
            //VIA LIBERA
            return classeService.findAll();
        }
    }

    
}
