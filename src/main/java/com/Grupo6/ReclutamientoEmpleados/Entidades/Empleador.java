package com.Grupo6.ReclutamientoEmpleados.Entidades;

import javax.persistence.Entity;

@Entity
public class Empleador extends Usuario{

    private String nombreEmpresa;
    
    public Empleador() {
    }

    public Empleador(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    
}
