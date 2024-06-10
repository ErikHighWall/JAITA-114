package com.generation.ultimalezione.models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class Persona extends Entity{

    private String nome;
    private String cognome;

    @JsonProperty("datanascita")
    private Date data_nascita;

    /*quando non voglio far arrivare al front end alcune informazioni dell'oggetto 
     * potenzialmente sensibili, posso usare l'annotazione @JsonIgnore
     * per dire a spring che in fase di conversione 
     * da oggetto java a json, non deve includere il campo annotato
     * di conseguenza il dato arriverà senza quelle informazioni al front end
     */
    @JsonIgnore
    private String username;

    @JsonIgnore
    private String password;



}
