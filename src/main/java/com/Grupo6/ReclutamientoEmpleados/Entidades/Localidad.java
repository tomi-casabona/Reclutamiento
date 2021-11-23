

package com.Grupo6.ReclutamientoEmpleados.Entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Tomi
 */
@Entity
public class Localidad {
    
    @Id
    private String id;
    
    private String nombre;

  
      public Localidad(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }  
      public Localidad() {          
    }
    
    

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
