package com.generation.ultimalezione.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.generation.ultimalezione.dao.DaoDocente;
import com.generation.ultimalezione.models.Docente;

@Service
public class ServiceDocente extends GenericService<Long, Docente, DaoDocente>{

    @Override
    public Docente createEntity(Map<String, String> map) {
        Docente d = getContext().getBean(Docente.class, map);
        return d;
    }
    
}
