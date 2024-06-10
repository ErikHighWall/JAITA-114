package models;

import java.util.List;

public class Bevanda extends  Entity{

    private String nome;
    private double prezzo;
   private List<Ingrediente> listaIngredienti;
    //tramite questa lista posso associare un bevanda aggli ingredienti che la compongono
    //cioè riesco a creare un collegamento tra le benvade e gli ingredienti rispecchiando
    //la stessa ssociazione che cè in mysqlo



    public Bevanda(){}
    public Bevanda(int id, String nome, double prezzo,List<Ingrediente> listaIngredienti) {
        super(id);
        this.nome = nome;
        this.prezzo = prezzo;
        this.listaIngredienti=listaIngredienti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public List<Ingrediente> getListaIngredienti() {
        return listaIngredienti;
    }

    public void setListaIngredienti(List<Ingrediente> listaIngredienti) {
        this.listaIngredienti = listaIngredienti;
    }

    @Override
    public String toString() {
        return "Bevanda "+ super.toString()+
                "nome='" + nome + '\'' +
                ", prezzo=" + prezzo +"€"+
                (!(listaIngredienti ==null) ?stampaIngredienti():" anghè miga");
               /* "lista ingredienti"+(!listaIngredienti.isEmpty()?stampaIngredienti():
                "non ci sono ingredienti associati a quella bevanda")*/
    }
    private String stampaIngredienti(){
        String ris="\nElenco ingredienti: \n";
        for(Ingrediente i:listaIngredienti)
        {
            ris+= i.getNome()+", ";
        }
        ris=ris.substring(0,ris.length()-2); // PER TOGLIERE LA VIRGOLA FINALE
        return ris;
    }

}
