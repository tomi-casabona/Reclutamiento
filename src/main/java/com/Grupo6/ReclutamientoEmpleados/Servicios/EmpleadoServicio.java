

package com.Grupo6.ReclutamientoEmpleados.Servicios;

import java.util.ArrayList;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Categoria;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Foto;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleado;
import com.Grupo6.ReclutamientoEmpleados.Enums.CarnetConducir;
import com.Grupo6.ReclutamientoEmpleados.Enums.DisponibilidadHoraria;
import com.Grupo6.ReclutamientoEmpleados.Enums.MovilidadPropia;
import com.Grupo6.ReclutamientoEmpleados.Enums.PosibleReubicacion;
import com.Grupo6.ReclutamientoEmpleados.Enums.SecundarioCompleto;
import com.Grupo6.ReclutamientoEmpleados.Enums.Sexo;
import com.Grupo6.ReclutamientoEmpleados.Enums.TerciarioCompleto;
import com.Grupo6.ReclutamientoEmpleados.Enums.UniversitarioCompleto;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.EmpleadoRepositorio;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.transaction.reactive.TransactionalOperatorExtensionsKt.transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tomi
 */
@Service
public class EmpleadoServicio {
    
    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;
//    @Autowired
//    private FotoServicio fotoServicio;
//    
//    
    
public List<Empleado> listAll (){
    
    return empleadoRepositorio.findAll();
    
}

@Transactional
public void crearEmpleado (String nombre,String otrosDatos, Sexo sexo,TerciarioCompleto terciarioCompleto, UniversitarioCompleto universitarioCompleto , SecundarioCompleto secundarioCompleto ,MultipartFile foto,PosibleReubicacion posibleReubicacion, String numeroTelefonico, String apellido,MovilidadPropia movilidadPropia, Date fechaNac,String email, List<Categoria> categorias, DisponibilidadHoraria disponibilidadHoraria, CarnetConducir carnetConducir){
    Empleado empleado = new Empleado();
    
    empleado.setNombre(nombre);
    empleado.setApellido(apellido);
    empleado.setFechaNac(fechaNac);
    empleado.setEmail(email);
    empleado.setNumeroTelefonico(numeroTelefonico);
    empleado.setOtrosDatos(otrosDatos);
   
    
    empleado.setCategorias(categorias);
    
    Foto fotografia = fotoServicio.crearFoto(foto);    
    
    empleado.setFoto(fotografia);
   
    
     
    empleado.setSexo(sexo);
    empleado.setDisponibilidadHoraria(disponibilidadHoraria);    
    empleado.setCarnetConducir(carnetConducir);
    empleado.setMovilidadPropia(movilidadPropia);  
    empleado.setPosiblereubucacion(posibleReubicacion);
    empleado.setSecundarioCompleto(secundarioCompleto);
    empleado.setTerciarioCompleto(terciarioCompleto);
    empleado.setUniversitarioCompleto(universitarioCompleto);
 
    
    
    
}


}
