package com.Grupo6.ReclutamientoEmpleados.Entidades;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Empleador extends Usuario{

    private String nombreEmpresa;
    
    @OneToOne
    private Foto foto;

    public Empleador() {
    }
    
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    
}
