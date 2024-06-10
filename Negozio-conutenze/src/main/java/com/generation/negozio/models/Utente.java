package com.generation.negozio.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Utente extends Entity implements Serializable{
    //marca uqesta classe ,dicendo che p una classe serializzabile
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private String email;
    private String ruolo;
}

