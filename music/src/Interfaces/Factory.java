package Interfaces;

import models.*;

import java.util.Map;

public interface Factory {

    static Entity make(String tipoOggetto ){
        Entity e= null;
        switch (tipoOggetto.toLowerCase()){
            case "album":
                e= new Album();
                break;
            case "artist":
                e= new Artist();
                break;
            case "record_label":
                e= new RecordLabel();
                break;
            case "song":
                e= new Song();
                break;


        }
        return e;

    }
    static Entity make(Map<String, String> mappa) {
        Entity e = null;
        if (mappa.containsKey("elemento")) {

            e = make(mappa.get("elemento"));


            mappa.remove("elemento");
            if (e != null)
                e.fromMap(mappa);
        }
        return e;


    }



}
