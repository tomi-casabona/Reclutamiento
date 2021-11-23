package com.Grupo6.ReclutamientoEmpleados.Controladores;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleador;
import com.Grupo6.ReclutamientoEmpleados.Errores.ErrorWeb;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.EmpleadorRepositorio;
import com.Grupo6.ReclutamientoEmpleados.Servicios.EmpleadorServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/editar-empleador")
public class EmpleadorControlador {
    
    @Autowired
    private EmpleadorServicio empleadorServicio;
    
    @Autowired
    private EmpleadorRepositorio empleadorRepositorio;
    
    @GetMapping("")
    @PreAuthorize("hasAnyRole('ROLE_EMPRESA')")
    public String editarEmpleador(Model model){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String id=empleadorServicio.findByName(auth.getName()).getId();
        if (id != null){
            Optional<Empleador> optional= empleadorRepositorio.findById(id);
            if (optional.isPresent()){
                model.addAttribute("empleador",optional.get());
            }else{
                return "redirect:/";
            }
        }else{
            model.addAttribute("empleador",new Empleador());
        }
        
        return "editar-empleador";
    }
    
    @PostMapping("/save-empleador")
    @PreAuthorize("hasAnyRole('ROLE_EMPRESA')")
    public String saveEmpleador(@RequestParam (required = false) String id,Model model,RedirectAttributes redirectAttributes,@ModelAttribute Empleador empleador){
        try {
            empleadorServicio.save(id,empleador);
            redirectAttributes.addFlashAttribute("success","Empleador cambiado con éxito");
        } catch (ErrorWeb e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/editar-empleador?id=" + id;
        }
        
        return "redirect:/";
    }
}
