
package com.Grupo6.ReclutamientoEmpleados.Controladores;

import com.Grupo6.ReclutamientoEmpleados.Servicios.EmpleadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    private EmpleadorServicio empleadorServicio;
    
    @GetMapping("/")
    public String index(){
        return "inicio";
    }
    
    @GetMapping("/registrar-empleador")
    public String registroEmpleador(ModelMap model){
        return "registro-empleador";
    }
}
