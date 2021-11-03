

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
    empleado.setPosiblereubucacion(posibleReubicacion);
    empleado.setEstudiosAlcanzados(estudiosAlcanzados);
    
    empleado.setOtrosDatos(otrosDatos);   
    
    empleadoRepositorio.save(empleado);
    return empleado;
}

public void validarEmpleado(String username,String password,String password2, String nombre, String apellido,
        Date fechaNac,String email, Sexo sexo, EstudiosAlcanzados estudiosAlcanzados,
        MultipartFile foto,PosibleReubicacion posibleReubicacion, String numeroTelefonico,MovilidadPropia movilidadPropia
        , List<Categoria> categorias, DisponibilidadHoraria disponibilidadHoraria, CarnetConducir carnetConducir
        , String otrosDatos) throws ErrorWeb{
    
        if (username == null){
            throw new ErrorWeb("El nombre de usuario no debe estar vacio");
        }        
        if (password == null || password.isEmpty() || password2 == null || password2.isEmpty()){
            throw new ErrorWeb("La contraseña no debe estar vacia");
        }        
         if (password != password2){
            throw new ErrorWeb("Las contraseñas no coinciden");
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


}
