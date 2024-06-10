package com.generation.ultimalezione.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Studente extends Persona{
    
    private Classe classe;


}
