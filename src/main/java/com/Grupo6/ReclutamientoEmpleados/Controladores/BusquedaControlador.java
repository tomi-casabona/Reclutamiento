
package com.Grupo6.ReclutamientoEmpleados.Controladores;

import com.Grupo6.ReclutamientoEmpleados.Servicios.CategoriaServicio;
import com.Grupo6.ReclutamientoEmpleados.Servicios.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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
    
    @GetMapping("")
    @PreAuthorize("hasAnyRole('ROLE_EMPRESA')")
    public String buscar(ModelMap model){
        model.addAttribute("categoria",categoriaServicio.listaCategorias());
        return "busqueda";
    }
    
    @GetMapping("/filtrar")
    @PreAuthorize("hasAnyRole('ROLE_EMPRESA')")
    public String filtro(ModelMap model,@RequestParam String categoria){
        model.addAttribute("empleados",empleadoServicio.listAllByCategoria(categoria));
        return "listaDeCandidatos";
    }
}
