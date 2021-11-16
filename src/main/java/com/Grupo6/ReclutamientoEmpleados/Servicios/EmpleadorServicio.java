
package com.Grupo6.ReclutamientoEmpleados.Servicios;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleador;
import com.Grupo6.ReclutamientoEmpleados.Enums.Rol;
import com.Grupo6.ReclutamientoEmpleados.Errores.ErrorWeb;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.EmpleadorRepositorio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmpleadorServicio{
    
    @Autowired
    private EmpleadorRepositorio empleadorRepositorio;
    
    @Transactional
    public Empleador save(Empleador empleador) throws ErrorWeb{
        
        if (empleador==null){
            throw new ErrorWeb("El usuario no puede ser nulo");
        }
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        empleador.setContrasenha(encoder.encode(empleador.getContrasenha()));
        
        empleador.setRol(Rol.EMPRESA);
        
        return empleadorRepositorio.save(empleador);
    }

    public Empleador findByName(String nombre){
        return empleadorRepositorio.findByUsername(nombre);
    }
}
