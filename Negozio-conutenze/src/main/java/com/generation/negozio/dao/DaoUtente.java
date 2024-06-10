package com.generation.negozio.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.generation.negozio.models.Entity;
import com.generation.negozio.models.Utente;

public class DaoUtente implements IDao{

    @Autowired
    private IDatabase db;
    @Autowired
    private ApplicationContext applicationContext;
    

    @Override
    public void add(Entity e) {
       String query = "INSERT INTO utenti (nome, cognome, username, password, email, ruolo) VALUES (?, ?, ?, ?, ?, ?)";
         Utente u= applicationContext.getBean( Utente.class);
            if(e instanceof Utente){
            u=(Utente)e;
            db.executeUpdate(query, u.getNome(), u.getCognome(), u.getUsername(), u.getPassword(), u.getEmail(), u.getRuolo());
            }
           
    }

    @Override
    public List<Map<String, String>> read(String query, String... params) {
      return db.executeQuery(query, params);
    }

    @Override
    public List<Map<String, String>> read() {
String query ="SELECT * FROM utenti";
        return db.executeQuery(query);
        //return "SELECT * FROM utenti
    }

    @Override
    public List<Entity> readAll() {
       List<Entity> ris= new ArrayList<>();
       Entity e;
       for(Map<String,String> riga : read()) {
        e=applicationContext.getBean(Utente.class, riga);
           ris.add(e);
       }
    return ris;
    }

    @Override
    public void update(Entity e) {
        String query = "UPDATE utenti SET nome = ?, cognome = ?, username = ?, password = ?, email = ?, ruolo = ? WHERE id = ?";
     
       Utente u= applicationContext.getBean("utente", Utente.class);
       if(e instanceof Utente){
        u=(Utente)e;
       }
       db.executeUpdate(query, u.getNome(), u.getCognome(), u.getUsername(), u.getPassword(), u.getEmail(), u.getRuolo(), String.valueOf(u.getId()));
    }

    @Override
    public void update(int id, String proprieta, String valoreNuovo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(long id) {
       String query = "DELETE FROM utenti WHERE id = ?";
       db.executeUpdate(query, String.valueOf(id));
    }

    @Override
    public Entity cercaPerId(Long id) {
      String query="SELECT * FROM utenti WHERE id = ?";
        List<Map<String,String>> righe = db.executeQuery(query, String.valueOf(id));
        Map<String,String> riga = righe.get(0);
        return applicationContext.getBean(Utente.class, riga);
    }

    @Override
    public String printAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printAll'");
    }

//autentica(String username,String password)
public Map<String,String> autentica(String username,String password){
    String query="SELECT * FROM utenti WHERE username = ? AND password = ?";
    List<Map<String,String>> righe = db.executeQuery(query, username, password);
    if(righe.size()==0) {
        return null;
    }
    return righe.get(0);
    
}
//userExists(String username)
public boolean userExists(String username){
    String query="SELECT * FROM utenti WHERE username = ?";
    List<Map<String,String>> righe = db.executeQuery(query, username);
    return righe.size()>0;

   
}
}
