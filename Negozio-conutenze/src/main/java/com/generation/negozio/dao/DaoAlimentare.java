package com.generation.negozio.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.generation.negozio.models.Alimentare;
import com.generation.negozio.models.Entity;
import com.generation.negozio.models.Factory;
import com.generation.negozio.models.IMappable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class DaoAlimentare implements IDao, IMappable {
/*tramite l'annotazione @Autowired spring è in grado di iniettare nella dipendenza conl'annotazione
 * un'istanza del tipo richiesto se esiste un Bean di quel tipo nel databse context
 * o in un context
 */
    @Autowired
    private IDatabase database;
    private String query;

    @Autowired
    private ApplicationContext applicationContext;
    //scatola con tutti i Bean


   public DaoAlimentare() {
        
    }



    @Override
    public void add(Entity e) {
        query = "insert into alimentari(nome, prezzo, data_scadenza) values(?,?,?)";
        if (e instanceof Alimentare){
            database.executeUpdate(query,
                    ((Alimentare)e).getNome(),
                    String.valueOf(((Alimentare)e).getPrezzo()),
                    String.valueOf(((Alimentare)e).getScadenza()));
        }
        System.out.println("\u001B[32m Alimento aggiunto correttamente \u001B[0m");
    }

    @Override
    public List<Map<String, String>> read(String query, String... params) {
        return database.executeQuery(query);
    }

    @Override
    public List<Map<String, String>> read() {
        query="select * from alimentari";
        return database.executeQuery(query);    }

    @Override
    public List<Entity> readAll() {
        List<Entity> ris=new ArrayList<>();
        Entity e;
        for(Map<String,String> m : read()) {
          /*prendo i bean che restituisce unm ogeto di alimentare e passargliu la mappa */
            //inserire un bean di tipo Alimentare dal context e passargli
        e = applicationContext.getBean(Alimentare.class,m);
//prende classe,nome metodo e anche il parametro che prende in input
       // applicationContext.getBean(Alimentare.class); se non cè anbiguita
        //e.fromMap(m);

            ris.add(e);
        }
        return ris;
    }
   /*il metodo getBean() dell'applicationContext 
     
fa overload e ci permette di essere più o meno specifici nel recuperare un bean
i Bean creati:
con il metodo getBean(Class classe) restituiscono un bean del
tipo della classe passata come parametro. Se ci sono più bean di quella classe
prenderà il bean con annotazione @Primary
Se non c'è l'annotazione e ci sono più bean di quella classe,
restituirà un'eccezione di tipo NoUniqueBeanDefinitionException
con il metodo getBean(String nome) restituiscono un bean
con il nome passato come parametro
Ad es. context.getBean("alimentare") restituirà un bean di
che ha come nome del metodo "alimentare"
Se ci sono più bean con lo stesso nome restituirà un'eccezione
getBean(Class classe, Object... args) restituisce un bean
del tipo della classe passata come parametro e con i parametri passati
getBean(String nome, Object... args) restituisce un bean
con il nome passato come parametro e con i parametri passati
in questo modo possiamo prendere anche i bean diversi da primary
getBean(String nome, Class classe) restituisce un bean
con il nome passato come parametro e del tipo della classe passata
*
*/
    public String printAll(){
        for(Entity e : readAll()) {
            System.out.println(e.toString());
        }return "";
    }

    @Override
    public void update(Entity e) {
        query = "update alimentari set nome = ?, prezzo = ?, data_scadenza = ? where id = ?";
        if (e instanceof Alimentare){
            database.executeUpdate(query,
                    ((Alimentare)e).getNome(),
                    String.valueOf(((Alimentare)e).getPrezzo()),
                    String.valueOf(((Alimentare)e).getScadenza()),
                    String.valueOf(((Alimentare)e).getId()));
        }
        System.out.println("\u001B[32m Alimento modificato correttamente \u001B[0m");
    }

    @Override
    public void update(int id, String proprieta, String valoreNuovo) {
        query ="update alimentari set " + proprieta + " = ? where id = ?";
        database.executeUpdate(query, valoreNuovo, String.valueOf(id));
        System.out.println("\u001B[32m Alimento modificato correttamente \u001B[0m");
    }

    @Override
    public Entity cercaPerId(Long id) {
        query="select * from alimentari where id = ?";
        Map<String,String> m;
        m= database.executeQuery(query, String.valueOf(id)).get(0);
        return applicationContext.getBean(Alimentare.class,
          m);
    }



    @Override
    public void delete(long id) {
        query="delete from alimentari where id=?";
        database.executeUpdate(query, String.valueOf(id));
        System.out.println("\u001B[32m Prodotto eliminato correttamente \u001B[0m");
    }
}
