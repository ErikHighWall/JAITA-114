package com.generation.negozio.services;

import java.util.List;
import java.util.Map;

import com.generation.negozio.models.Prodotto;

public interface IServiceProdotti {

    List <Prodotto> findAll();
    void add(Map<String,String> params);
    void modificaProdotto(Map<String,String> params);
    void eliminaProdotto(Long idProdotto);

}
