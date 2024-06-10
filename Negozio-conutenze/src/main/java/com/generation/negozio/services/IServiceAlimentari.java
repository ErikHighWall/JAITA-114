package com.generation.negozio.services; // Update the package declaration
import java.util.List; // Add this import statement
import java.util.Map;

import com.generation.negozio.models.Alimentare; // Add this import statement
public interface IServiceAlimentari {
    
      
    List <Alimentare> findAll();
    Alimentare findById(Long id);
    void add(Map<String, String> params);
    void modificaAlimentare(Map<String, String> params);
    void eliminaAlimentare(Long id);
     
}
