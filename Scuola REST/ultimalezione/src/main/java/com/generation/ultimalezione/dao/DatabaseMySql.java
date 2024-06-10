package com.generation.ultimalezione.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;


/*tramite l'annotazione @Service indico a Spring che questa classe
 * è una classe di servizio e quindi deve essere gestita da Spring
 * cioè andrà direttmente spring a creare un bean di questa classe
 * senza che io debba farlo manualmente in un context
 * spring creerà un'istanza di questa classe e la metterà a disposizione
 * per essere iniettata in altre classi
 * Un bean di servizio è una classe che implementa la logica di business
 * Cioè la logica usata nella classi che hanno funzionalità 
 * legate al business dell'applicazione(Dao, repository)
 * Si occuperà si della creazione del bean e della sua gestione 
 * Di default il bean sarà singleton cioè sarà unico per tutta l'applicazione
 */
@Service
@ConditionalOnProperty(name="db.type",havingValue = "mysql")
public class DatabaseMySql implements Database{

    /*prendo i valori dal file di configurazione in questo modo
     * posso cambiare i valori senza dover ricompilare il codice
     * basta modificare i valori salvati nel di configurazione
     * in questo caso prendo i valori relativi al database mysql
     * con l'annotazione @Value("${db.mysql.username}") prendo 
     * il valore della variabile username dal file di configurazione
     * e lo inietto nella proprietà username
     * posso uaare questa annotazione per tutte le proprietà che voglio
     * e anche per i metodi
     */
    @Value("${db.mysql.username}")
    private String username;

    @Value("${db.mysql.password}")
    private String password;
    @Value("${db.mysql.path}")
    private String path;
    @Value("${db.mysql.timezone}")
    private String timezone;
    @Value("${db.mysql.schema}")
    private String nomeDb;

    private Connection connection;

    //inserisco il costruttore di default vuoto
    //perchè spring ha bisogno di un costruttore vuoto
    //per creare il bean
    //dopo aver creato l'istanza carica i valori delle proprietà
    //dal file di configurazione
    public DatabaseMySql() {}

    @PostConstruct
    public void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(path + nomeDb 
            + timezone, username, password);
        }
        catch(SQLException e){
            System.out.println("Errore connesione: " + e.getMessage());
            e.printStackTrace();
        }
        catch(Exception e){
            System.out.println("Driver non trovato");
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try{
            connection.close();
        }
        catch(SQLException e){
            System.out.println("Errore chiusura connessione");
        }
    }

    public Connection getConnection(){
        return connection;
    }


    @Override
    public Long executeDML(String query, String... params) {
        Long id = -1L;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String generatedColumns[] = {"id"};
            ps = connection.prepareStatement(query,generatedColumns);
            for(int i = 0; i < params.length; i++){
                ps.setString(i+1, params[i]);
            }
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                id = rs.getLong(1);
            }
            if(ps != null){
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
        }catch(Exception e){
            System.out.println("Errore esecuzione query: " + e.getMessage());
            e.printStackTrace();
            return -2L;
        }
        return id;
    }

    @Override
    public Map<Long, Map<String, String>> executeDQL(String query, String... params) {
       Map<Long, Map<String, String>> result = new HashMap<>();
       PreparedStatement ps = null;
       ResultSet rs = null;
         try{
              ps = connection.prepareStatement(query);
              for(int i = 0; i < params.length; i++){
                ps.setString(i+1, params[i]);
              }
              rs = ps.executeQuery();
              Map<String, String> mappaProprietà;
              int columnCount = rs.getMetaData().getColumnCount();
              while(rs.next()){
                mappaProprietà = new HashMap<>();
                for(int i = 1; i <= columnCount; i++){
                    mappaProprietà.put(rs.getMetaData().getColumnName(i),
                     rs.getString(i));
                }
                result.put(rs.getLong("id"), mappaProprietà);
              }
              if(ps != null){
                ps.close();
              }
              if(rs != null){
                rs.close();
              }
            }catch(Exception e){
                e.printStackTrace();
            }
            return result;
    }
    
}
