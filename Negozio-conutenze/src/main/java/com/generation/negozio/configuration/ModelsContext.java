package com.generation.negozio.configuration;

import java.sql.Date;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import com.generation.negozio.models.Alimentare;
import com.generation.negozio.models.Prodotto;
import com.generation.negozio.models.Utente;

//contenitore dei bean che ho inserito nel package models
//per poterni iniettare in altir bean
//tramiote l'annotazione @Configuration, questa classe sarà un oggetto che mi permetterà di definire
//le istanze o l'istanza necessarie per il funzionamentro dell'applicazione

@Configuration
public class ModelsContext {
    //1. la classe ModelsContext è un contenitore di bean(modelli)
    //2 anche ModelsContext è un bean

    //Alimentare
   //ogni volta che chiedo di avere un bean di questo tipo ,lo clona cosi da ottenere + istanze
     //se ho + bean dello stesso tipo, spring restituirà quello con l'annotazione @Primary
   /*  public Alimentare newAlimentare(Map<String,String> map) {

        
       Long id=Long.parseLong(map.get("id"));
        
       

        String nome = map.get("nome");
        double prezzo = Double.parseDouble(map.get("prezzo"));
        Date scadenza=Date.valueOf(map.get("data_scadenza"));
    
        
        return new Alimentare(id,nome,prezzo,scadenza);}*/
        @Bean
        @Scope("prototype") 
        @Primary
         public Alimentare newAlimentare(Map<String,String> mappa){
      
            Long id=0L;
            if (mappa.containsKey("id")) {
                id = Long.parseLong(mappa.get("id"));
                
            }
      
                String nome = mappa.get("nome");
             
                double prezzo = Double.parseDouble(mappa.get("prezzo"));
          
                Date scadenza = Date.valueOf(mappa.get("data_scadenza"));
           
         
                Alimentare a = new Alimentare();
                a.setId(id);
                a.setNome(nome);
                a.setPrezzo(prezzo);
                a.setScadenza(scadenza);
                return a;
            
        }
    
    @Bean
    @Scope("prototype")
    
    public Alimentare alimentare() {
        return new Alimentare();
    }//sipuò creare + bean dello stesso tipo,ma la firma deve essere diversa
    //Prodotto
    @Bean
    @Scope("prototype")
    @Primary
    public Prodotto newProdotto(Map<String,String> map) {
        Long id=0L;
        if (map.containsKey("id")) {
            id = Long.parseLong(map.get("id"));
            
        }
        String nome = map.get("nome");
        double prezzo = Double.parseDouble(map.get("prezzo"));
        return new Prodotto(id,nome,prezzo);
    }

    @Bean
    @Scope("prototype")
    public Prodotto prodotto() {
        return new Prodotto();
    }

    @Bean
    @Scope("prototype")
    public Utente utente(){
        return new Utente();
    }
    @Bean
    @Scope("prototype")
    @Primary
    public Utente newUtente(Map<String,String> map) {
        Utente u=new Utente();
        Long id=0L;
        if (map.containsKey("id")) {
            id = Long.parseLong(map.get("id"));
            
        }
        u.setNome(map.get("nome"));
        u.setCognome(map.get("cognome"));
        u.setUsername(map.get("username"));
        u.setPassword(map.get("password"));
        u.setEmail(map.get("email"));
        u.setRuolo(map.get("ruolo"));
        return u;
       
    }
}
