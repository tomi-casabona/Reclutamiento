
package com.Grupo6.ReclutamientoEmpleados.Servicios;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleador;
import com.Grupo6.ReclutamientoEmpleados.Errores.ErrorWeb;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.EmpleadorRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadorServicio{
    
    @Autowired
    private EmpleadorRepositorio empleadorRepositorio;
    
    @Transactional
    public Empleador save(Empleador empleador) throws ErrorWeb{
        
        if (empleador.getNombreEmpresa()==null){
            throw new ErrorWeb("El nombre de la empresa no puede ser nulo");
        }
        
        return empleadorRepositorio.save(empleador);
    }

}
