package com.generation.negozio.controllers;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.negozio.models.Utente;
import com.generation.negozio.services.ServiceUtente;

import jakarta.servlet.http.HttpSession;




@Controller
public class UtenteController {
    @Autowired
    private ServiceUtente serviceUtente;
    @Autowired
    private ApplicationContext applicationContext;
   


    //per mette di registrare un utenete,svolge la funzione di legare i dati dalla form al controller
    //vedro se i dati sono inseriti correttamenre
    //che la password sia uguale a qulla di conferma
    //che l'username non sia gia presente
    //Modelattribute prende dalla form i dati che ha le proprità uguali a quelle dell'oggetto

    //httpsESSION rappresenta la sessione http ,quando un utente si registra ,rappresenta quella pagina,permette
    //di salvare le informazioni dello user che sta usando quella pagina
    //ci serve per mantenere i dati dello user,
    //pagina di registrazione,come utnete siamo legati a d un url specifico,hon hhtpsSession,possiamo salvare i dati di quella sessione
    //cosi quandol'utente manda una richiesta di registrazione,vengano mantenuti lo stato di quello Userù
    //teniamo raccia dei dati dello user
    //per non riloggarsi ogni volta,cosi una volta che si registra,rimane registrato
    @GetMapping("/formRegistrazione")
    public String register(Model model) {
        Utente u=applicationContext.getBean("utente",Utente.class);
        model.addAttribute("utente", u);
        return "registrazioneUtente.html";
    }
    @PostMapping("/register")
    public String registerUser(@RequestParam("confermaPassword") String confermaPassword,
    @ModelAttribute Utente utente,Model model,
    HttpSession session,
    @RequestParam Map<String,String> allParams) {
//verifico che lo user non sia già poresente del db,grazie al HttpSession

if(serviceUtente.findByUsername(utente.getUsername())) {

    model.addAttribute("error", "Username già presente");
    return "registrazioneUtente.html";    
    }

    //verifica che la email non sia già presente
  
    //settare un numero massimo di caratteri per la password
    if(!utente.getPassword().equals(confermaPassword)) {
        model.addAttribute("error", "Password diverse");
        return "registrazioneUtente.html";
    }
    //inserisco lo user nel db
    serviceUtente.add(allParams);
    //salvo lo user nella sessione
    session.setAttribute("utente", utente);
    return "confermaRegistrazione.html";

    
}

}