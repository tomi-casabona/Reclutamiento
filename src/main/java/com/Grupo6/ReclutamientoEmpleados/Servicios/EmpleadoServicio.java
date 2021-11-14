

package com.Grupo6.ReclutamientoEmpleados.Servicios;


import com.Grupo6.ReclutamientoEmpleados.Entidades.Categoria;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Foto;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleado;
import com.Grupo6.ReclutamientoEmpleados.Enums.CarnetConducir;
import com.Grupo6.ReclutamientoEmpleados.Enums.DisponibilidadHoraria;
import com.Grupo6.ReclutamientoEmpleados.Enums.EstudiosAlcanzados;
import com.Grupo6.ReclutamientoEmpleados.Enums.MovilidadPropia;
import com.Grupo6.ReclutamientoEmpleados.Enums.PosibleReubicacion;
import com.Grupo6.ReclutamientoEmpleados.Enums.Sexo;
import com.Grupo6.ReclutamientoEmpleados.Errores.ErrorWeb;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.EmpleadoRepositorio;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tomi
 */
@Service
public class EmpleadoServicio {
    
    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;
    @Autowired
    private FotoServicio fotoServicio;
    @Autowired 
    private CategoriaServicio categoriaServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
       
    
    
public List<Empleado> listAll (){
    
    return empleadoRepositorio.findAll();
    
}

@Transactional
public Empleado save(Empleado empleado) throws IOException, ErrorWeb{
    
    if (empleado == null) {
        throw new ErrorWeb("el empleado no existe");        
    }
     
    return empleadoRepositorio.save(empleado);
    
}

public Empleado modificarEmpleado (String id, String nombre, String apellido,
        Date fechaNac,String email, Sexo sexo, EstudiosAlcanzados estudiosAlcanzados,
        MultipartFile foto,PosibleReubicacion posibleReubicacion, String numeroTelefonico,MovilidadPropia movilidadPropia
        , List<Categoria> categorias, DisponibilidadHoraria disponibilidadHoraria, CarnetConducir carnetConducir
        , String otrosDatos) throws ErrorWeb, IOException{
    
      validarModif(id, nombre, apellido, fechaNac, email, sexo, estudiosAlcanzados, foto, posibleReubicacion, numeroTelefonico, movilidadPropia, categorias, disponibilidadHoraria, carnetConducir, otrosDatos);
    
       Empleado empleado = empleadoRepositorio.findById(id).get();
    
          empleado.setNombre(nombre);
    empleado.setApellido(apellido);
    
    empleado.setFechaNac(fechaNac);
    
    empleado.setEmail(email);
    empleado.setNumeroTelefonico(numeroTelefonico);
    
    empleado.setCategorias(categorias);
    
    Foto fotografia = fotoServicio.guardar(foto);    
    
    empleado.setFoto(fotografia);    
     
    empleado.setSexo(sexo);
    empleado.setDisponibilidadHoraria(disponibilidadHoraria);    
    empleado.setCarnetConducir(carnetConducir);
    empleado.setMovilidadPropia(movilidadPropia);  
    empleado.setPosiblereubicacion(posibleReubicacion);
    empleado.setEstudiosAlcanzados(estudiosAlcanzados);
    
   
    
    empleadoRepositorio.save(empleado);
    return empleado;
}

public void validarEmpleado(String username,String password,String password2, String nombre, String apellido,
        Date fechaNac,String email, Sexo sexo, EstudiosAlcanzados estudiosAlcanzados,
        MultipartFile foto,PosibleReubicacion posibleReubicacion, String numeroTelefonico,MovilidadPropia movilidadPropia
        , List<Categoria> categorias, DisponibilidadHoraria disponibilidadHoraria, CarnetConducir carnetConducir
        ) throws ErrorWeb{
    
        if (username == null){
            throw new ErrorWeb("El nombre de usuario no debe estar vacio");
        }       
        
        if (password == null || password.isEmpty()){
            throw new ErrorWeb("La contraseña no debe estar vacia");   
        }
        if (password2 == null || password2.isEmpty()){
            throw new ErrorWeb("Debe completar el capo de validacion de contraseña");   
        }
        if (password.length() < 6){
            throw new ErrorWeb("la contraseña debe tener al menos seis caracteres");   
        } 
        
        
         if (nombre == null || nombre.length() < 3){
            throw new ErrorWeb("El nombre debe contener al menos 3 caracteres");
        }
         if (apellido == null || apellido.length() < 2){
            throw new ErrorWeb("El apellido debe contener al menos 2 caracteres");
        }
         if (fechaNac == null) {
            throw new ErrorWeb("Ingrese fecha de nacimiento");
        }
         
         Date hoy = new Date();
         
          if ( hoy.getYear() - fechaNac.getYear() <= 18 ) {
            throw new ErrorWeb("Debe ser mayor de edad");
        }        
        
         if (email == null){
            throw new ErrorWeb("Ingrese su email");
        }
         if (sexo == null){
            throw new ErrorWeb("Ingrese su sexo");
        }
         if (estudiosAlcanzados == null){
            throw new ErrorWeb("Ingrese nivel de estudios alcanzado");
        }
        
         if (foto == null){
            throw new ErrorWeb("Ingrese una imagen");
        }     
         if (posibleReubicacion == null){
            throw new ErrorWeb("Ingrese si esta dispuesto a reubicarse");
        }     
         if (numeroTelefonico == null || numeroTelefonico.length() != 10){
            throw new ErrorWeb("Ingrese su numero telefonico con diez digitos, sin 0 y sin 15 ej: 3417123123");
        }     
         if (categorias.isEmpty()){
            throw new ErrorWeb("Ingrese al menos una categoria");
        }     
         if (disponibilidadHoraria == null){
            throw new ErrorWeb("Ingrese su disponibilidad horaria");
        }     
         if (carnetConducir == null){
            throw new ErrorWeb("Ingrese su tipo de carnet de conducir si posee, o no");
        }    
}
        public void validarModif(String id, String nombre, String apellido,
        Date fechaNac,String email, Sexo sexo, EstudiosAlcanzados estudiosAlcanzados,
        MultipartFile foto,PosibleReubicacion posibleReubicacion, String numeroTelefonico,MovilidadPropia movilidadPropia
        , List<Categoria> categorias, DisponibilidadHoraria disponibilidadHoraria, CarnetConducir carnetConducir
        , String otrosDatos) throws ErrorWeb{    
        
         if (id == null){
            throw new ErrorWeb("Ingrese id");
        }
         if (nombre == null){
            throw new ErrorWeb("Ingrese nombre");
        }
         if (apellido == null){
            throw new ErrorWeb("Ingrese apellido");
        }
         if (fechaNac == null){
            throw new ErrorWeb("Ingrese fecha de nacimiento");
        }
         if (email == null){
            throw new ErrorWeb("Ingrese su email");
        }
         if (sexo == null){
            throw new ErrorWeb("Ingrese su sexo");
        }
         if (estudiosAlcanzados == null){
            throw new ErrorWeb("Ingrese nuvel de estudios alcanzado");
        }
         if (email == null){
            throw new ErrorWeb("Ingrese su email");
        }
         if (foto == null){
            throw new ErrorWeb("Ingrese una imagen");
        }     
         if (posibleReubicacion == null){
            throw new ErrorWeb("Ingrese si esta dispuesto a reubicarse");
        }     
         if (numeroTelefonico == null || numeroTelefonico.length() != 10){
            throw new ErrorWeb("Ingrese su numero telefonico con diez digitos, sin 0 y sin 15 ej: 3417123123");
        }     
         if (categorias.isEmpty()){
            throw new ErrorWeb("Ingrese al menos una categoria");
        }     
         if (disponibilidadHoraria == null){
            throw new ErrorWeb("Ingrese su disponibilidad horaria");
        }     
         if (carnetConducir == null){
            throw new ErrorWeb("Ingrese su tipo de carnet de conducir si posee, o no");
        }     
         if (otrosDatos == null){
            throw new ErrorWeb("Ingrese experiencias laborales o una carta de presentacion breve");
        }     
    } 
    
    public List<Empleado> listAllByCategoria(String categoria){
        List<Empleado> lista= new ArrayList<>();
        
        List<Empleado> lista1= empleadoRepositorio.findAll();
        
        for (Empleado empleado : lista1) {
            if (empleado.getCategorias().contains(categoria)){
                lista.add(empleado);
            }
        }
        
        return lista1;
    }
    
    public Empleado findById(String id){
        return empleadoRepositorio.findById(id).get();
    }

}
