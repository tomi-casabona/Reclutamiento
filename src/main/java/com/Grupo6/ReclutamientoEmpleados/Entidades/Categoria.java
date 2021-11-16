
package com.Grupo6.ReclutamientoEmpleados.Entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Categoria {

    @Id
    private String id;
    
    private String nombre;

    public Categoria() {
    }

    public Categoria(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre.toUpperCase();
    }
    
    
    
}