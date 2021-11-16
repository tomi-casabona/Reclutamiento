
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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        if (empleador==null){
            throw new ErrorWeb("El usuario no puede ser nulo");
        }
        
        if (empleador.getContrasenha().length() <= 5 || empleador.getContrasenha().isEmpty()){
            throw new ErrorWeb("Ingrese una contraseña correcta");
        }else{
            empleador.setContrasenha(encoder.encode(empleador.getContrasenha()));
        }
        
        if (empleador.getNombreEmpresa()==null){
            throw new ErrorWeb("Ingrese un nombre de empresa");
        }
        
        if (empleador.getNombre_usuario()==null){
            throw new ErrorWeb("Ingrese un nombre de usuario");
        }
        
        empleador.setRol(Rol.EMPRESA);
        
        return empleadorRepositorio.save(empleador);
    }

    public Empleador findByName(String nombre){
        return empleadorRepositorio.findByUsername(nombre);
    }
}
