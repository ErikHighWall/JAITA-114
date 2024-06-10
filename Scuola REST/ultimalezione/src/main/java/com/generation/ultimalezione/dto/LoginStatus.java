package com.generation.ultimalezione.dto;

import lombok.Getter;

@Getter
public class LoginStatus {
    
    private String token;

    public void setToken(String ruolo, Long idPersona){
        this.token = ruolo + "-" + idPersona;
    }

    public String getRuolo(){
        return token.split("-")[0];
    }

    public Long getIdPersona(){
        return Long.parseLong(token.split("-")[1]);
    }
}
