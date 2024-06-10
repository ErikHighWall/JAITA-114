package com.generation.negozio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.generation.negozio.dao.DaoProdotto;
import com.generation.negozio.dao.IDao;
import com.generation.negozio.models.Alimentare;
import com.generation.negozio.models.Entity;
import com.generation.negozio.models.Prodotto;

public class ServiceProdotti implements IServiceProdotti{

    @Autowired
    private IDao daoProdotto;
    @Autowired
    private ApplicationContext context;

    @Override
    public List<Prodotto> findAll() {
        List<Entity> ris= daoProdotto.readAll();
        List<Prodotto> prodotti = new ArrayList<>();
          for(Entity e : ris) {
                if(e instanceof Prodotto)
                    prodotti.add((Prodotto)e);
            }

        return prodotti;
        
    }

    @Override
    public void add(Map<String, String> params) {
       
        Prodotto p=context.getBean(Prodotto.class, params);
        daoProdotto.add(p);

    }

    @Override
    public void modificaProdotto(Map<String, String> params) {
        Prodotto p=context.getBean(Prodotto.class, params);
        daoProdotto.update(p);
        
    }

    @Override
    public void eliminaProdotto(Long idProdotto) {
        daoProdotto.delete(idProdotto);
    }


    
}
