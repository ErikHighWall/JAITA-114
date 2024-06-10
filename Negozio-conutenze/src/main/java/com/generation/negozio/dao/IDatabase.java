package com.generation.negozio.dao;

import java.util.List;
import java.util.Map;

public interface IDatabase {
    
    int executeUpdate(String query,String...params);
    List<Map<String,String>> executeQuery(String query,String...params);
}
