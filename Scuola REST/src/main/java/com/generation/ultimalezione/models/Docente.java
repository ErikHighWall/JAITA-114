package com.generation.ultimalezione.models;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Docente extends Persona{

    private List<Classe> classi;
    
}
