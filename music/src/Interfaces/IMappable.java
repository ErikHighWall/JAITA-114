package Interfaces;

import java.lang.reflect.Method;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
// questa interfaccia mi serve per fare un'operazione che si chiama REFLECTION:
// è la capacità di un oggetto di specchiarsi,di guardare se stesso,e quindi
// di conoscere che proprità e metodi possiede.
// siccome non ha senso implementare i seguenti metodi per ogni classe modello
//doabbiamo crcare di implementarla nel modo più universale possinile!!...

public interface IMappable {
    //la mappa che ricevo è composta da
    // chiave = nomeColonna/proprietà
    // valore = valoreColonna/proprietà
    default void fromMap (Map<String,String> param){
String nomeProp;//
String valoreProp;//

       // L'obiettivo del from map è prendere i valori contenuti nella mappa e passarli ai set delle proprietà dell'oggetto che usa il from map in modo automatizzato

        // this si riferisce all'istanza che chiama il metodo fromMap()
        //getClass() va a prendere la classe a cui appartiene l'istanza
        //getMethods mi restituisce un elenco dei metodo della classe,un array di metodo

        //ad esempio se sarà un oggetto di tipo Bevanda and invocare il fromMap
        //avrò un vettore restituito dal geMethods fatto soi:
        //1 cella: conterra metodo costruttore
        //2 cella,costruttore vuoto
        //3 celola metodo get nome
        //4 cellaa metodo set nome

        for(Method m :this.getClass().getMethods()){
            //beccda il nome del metodo
            // startsWith() controlla se il metodo ciclato comincia con
            //una determinata stringa
            if(m.getName().startsWith("set")){
                //substring() mi ritaglia la stringa che lo chiama
                // in questo caso il nome del setter
                //e ne ottiene una sottostringa togliendo i primi caratteri,cio è il set

                nomeProp=m.getName().substring(3);
                //se ad esempio il metodo m in questo giro del for è setNome,
                // attraverso il substring(3) otteniamo: "Nome"
                // per cambiare la lettera iniziale da N a n
                //divido il nome in due pezzi: N e "ome"
                // e trasforma la N in n attraverso Character.toLowerCase()
                 nomeProp=Character.toLowerCase(nomeProp.charAt(0))+ nomeProp.substring(1);
                //containskey restituisce true se la mappa che lo chiama
                //contiene la ciave passata come parametro
                if(param.containsKey(nomeProp)){
                    valoreProp=param.get(nomeProp);
                    //getParameters restituisce3 un array dei parametri
                    // in ingresso(tipo di parametro in ingresso)

                    //con [0] accedo al pèrimo parametro
                    //con getType mi prendo il suo tipo
                    // con getSimpleName() ottengo il nome del tipo in FORMATO STRING
                    String tipo = m.getParameters()[0].getType().getSimpleName();

                    try {
                        //il metodo invoke viene chiamato dal metodo m e accetta
                        //m sta chiedendo all'oggetto this di essere invocato
                        //in input 2 valori: l'oggetto chiamante,e i parametri che il metodo che viene chiamato necessita
                        // in input
                        switch (tipo.toLowerCase()) {
                            case "string":
                                m.invoke(this, valoreProp);
                                break;

                            case "int":
                                m.invoke(this, Integer.parseInt(valoreProp));
                                break;
                            case "double":
                                m.invoke(this, Double.parseDouble(valoreProp));
                                break;
// FACCIAMO ESEMPI ANCHE CON TIPI NON PRESENTI IN QUESTO PROGETTO
                            //DAYE.VALUEF OF() TRASfroma la stringa in ingresso in una data
                            case "date":
                                m.invoke(this, Date.valueOf(valoreProp));
                                break;
                            case "boolean":
                                m.invoke(this,valoreProp.equals("1")||
                                        valoreProp.equalsIgnoreCase("true") ||
                                        valoreProp.equalsIgnoreCase("t"));
                                break;
                            default:
                                System.err.println("attenzione non ho riconosciuto il tipo"+ tipo);
                                break;


                        }
                    }catch(Exception e){
                        e.printStackTrace();
                        System.out.println("Errore nel metodo fromMap");
                    }
                }
            }


            //beccda il nome del metodo

        }

    }
    default Map<String, String> toMap() {
        Map<String, String> ris = new HashMap<>();
        String keyProp = "";
        String valueProp = "";
        boolean check = false;
        //getClass() viene ereditato in automaticol
        for (Method m : this.getClass().getMethods()) {
            // se preferite implementare il metodo qui sopra senza l'else-if e quindi senza booleano:
            // potete inserire nel for-each:
//    if((m.getName().startsWith("get") || m.getName().startsWith("is")) &&
//            !m.getName().equalsIgnoreCase("getClass") &&
//            m.getParameterCount() == 0)
//            try {
//        int partenza = m.getName().startsWith("get") ? 3 : 2;
//        String nomeProp = m.getName().substring(partenza);
//        nomeProp = Character.toLowerCase(nomeProp.charAt(0)) + nomeProp.substring(1);
//        ris.put(nomeProp, m.invoke(this) + "");
//    }catch (Exception e) {
//        e.printStackTrace();
//    }

            if (m.getName().startsWith("get") && !m.getName().equals("getClass") ) {
                keyProp = m.getName().substring(3);
                check = true;


            } else if (m.getName().startsWith("is")) {
                keyProp = m.getName().substring(2);

                check = true;

            }
            //se il metodo trovato è un getter vado avanti
            if (check) {
                keyProp = Character.toLowerCase(keyProp.charAt(0)) + keyProp.substring(1);
                try {

// inseriamo dentro il valueDrop il ritorno del getter che invoxchaimo
                    valueProp = String.valueOf(m.invoke(this));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("Errore nel toMap" + this.getClass().getName());
                }
                ris.put(keyProp, valueProp);
                check = false;
            }

        }
        return ris;
    }
}
