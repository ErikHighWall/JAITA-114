package database;

import Interfaces.IDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database implements IDatabase {

    private String percorso, user, password;
    private Connection connection;
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Database istance;

    public Database() {
        setPercorso("music");
        this.user = "root";
        this.password = "root";
    }
    public static IDatabase getIstance(){
        if(istance==null){
            istance= new Database();
        }
        return istance;
    }

    public String getPercorso() {
        return percorso;
    }

    public void setPercorso(String nomeDB) {

        String url = "jdbc:mysql://localhost:3306/";
        String timezone = "?useSSL=false&serverTimezone=UTC?useSSL=false&serverTimezone=UTC";
        this.percorso = url + nomeDB + timezone;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String username) {
        this.user = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void apriConnessione() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(percorso, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("controlla il connettore e il percorso del driver");//stampa in rosso
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("controlla il percorso,lo user,la password e il nome del db");

        }
    }

    public void chiudiConessione() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Controlla la connessione perchè non è possibile chiuderla ");
        }
    }
    public void chiudiComunicazioni(PreparedStatement ps, ResultSet rs){
        try{
            if(ps!=null)
                ps.close();
            if(rs!=null)
                rs.close();
        }catch(Exception e){
            e.printStackTrace();
            System.err.println("ho provato a chiudere ps e rs");
        }
    }
    /*
    try{
     ps = connection.prepareStatement(query);
     //a questo punto se il varargs contiene dei valori allora
    //dovrò sostituire i valori in esso inseriti al posto dei segnaposti(?)
    //della query
    for(int i = 0; i < params.length; i++){
        //prendo alla prima iterazione il primo valore dell'array params
        //e lo setto al posto del primo ?
        //1: select * from bevande where nome = ? and prezzo = ? -> 2
        //1. params[] = {fanta,2.5}
        ps.setString(i + 1,params[i]);
        //dopo questa istruzione la query di esempio sarà:
        //1: select * from bevande where nome = fanta and prezzo = ?
        //alla seconda iterazione andiamo a settare il 2 ? con il secondo valore
        //salvato nell'array
        //select * from bevande where nome = fanta and prezzo = 2.5
    }
    ps.executeQuery();

}catch (Exception e){
     */

    /***
     *
     * @param query
     * @param params
     * @return listaDi Mappe
     */
    @Override
    public List<Map<String, String>> executeQuery(String query, String... params) {
        List<Map<String, String>> ris= new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String, String> rigaTabella;
        apriConnessione();
        try{
         ps = connection.prepareStatement(query);
         for(int i=0;i<params.length;i++){
             // prendo alla prima iterazione il primo valore dell'array params
             //e lo setto al posto del primo ?
             ps.setString(i+1,params[i]);

         }
         //se il db ci risponde con un dato tabellare lo salviamo in rs
         rs= ps.executeQuery();
         while(rs.next()){
             //se ci sono righe nella tabella,salviamo ogni riga in una mappa
             //la mappa sarà costituita da chiave-valore
             //dove la chiave sarà il nome della colonna e il valore quello associato
             //a quel determinato campo
             //id,nome,prezzo
             //9,fanta,2.5
             rigaTabella = new HashMap<>();
           //  getMetaData().getColumnCount() ci ritorna il numero di colonne del numero di colonna del tb
             //parto dalla prima colonna e arrivo fino alla terza
             for(int i =1;i<=rs.getMetaData().getColumnCount();i++){
                 //nella mappa devo inserire il nome della prima colonna  come chiave,e come valore associato
                 //il numero di ide riportato dalla tabella

                 if(rigaTabella.containsKey(rs.getMetaData().getColumnName(i)))
                 {
                     String name2=rs.getMetaData().getColumnName(i)+" ";
                     rigaTabella.put(name2,rs.getString(i));
                 }
                 else
                 rigaTabella.put(rs.getMetaData().getColumnName(i),rs.getString(i));

             }
             ris.add(rigaTabella);

         }
        }catch (Exception e){
e.printStackTrace();
        }finally {
            chiudiComunicazioni(ps,rs);
        }
 chiudiConessione();
        return ris;
    }

    @Override

    public void executeUpdate(String query, String... params) {
        apriConnessione();
// insert into bevande (nome,prezzo) values (?,?);
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(query);
            for(int i=0;i<params.length;i++){
                ps.setString(i+1,params[i]);
            }
            //ps.executeUpdate() restituisce il numero di righe manipolate dal comndo inviato
            //cioè se ho fatto 3 inserti in un solo comando,mi restituirà un injt con valore 3
            //se però il comando non è stato eseguito risulterà 0 perchè non ha influenzato nessuna riga

           int righeEseguite= ps.executeUpdate();
            if(righeEseguite==0){
                System.out.println("la query"+ query+ "non è stato eseguito");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("errore nell'esecuzione della query"+ query);
        }finally {
            chiudiComunicazioni(ps,null);
            chiudiConessione();
        }

    }
}
