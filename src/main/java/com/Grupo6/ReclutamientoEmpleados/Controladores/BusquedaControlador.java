
package com.Grupo6.ReclutamientoEmpleados.Controladores;

import com.Grupo6.ReclutamientoEmpleados.Servicios.CategoriaServicio;
import com.Grupo6.ReclutamientoEmpleados.Servicios.EmpleadoServicio;
import com.Grupo6.ReclutamientoEmpleados.Servicios.LocalidadServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/busqueda")
public class BusquedaControlador {
    
    @Autowired
    private EmpleadoServicio empleadoServicio;
    
    @Autowired
    private CategoriaServicio categoriaServicio;
    
    @Autowired
    private LocalidadServicio localidadServicio;
    
    @GetMapping("")
    @PreAuthorize("hasAnyRole('ROLE_EMPRESA')")
    public String buscar(ModelMap model){
        model.addAttribute("categoria",categoriaServicio.listaCategorias());
        model.addAttribute("localidad",localidadServicio.listarLocalidad());
        return "busqueda";
    }
    
    @GetMapping("/filtrar")
    @PreAuthorize("hasAnyRole('ROLE_EMPRESA')")
    public String filtro(ModelMap model,@RequestParam String categoria,@RequestParam String localidad){
        model.addAttribute("empleados",empleadoServicio.listAllByCategoriaYLocalidad(categoria,localidad));
        return "listaDeCandidatos";
    }
    
    @GetMapping("/filtrar/visualizar-perfil")
    @PreAuthorize("hasAnyRole('ROLE_EMPRESA')")
    public String visualizar(@RequestParam (required=false) String id,Model model){
        model.addAttribute("empleado",empleadoServicio.findById(id));
        return "visualizaciones";
    }
    
    @GetMapping("/filtrar/visualizar-perfil/contactar")
    @PreAuthorize("hasAnyRole('ROLE_EMPRESA')")
    public String datosContacto(@RequestParam (required=false) String id,Model model){
        model.addAttribute("empleado",empleadoServicio.findById(id));
        return "datosDeContacto";
    }
}
