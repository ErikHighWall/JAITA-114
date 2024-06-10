package com.generation.negozio.controllers;

import java.security.Provider.Service;
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
import com.generation.negozio.services.ServiceUtente;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;





/*una classe controller  ha il compito di associare le richieste fatte dal client
 * alle risorse che devono essere utilizzate per sopddisfarle
 * https->certificato di sicurezza
 * 
 */
@Controller
public class AppController {

    //metodo che restituisce la paginma di benvenuto
    //quando l'utente digita localhost:8080,va nella home
    //visualizzera la pagina main.html con la lista degli alimenti
    @Autowired
    private IServiceAlimentari serviceAlimentari;

    @Autowired
    private ServiceUtente serviceUtente;

    //questo a riga 20 è il dato quindi nell'MVC è il model

    //la view è la pagina html

    //per indicareil percorso dellla pagina html devo associare la pagina al metodo
    @GetMapping("/") //associa metodo alla pagina solo / è homepage
    public String home(HttpSession session) {
        if (session.getAttribute("loggato")==null) {
            return "redirect:/formLogin";
        }else {
            return "main.html";
        }

        


    }
    @GetMapping("/formLogin")
    public String login() {
        return "formLogin.html";
    }
 //   @GetMapping("/formRegistrazione")

    @PostMapping("/login")
    public String login(@RequestParam ("username") String username,
    @RequestParam ("password") String password, Model model,
    HttpSession session) {
        /*questo metodo deve prendere in input i dati passati dall'utente
         * e verificare che sono corretti
         * cioè verifico che esista un utente con uqell username e password associata
         * se l'utente esiste lo reindrezizzo nella pagina di login
         * 
         */
        //verifico che l'utente esista e che la password associata sia corretta
     Utente utenteLoggato=serviceUtente.findByUsernameAndPassword(username, password);

     //verifico che l'utente sia loggato
     if(utenteLoggato==null) {
            model.addAttribute("error", "Username e password errata");
            return "formLogin.html";
        }else{
            //in quest caso i dati sono corretti posso salvare l'utente nella sessione
            session.setAttribute("loggato", "ok");
            session.setAttribute("utente", utenteLoggato);
            //verificare ruolo utente
            String ruolo=utenteLoggato.getRuolo();
            if(ruolo.equalsIgnoreCase("admin")) {
                return "redirect:/";
            }else if(ruolo.equalsIgnoreCase("user")) {
                return "redirect:/";

            }else{
                session.setAttribute("loggato", null);
                return "redirect:/";
            }
        }
        
    }

//metodo che permette all'utente di fare logout dalla sessione
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        //prendo la sessione che ha attreibuto loggato
        session.setAttribute("loggato", null);
        session.setAttribute("utente", null);
        return "redirect:/";
    }

}


    

