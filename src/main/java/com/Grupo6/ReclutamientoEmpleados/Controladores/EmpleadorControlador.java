package com.Grupo6.ReclutamientoEmpleados.Controladores;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleador;
import com.Grupo6.ReclutamientoEmpleados.Errores.ErrorWeb;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.EmpleadorRepositorio;
import com.Grupo6.ReclutamientoEmpleados.Servicios.EmpleadorServicio;
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

@Controller
@RequestMapping("/interfaz-empleador")
public class EmpleadorControlador {
    
    @Autowired
    private EmpleadorServicio empleadorServicio;
    
    @Autowired
    private EmpleadorRepositorio empleadorRepositorio;
    
    @GetMapping("")
    public String registro(){
        return "registro";
    }
    
    @GetMapping("/editar-empleador")
    @PreAuthorize("hasAnyRole('ROLE_EMPLEADOR')")
    public String editarEmpleador(Model model,@RequestParam(required = false) String id){
        if (id != null){
            Optional<Empleador> optional= empleadorRepositorio.findById(id);
            if (optional.isPresent()){
                model.addAttribute("empleador",optional.get());
            }else{
                return "redirect:/editar-empleador";
            }
        }else{
            model.addAttribute("empleador",new Empleador());
        }
        
        return "editar-empleador";
    }
    
    @PostMapping("/save-empleador")
    @PreAuthorize("hasAnyRole('ROLE_EMPLEADOR')")
    public String saveEmpleador(Model model,RedirectAttributes redirectAttributes,@ModelAttribute Empleador empleador){
        try {
            empleadorServicio.save(empleador);
            redirectAttributes.addFlashAttribute("success","Empleador cambiado con exito");
        } catch (ErrorWeb e) {
            e.printStackTrace();
            return "redirect:/interfaz-empleador/editar-empleador";
        }
        
        return "redirect:/interfaz-empleador";
    }
}
