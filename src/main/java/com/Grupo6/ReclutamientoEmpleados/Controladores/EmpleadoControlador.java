

package com.Grupo6.ReclutamientoEmpleados.Controladores;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleado;
import com.Grupo6.ReclutamientoEmpleados.Errores.ErrorWeb;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.CategoriaRepositorio;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.EmpleadoRepositorio;
import com.Grupo6.ReclutamientoEmpleados.Servicios.CategoriaServicio;
import com.Grupo6.ReclutamientoEmpleados.Servicios.EmpleadoServicio;
import com.Grupo6.ReclutamientoEmpleados.Servicios.LocalidadServicio;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    
    @Autowired
    private CategoriaServicio categoriaServicio;
    
    @Autowired
    private LocalidadServicio localidadServicio;
    
    @GetMapping("")
    public String verPerfil(ModelMap model){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("empleado",empleadoServicio.findByUsername(auth.getName()));
        return "visualizaciones";
    }
    
    @GetMapping("/editar")
    @PreAuthorize("hasAnyRole('ROLE_CANDIDATO')")
    public String editarEmpleado(Model model,@RequestParam(required = false) String id){
        if (id != null){
            Optional<Empleado> optional= empleadoRepositorio.findById(id);
            if (optional.isPresent()){
                model.addAttribute("categoria",categoriaServicio.listaCategorias());
                model.addAttribute("localidad", localidadServicio.listarLocalidad());
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
    @PreAuthorize("hasAnyRole('ROLE_CANDIDATO')")
    public String saveEmpleado(@RequestParam (required = false) String id ,Model model,RedirectAttributes redirectAttributes,@ModelAttribute Empleado empleado,MultipartFile imagen) throws IOException{
        try {
            empleadoServicio.save(id,empleado,imagen);
            redirectAttributes.addFlashAttribute("success","Empleado cambiado con éxito");
        } catch (ErrorWeb e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/interfaz-empleado/editar?id=" + id;
        } 
        return "redirect:/";
    }
}