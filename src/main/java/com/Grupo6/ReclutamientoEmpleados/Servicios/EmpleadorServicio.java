package com.Grupo6.ReclutamientoEmpleados.Servicios;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleador;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Foto;
import com.Grupo6.ReclutamientoEmpleados.Errores.ErrorWeb;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.EmpleadorRepositorio;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmpleadorServicio {

    @Autowired
    private EmpleadorRepositorio empleadorRepositorio;
    
    @Autowired
    private FotoServicio fotoServicio;
    
    @Transactional
    public Empleador save(String username,String password,String password2,MultipartFile archivo) throws ErrorWeb, IOException{
        Empleador empleador=new Empleador();
        
        if (username==null){
            throw new ErrorWeb("El nombre de usuario no puede ser nulo");
        }
        
        if(password.isEmpty() ||password==null || password2==null || password2.isEmpty()){
            throw new ErrorWeb("La contraseña no puede estar vacia");
        }
        
        if (archivo == null){
            throw new ErrorWeb("La foto no puede ser nula");
        }else{
            Foto foto = fotoServicio.guardar(archivo);
            
            empleador.setFoto(foto);
        }
        
        return empleadorRepositorio.save(empleador);
    }
    
    @Transactional
    public void eliminarEmpleador(String id) throws ErrorWeb{
        Empleador empleador = empleadorRepositorio.findById(id).get();
        Optional<Empleador> respuesta = empleadorRepositorio.findById(id);
        
        if (respuesta.isPresent()){
            empleadorRepositorio.delete(empleador);
        }else{
            throw new ErrorWeb("Ingrese un id correcto");
        }
        
    }
    
}
