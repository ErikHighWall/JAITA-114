package com.generation.ultimalezione.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.generation.ultimalezione.dao.DaoClasse;
import com.generation.ultimalezione.models.Classe;

@Service
public class ServiceClasse extends GenericService<Long, Classe, DaoClasse>{

    @Override
    public Classe createEntity(Map<String, String> map) {
        Classe c = getContext().getBean(Classe.class, map);
        return c;
    }
    
}
