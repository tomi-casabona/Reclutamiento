
package com.Grupo6.ReclutamientoEmpleados.Controladores;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleado;
import com.Grupo6.ReclutamientoEmpleados.Errores.ErrorWeb;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.EmpleadoRepositorio;
import com.Grupo6.ReclutamientoEmpleados.Servicios.EmpleadoServicio;
import com.Grupo6.ReclutamientoEmpleados.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/foto")
public class FotoControlador {
    
    @Autowired
    private EmpleadoServicio empleadoServicio;
    
    @GetMapping("/usuario")
    public ResponseEntity<byte[]> fotoUsuario(@RequestParam String id){
        try {
            Empleado empleado = empleadoServicio.findById(id);
            if(empleado.getFoto()==null){
                throw new ErrorWeb("El usuario no tiene foto");
            }
            byte[] foto= empleado.getFoto().getContenido();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            
            return new ResponseEntity<>(foto,headers, HttpStatus.OK);
            
        } catch (ErrorWeb e) {
            e.getMessage();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } 
}
