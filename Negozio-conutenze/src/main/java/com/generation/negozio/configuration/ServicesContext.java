package com.generation.negozio.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import com.generation.negozio.services.IServiceAlimentari;
import com.generation.negozio.services.IServiceProdotti;
import com.generation.negozio.services.ServiceAlimentari;
import com.generation.negozio.services.ServiceProdotti;
import com.generation.negozio.services.ServiceUtente;

@Controller
public class ServicesContext {
    @Bean
    public IServiceAlimentari serviceAlimentari() {
        return new ServiceAlimentari();
    }
   // @Qualifier metter eun etichetta per ambiguità
    @Bean
    public IServiceProdotti serviceProdotti() {
        return new ServiceProdotti();
    }
@Bean
public ServiceUtente serviceUtente() {
    return new ServiceUtente();

}
}