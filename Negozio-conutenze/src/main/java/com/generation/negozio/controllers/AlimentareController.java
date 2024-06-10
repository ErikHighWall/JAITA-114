package com.generation.negozio.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.negozio.models.Alimentare;
import com.generation.negozio.models.Utente;
import com.generation.negozio.services.IServiceAlimentari;

import jakarta.servlet.http.HttpSession;



@Controller
public class AlimentareController {
        @Autowired
    private IServiceAlimentari serviceAlimentari;
 


@GetMapping("/pagina-alimentari") //associa metodo alla pagina,quando qualcuno clicca quell'elemento
    public String homeAlimentari(Model model,HttpSession session) {
     //il controller chiama il servizio
        //il servizio chiama il dao
        //il dao chiama il database
        //il database restituisce i dati
        //il dao restituisce i dati al servizio
        //il servizio restituisce i dati al controller
        //il controller restituisce i dati alla view
        boolean isAdmin = false;
    
        List<Alimentare> ris = serviceAlimentari.findAll();
       // System.out.println("fdf");
  

        model.addAttribute("alimentari", ris);
        if(session.getAttribute("utente")!=null&&session.getAttribute("utente") instanceof Utente){
            if(((Utente)session.getAttribute("utente")).getRuolo().equalsIgnoreCase("admin")){
                isAdmin=true;
                System.out.println("fdf");
           }
           
         }
         model.addAttribute("isAdmin", isAdmin);
        return "paginaAlimentari.html";
       
    
       //aggiunhgoi nell'oggetto model un atributo, dove nella prima parte si mette dove si andrà a sostiture
       //nella seconda parte si mette il dato che si vuole sostituire
               // ritorna una pagina html quando va nella home
        //il model permette di salvare nel suo interno attributi,e quindi far passarrre dei valori
        //dalla classe controller alla pagina html
    
}

//quando lutente inserirà dei dati nella form,e li potrò leggere
//nel controller per poi passarlo al back end e poi al server
@PostMapping("/inserisci-alimentare")
public String inserisciAlimentare(@RequestParam Map<String,String> params,
HttpSession session,Model model)//questo metodo permette di recuperare i dati dalla form)
{
    //queto metodo permette di recuperare i dati dalla form
    //html e inserirli nel database tramite il service
    //controllo se l'utente è un admin o uno user
    //se è un admin può inserire un alimento
    //se è un user non può inserire un alimento
//boolean isAdmin = false;
/*bject utente= session.getAttribute("utente");
if(utente!=null&& utente instanceof Utente)
{
  Utente u = (Utente)utente;
  if(u.getRuolo().equals("admin"))
  {
      isAdmin=true;
  }

}*/


/*if(session.getAttribute("utente")!=null&&session.getAttribute("utente") instanceof Utente){
   if(((Utente)session.getAttribute("utente")).getRuolo().equals("admin")){
       isAdmin=true;
       System.out.println("fdf");
   }
  
}*/
//model.addAttribute("isAdmin", isAdmin);


   
    serviceAlimentari.add(params);
    //il redirect permette di fare un'ulteriore richiesta al server,ci rimanda alla pagina/paGINA-alimentari,coè al'endpoinmt homeAlimentari
    //come se lo stesse reiseguendo,cioò di restituire nuovamente la lista agggiornata
return "redirect:/pagina-alimentari";
}


@PostMapping("/modifica-alimentare ")
public String modificaAlimentare(@RequestParam Map<String,String> params)
{
    serviceAlimentari.modificaAlimentare(params);
    return "redirect:/pagina-alimentari";
}



//http://localhost:8080/elimina-alimentare?idAlimentare=[value]

//recuper dato che ha id =value


//
@GetMapping("/elimina-alimentare")
public String eliminaAlimentare(@RequestParam(name="idAlimentare"/*idAlimentare */,
defaultValue = "0") Long idAlimentare/*value */, Model model)//prende i parametri dall etichetta che ha nome idAlimentare,che si salva in idAlimentare
{if(idAlimentare==0){//l'operazione non è andata a buon fgine
    model.addAttribute("error", "errore eliminazione "+
   " dell'alimento associato al parametro idAlimentare");
   return "paginaErrore.html";
    
}else
    serviceAlimentari.eliminaAlimentare(idAlimentare);
    return "redirect:/pagina-alimentari";
}
//http://localhost:8080/alimentare-byId?idAlimentare=[value]
@GetMapping("/alimentare-byId")
String alimentareById(@RequestParam(name="idAlimentare",defaultValue = "0") Long idAlimentare ,Model model)

{


     Alimentare a= serviceAlimentari.findById(idAlimentare);
     if(a==null)
     {
         model.addAttribute("error", "errore ricerca alimento associato al parametro idAlimentare"+
         "con id"+idAlimentare);
         return "paginaErrore.html";
     }else {
            model.addAttribute("alimentare", a);
            return "dettaglioAlimentare.html";
     }


}
}

