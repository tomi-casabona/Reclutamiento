package com.Grupo6.ReclutamientoEmpleados.Entidades;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Empleador extends Usuario{

    private String nombreEmpresa;
    
    @ManyToOne
    private Categoria categoria;

    public Empleador() {
    }

    public Empleador(String nombreEmpresa, Categoria categoria) {
        this.nombreEmpresa = nombreEmpresa;
        this.categoria = categoria;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
}
