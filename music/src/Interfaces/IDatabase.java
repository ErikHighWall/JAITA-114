package Interfaces;

import java.util.List;
import java.util.Map;

public interface IDatabase {
    // metodo che esegue le query(DQL) cioè le select
    List<Map<String,String>> executeQuery(String query,String...params);
    void executeUpdate(String query,String...params);


}
