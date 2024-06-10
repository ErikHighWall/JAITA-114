package com.generation.negozio.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.negozio.models.Prodotto;
import com.generation.negozio.services.IServiceProdotti;



@Controller
public class ProdottiController {
    @Autowired
    private IServiceProdotti serviceProdotti;



    @GetMapping("/pagina-prodotti")
    String homeProdotti(Model model){




        List<Prodotto> ris = serviceProdotti.findAll();
        List<Prodotto> ris1 =  new ArrayList<>();

        for(int i=0;i<2;i++){
            ris1.add(ris.get(i));
        }

        model.addAttribute("prodotti", ris1);
        return "paginaProdotti.html";

    }

    @PostMapping("/inserisci-prodotto")
    String inserisciProdotto(@RequestParam Map<String,String> params){

        
        serviceProdotti.add(params);
        return "redirect:/pagina-prodotti";

    }
    @PostMapping("/modifica-prodotto")
    String modificaProdotto(@RequestParam Map<String,String> params){

        
        serviceProdotti.modificaProdotto(params);
        return "redirect:/pagina-prodotti";

    }
    @GetMapping("/elimina-prodotto")
public String eliminaAlimentare(@RequestParam(name="idProdotto",
defaultValue = "0") Long idProdotto/*value */, Model model)
{
    if(idProdotto==0){
    model.addAttribute("error", "errore eliminazione "+
   " dell'alimento associato al parametro idAlimentare");
   return "paginaErrore.html";
    
}else{
    serviceProdotti.eliminaProdotto(idProdotto);
    return "redirect:/pagina-prodotti";
}
   
}
    

    
}
