package com.generation.negozio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.generation.negozio.dao.DaoUtente;
import com.generation.negozio.models.Entity;
import com.generation.negozio.models.Utente;

public class ServiceUtente {
    @Autowired
    private DaoUtente daoUtente;
    @Autowired
    private ApplicationContext applicationContext;

    public void add(Map<String, String> params) {
        Utente u =applicationContext.getBean(Utente.class,params);
        daoUtente.add(u);
   

    }
    public List<Utente> findAll() {
        List<Entity> ris = daoUtente.readAll();
        List<Utente>  u = new ArrayList<>();
  for(Entity e : ris) {
      if(e instanceof Utente)
          u.add((Utente)e);
    }
    return u;
    
}
public Utente findById(Long id) {
    Entity e = daoUtente.cercaPerId(id);
    return (Utente)e;
}

public void update(Map<String, String> params) {
    Utente u = applicationContext.getBean(Utente.class,params);
    daoUtente.update(u);
}
public void delete(Long id) {
    daoUtente.delete(id);
}

public boolean findByUsername(String username) {
    return daoUtente.userExists(username);
}
public Utente findByUsernameAndPassword(String username, String password) {
    Map<String,String> u= daoUtente.autentica(username, password);
    if(u!=null) {
        return applicationContext.getBean(Utente.class,u);
    }
    return null;
}
}
