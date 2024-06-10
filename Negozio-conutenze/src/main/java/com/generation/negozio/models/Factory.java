package com.generation.negozio.models;

import com.generation.negozio.models.*;

import java.util.Map;

public interface Factory {
    static Entity make(Map<String,String> mappa){
        Entity e = null;
        if(mappa.containsKey("elemento")){
            //se esiste la chiave elemento devo creare una bevanda
            //valore associato alla chiave "elemento"
            String valore = mappa.get("elemento");
            //creo l'istanza
            e = make(valore);
        }//chiude lo switch
        mappa.remove("elemento");
        if(e != null)
            e.fromMap(mappa);//assegna i valori alle proprietà prendendoli dalla mappa
        return e;
    }

    static Entity make(String tipoConcreto){
        Entity entity = null;
        switch(tipoConcreto.toLowerCase()){
            case "alimentare":
                entity = new Alimentare();
                break;
            case "prodotto":
                entity = new Prodotto();
                break;
        }
        return entity;
    }
}
