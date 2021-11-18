
package com.Grupo6.ReclutamientoEmpleados.Servicios;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleador;
import com.Grupo6.ReclutamientoEmpleados.Enums.Rol;
import com.Grupo6.ReclutamientoEmpleados.Errores.ErrorWeb;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.EmpleadorRepositorio;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.UsuarioRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmpleadorServicio{
    
    @Autowired
    private EmpleadorRepositorio empleadorRepositorio;
    
    @Autowired 
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Transactional
    public Empleador save(String id,Empleador empleador) throws ErrorWeb{
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        empleador.setNombre_usuario(validarUsername(id,empleador.getNombre_usuario()));
        
        if (empleador==null){
            throw new ErrorWeb("El usuario no puede ser nulo");
        }
        
        if (empleador.getContrasenha().length() <= 5 || empleador.getContrasenha().isEmpty()){
            throw new ErrorWeb("Ingrese una contraseña correcta");
        }else{
            empleador.setContrasenha(encoder.encode(empleador.getContrasenha()));
        }

        if (empleador.getNombreEmpresa()==null || empleador.getNombreEmpresa().isEmpty()){
            throw new ErrorWeb("Ingrese un nombre de empresa");
        }
        
        if (empleador.getNombre_usuario()==null || empleador.getNombre_usuario().isEmpty()){
            throw new ErrorWeb("Ingrese un nombre de usuario");
        }
        
        empleador.setRol(Rol.EMPRESA);
        
        return empleadorRepositorio.save(empleador);
    }

    public Empleador findByName(String nombre){
        return empleadorRepositorio.findByUsername(nombre);
    }
    
    public String validarUsername(String id,String username) throws ErrorWeb{
    
        if (usuarioRepositorio.findById(id).get().getNombre_usuario().equals(username)) {
            return username;
        }else{
            if (usuarioRepositorio.findByUsername(username) != null){
                throw new ErrorWeb("El nombre de usuario ya se encuentra registrado");
            }else{
                return username;
            }
        }
    }
}
