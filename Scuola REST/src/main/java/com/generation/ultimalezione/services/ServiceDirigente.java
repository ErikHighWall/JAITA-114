package com.generation.ultimalezione.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.generation.ultimalezione.dao.DaoDirigente;
import com.generation.ultimalezione.models.Dirigente;

@Service
public class ServiceDirigente extends GenericService<Long, Dirigente, DaoDirigente>{

    @Override
    public Dirigente createEntity(Map<String, String> map) {
        Dirigente d = getContext().getBean(Dirigente.class, map);
        return d;
    }


    
}
