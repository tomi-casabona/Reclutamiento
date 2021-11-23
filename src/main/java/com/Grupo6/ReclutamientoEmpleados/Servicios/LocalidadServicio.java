

package com.Grupo6.ReclutamientoEmpleados.Servicios;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Localidad;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.LocalidadRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomi
 */
@Service
public class LocalidadServicio {

    @Autowired
    private LocalidadRepositorio localidadRepositorio;
    
    public List<Localidad> listarLocalidad (){
        return localidadRepositorio.findAll();
    }
}
