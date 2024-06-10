package interfaces;

import models.Entity;

import java.util.List;

public interface IServiceCliente {

    List<Entity> bevande();
    List<Entity> ingredienti();
    List<Entity> listaCompleta();
    List<Entity> cercaBevandaPerIngrediente(int id);
    Entity cercaBevandaPerNome(String nome);

}
