package com.generation.negozio.models;

public class Entity implements IMappable{

    private Long id;

    public Entity() {
    }

    public Entity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Entity [id=" + id + "]";
    }

    
}
