

package com.Grupo6.ReclutamientoEmpleados.Controladores;

import com.Grupo6.ReclutamientoEmpleados.Servicios.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Tomi
 */
@Controller
@RequestMapping("/interfaz-empleado")
public class EmpleadoControlador {
    
    @Autowired
    private EmpleadoServicio empleadoServicio;
    
    
    @GetMapping("/list")
    public String listarEmpleados(Model model) {
        model.addAttribute("empleados", empleadoServicio.listAll());
        return "empleados-list";
    }
    
    @GetMapping("/form")
    public String cargarEmpleado (){        
        return "empleado-form";  
    }
    
    

}
