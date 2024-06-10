package dao;

import database.Database;
import interfaces.IDao;
import interfaces.IDatabase;
import models.Bevanda;
import models.Entity;
import models.Ingrediente;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// LETTURA E MANIPOLAZIONE DEI DATI DELLE SOLE BEVANDE
// CRUD DELLE BEVANDE
public class DaoBevande implements IDao {

    //proprietà:
    private IDatabase database;
    private String queryInsert = "insert into bevanda (nome,prezzo) values (?,?);";
    private String queryUpdate = "update bevanda set = nome?,prezzo=? where id=?";
    private String queryDelete = "delete from bevanda where id= ?";
    private String queryRead = "select* from bevanda";
    private String queryReadOne = "select*from bevanda where id = ?";
    private String queryIdIngrediente = "Select idBevanda,idIngrediente from contiene " +
            "where idIngrediente = ?";
    private String queryAssociativa = "insert into contiene (idBevanda,idIngrediente,quantitaIngrediente) values(?,?,?)";
    private String queryDissociativa = "delete from contiene where idBevanda=? and idIngrediente =?";

    private String queryDissociaBevanda = " delete from contiene where idBevanda=?";
    private String queryDissociaIngrediente = " delete from contiene where idIngrediente=?";

    public DaoBevande(String nomeDB) {
        database = new Database(nomeDB);
    }

    @Override
    public void add(Entity e) {
        //DML -> executeUpdate(query,parametri) -> insert
        //questo metodo prende in input la query con i segnaposto
        //e i valori da settare dei ?
        //una volta fatto lo scambio esegue il comando di insert
        if (e instanceof Bevanda) // controllo che l'oggetto sia effettivamente una Bevanda
            database.executeUpdate(queryInsert, ((Bevanda) e).getNome(), ((Bevanda) e).getPrezzo() + "");

    }

    @Override
    public List<Map<String, String>> read(String query, String... params) {


        return database.executeQuery(query);


    }

    @Override
    public List<Map<String, String>> read() {

        //DQL
        List<Map<String, String>> ris = new ArrayList<>();

        return ris = database.executeQuery(queryRead);
    }




    @Override
    public void update(Entity e) {
        //DML -> executeUpdate(query,parameetri)
        if (e instanceof Bevanda) {
            Bevanda b = (Bevanda) e;
            database.executeUpdate(queryUpdate, b.getNome(),
                    String.valueOf(b.getPrezzo()), String.valueOf(e.getId()));
        }
    }


    @Override
    public void update(int id, String colonna, String valoreNuovo) {
        String queryUpdateValore = "update [tabella] set" + colonna + "=? where id=?";


        queryUpdateValore = queryUpdateValore.replace("[tabella]", "bevanda");
        //sostituire quello che contiene  in tabella con il replaqcement

        database.executeUpdate(queryUpdateValore, valoreNuovo, id + "");

    }


    @Override
    public void delete(int id) {
        database.executeUpdate(queryDelete, String.valueOf(id));//trasforma int in String
    }

    @Override
    public Map<String,String> cercaPerId(int id) {
        Entity e = null;
        List<Map<String, String>> coppia = database.executeQuery(queryReadOne, String.valueOf(id));
        return   coppia.get(0);

    }
    //query per vedere gli ingredienti associati ad una bevaNDA
    //select* from contiene where idBevanda = ?
    //

    /***
     *
     * @param id della bevanda di cui voglio vedere gli ingredienti associati
     * @return List<Map < String, String>> lista di mappe contenenti i dati ricevuti dal db
     */
    public List<Map<String, String>> readIngredienti(int id) {
        String query = "select * from contiene where idBevanda = ?";
        List<Map<String, String>> lista = database.executeQuery(query, String.valueOf(id));
        return lista;

    }


    //idIngredienti
    /*"Select idBevanda,idIngrediente from contiene " +
            "where idIngrediente = ?"*/


//metodo che ci permette di aggiungere una associazione,cioè un recxord nella tabella contiene
    //un modo da associare una bevanda an un ingrediente
    //insert into contiene (idBevanda,idIngrediente,quantitaIngrediente) values(?,?,?);


    //DQL -> executeQuery lista di mappe

    public List<Map<String, String>> readBevandeFromIngrediente(int idIngrediente) {

        return database.executeQuery(queryIdIngrediente, String.valueOf(idIngrediente));

    }

    public List<Entity> readBevandeFromIngredientee(int idIngrediente) {

        List<Map<String, String>> listaMappa = database.executeQuery(queryIdIngrediente, String.valueOf(idIngrediente));

        List<Entity> ris = new ArrayList<>();
        for (Map<String, String> riga : listaMappa) {
            Entity e = new Bevanda(Integer.parseInt(riga.get("id")),
                    riga.get("nome"),
                    Double.parseDouble(riga.get("prezzo")), null);

            ris.add(e);
        }
        return ris;
    }

    public void associa(int idBevanda, int idIngrediente, double quantitaIngrediente) {
        //DML -> executeUpdate()
        database.executeUpdate(queryAssociativa, String.valueOf(idBevanda),
                String.valueOf(idIngrediente), String.valueOf(quantitaIngrediente));
    }

    //metodo che ci permette di dissociare una bevanda da un ingrediente

    //permette di eliminare un record dalla tabella contiene,sapendo l'id della bevanda e l'id dell'ingrediente

    public void dissocia(int idBevanda, int idIngrediente) {
        // "delete from contiene where idBevanda=? and idIngrediente =?
        database.executeUpdate(queryDissociativa, String.valueOf(idBevanda), String.valueOf(idIngrediente));
    }

    //metodo che permetta di eliminare tutti i record nella tabella contiene legati ad una bevanda
    public void dissociaBevanda(int idBevanda) {
        // "delete from contiene where idBevanda=? and idIngrediente =?

        database.executeUpdate(queryDissociaBevanda, String.valueOf(idBevanda));
    }

    //metodo che permetta di eliminare tutti i record nella tabella contiene legati ad un ingrediente
    public void dissociaIngrediente(int idIngrediente) {
        // "delete from contiene where idBevanda=? and idIngrediente =?

        database.executeUpdate(queryDissociaIngrediente, String.valueOf(idIngrediente));
    }

    public Entity cercaBevandaNome(String nome) {
        String query = "select*from bevanda where nome= ?";
        List<Map<String, String>> mappa = database.executeQuery(query, nome);
        Map<String, String> riga = mappa.get(0);

      /*  return new Bevanda(Integer.parseInt(riga.get("id")),
                riga.get("nome"),
                Double.parseDouble(riga.get("prezzo")), null);*/

        //utilizzando un metodo che crea oggetti vbevanda per velocizzarte le cose
        return creazioneOggettoBevanda(riga.get("id"),  riga.get("nome"),riga.get("prezzo"),null);


    }

    public Entity creazioneOggettoBevanda(String id, String nome, String prezzo, List<Ingrediente> lista) {
        Entity e = null;

        e = new Bevanda(Integer.parseInt(id),
                nome,
                Double.parseDouble(prezzo), null);
        return e;

    }

}

