package com.generation.ultimalezione.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.ultimalezione.models.Docente;
import com.generation.ultimalezione.services.ServiceDocente;

import lombok.Data;

@RestController
@Data
@RequestMapping("api/docente")
public class DocenteRestController {

    private final ServiceDocente docenteService;

    // http://localhost:8080/api/docente/all
    @GetMapping("/all")
    public List<Docente> getAll(@RequestHeader("token") String token) {

        String ruolo = token.split("-")[0];
        Long idPersona = Long.parseLong(token.split("-")[1]);

        if (ruolo.equalsIgnoreCase("NONE") || idPersona == -1) {
            return null;
        } else if(ruolo.equals("dirigente") || ruolo.equals("docente")){
            // VIA LIBERA
            return docenteService.findAll();
        }
        return null;
    }

    
}
