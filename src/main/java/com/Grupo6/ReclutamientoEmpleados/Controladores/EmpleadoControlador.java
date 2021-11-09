

package com.Grupo6.ReclutamientoEmpleados.Controladores;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleado;
import com.Grupo6.ReclutamientoEmpleados.Errores.ErrorWeb;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.EmpleadoRepositorio;
import com.Grupo6.ReclutamientoEmpleados.Servicios.EmpleadoServicio;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Tomi
 */

@Controller
@RequestMapping("/interfaz-empleado")
public class EmpleadoControlador {
    
    @Autowired
    private EmpleadoServicio empleadoServicio;
    
    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;
    
    @GetMapping("")
    public String registro(){
        return "registroEmpleador";
    }
    
    @GetMapping("/editar-empleado")
    @PreAuthorize("hasAnyRole('ROLE_EMPLEADO')")
    public String editarEmpleado(Model model,@RequestParam(required = false) String id){
        if (id != null){
            Optional<Empleado> optional= empleadoRepositorio.findById(id);
            if (optional.isPresent()){
                model.addAttribute("empleado",optional.get());
            }else{
                return "redirect:/editar-empleado";
            }
        }else{
            model.addAttribute("empleado",new Empleado());
        }
        
        return "editar-empleado";
    }
    
    @PostMapping("/save-empleado")
    @PreAuthorize("hasAnyRole('ROLE_EMPLEADO')")
    public String saveEmpleado(Model model,RedirectAttributes redirectAttributes,@ModelAttribute Empleado empleado) throws IOException{
        try {
            empleadoServicio.save(empleado);
            redirectAttributes.addFlashAttribute("success","Empleado cambiado con exito");
        } catch (ErrorWeb e) {
            e.printStackTrace();
            return "redirect:/interfaz-empleado/editar-empleado";
        }
        
        return "redirect:/interfaz-empleado";
    }
}