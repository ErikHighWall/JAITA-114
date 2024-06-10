package com.generation.negozio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.generation.negozio.dao.IDao;
import com.generation.negozio.models.Alimentare;
import com.generation.negozio.models.Entity;

public class ServiceAlimentari implements IServiceAlimentari{
        @Autowired
        private IDao daoAlimentare;
@Autowired 
        private ApplicationContext context;// DIPOSIZIONE TUTTO CONTENITORE DEI BEAN


        @Override
        public List<Alimentare> findAll() {
            List<Entity> ris= daoAlimentare.readAll();
            List<Alimentare> alimentari = new ArrayList<>();

            for(Entity e : ris) {
                if(e instanceof Alimentare)
                    alimentari.add((Alimentare)e);
            }
        return alimentari;
        }

        @Override
        public Alimentare findById(Long id) { // Change the parameter type from int to Long
                Entity e =daoAlimentare.cercaPerId(id);
                return (Alimentare)e; // Replace null with the appropriate return statement
        }

        @Override
        public void add(Map<String, String> params) {
               
      Alimentare a=  context.getBean(Alimentare.class,params);//creo alimento tramite i Bean
      daoAlimentare.add(a);// passo l'oggetto creato al dao specifico per aggiungerlo al database
             
        }

        @Override
        public void modificaAlimentare(Map<String, String> params) {
                Alimentare a= context.getBean(Alimentare.class,params);
                daoAlimentare.update(a);
            
        }

        @Override
        public void eliminaAlimentare(Long id) {
              
               daoAlimentare.delete(id);
        }


}
