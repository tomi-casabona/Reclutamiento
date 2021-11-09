
package com.Grupo6.ReclutamientoEmpleados.Controladores;

import com.Grupo6.ReclutamientoEmpleados.Servicios.CategoriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    private CategoriaServicio categoriaServicio;
    
    @GetMapping("/")
    public String index(){
        return "index";
    }
    
    @GetMapping("/dont-account")
    public String indexDHA(){
        return "index_dont_have_account";
    }
    
    @GetMapping("/registrar-empleador")
    public String registroEmpleador(ModelMap model){
        return "registro-empleador";
    }
    
    @GetMapping("/busqueda")
    public String buscar(ModelMap model){
        model.addAttribute("categoria",categoriaServicio.listaCategorias());
        return "busqueda";
    }
}
