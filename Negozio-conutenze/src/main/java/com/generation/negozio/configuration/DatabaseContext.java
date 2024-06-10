package com.generation.negozio.configuration;
import org.springframework.context.annotation.Configuration;

import com.generation.negozio.dao.DaoAlimentare;
import com.generation.negozio.dao.DaoProdotto;
import com.generation.negozio.dao.DaoUtente;
import com.generation.negozio.dao.Database;
import com.generation.negozio.dao.IDao;
import com.generation.negozio.dao.IDatabase;

import org.springframework.context.annotation.Bean;
/* tramikte l'annotazione @confi,   questa classe sarà un oggetto che mi permetterà di definire
 * le istanze o l'istanza necessarie per il funzionamentro dell'applicazione
 * 
 *quando ci servono oggetti di n'altra classe,evita che il programmatore lo deve fare lui
 creiamo questo contenitore per le isrtanze di database
 */
@Configuration
class DatabaseContext {
    //uso questa classe per definire i bEAN(istanze) CHE mi serviranno per usare il database
    /*metodo che restituisce un0oggetto formalmente di tipo IDatabse e concretamente di tipo
     * database che contiene i metodi per inviare una query o un comando DML
     * con l'annotazione @Bean, indico che il metoodo restituirà un oggetto che sarà gestito da spring
     * generà un Bean e lo salvera nel DatabaseContex
     * 
     * di default se non dichiaro nulla in merito al numero di istanze, spring ne crea una sola
     * quindi di default è un singleton
     */
    @Bean 
    public IDatabase database() {
        return new Database();
    }//restituisce un oggetto di tipo database
@Bean
public IDao daoAlimentare() {
    return new DaoAlimentare();

}
@Bean
public IDao daoProdotto() {
    return new DaoProdotto();

}
@Bean
public DaoUtente daoUtente() {
    return new DaoUtente();
}
}