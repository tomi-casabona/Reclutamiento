
package com.Grupo6.ReclutamientoEmpleados.Controladores;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Usuario;
import com.Grupo6.ReclutamientoEmpleados.Errores.ErrorWeb;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.UsuarioRepositorio;
import com.Grupo6.ReclutamientoEmpleados.Servicios.UsuarioServicio;
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
@RequestMapping("/registro")
public class UsuarioControlador {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @GetMapping("/registro")
    public String registro(){
        return "registro";
    }
    
    @PostMapping("/registro-empresa")
    public String registrarNuevoEmpleador(Model model,@RequestParam String username,@RequestParam String password,@RequestParam String password2,@RequestParam String nombre_empresa){
        try {
            usuarioServicio.crearUsuarioEmpresa(username,password,password2, nombre_empresa);
            
            return "redirect:/registro-empresa";
        } catch (ErrorWeb e) {
            model.addAttribute("error",e.getMessage());
            model.addAttribute("username",username);
            return "registro";
        }
    }
    
    @GetMapping("/modificar-usuario")
    @PreAuthorize("hasAnyRole('ROLE_EMPLEADOR','ROLE_EMPLEADO')")
    public String modificarUsuario(Model model,@RequestParam(required = false) String id){
        if (id != null){
            Optional<Usuario> optional= usuarioRepositorio.findById(id);
            if (optional.isPresent()){
                model.addAttribute("usuario",optional.get());
            }else{
                return "redirect:/modificar-usuario";
            }
        }else{
            model.addAttribute("usuario",new Usuario());
        }
        
        return "modificar-usuario";
    }
    
    @PostMapping("/save-usuario")
    @PreAuthorize("hasAnyRole('ROLE_EMPLEADOR','ROLE_EMPLEADO')")
    public String saveUsuario(Model model,RedirectAttributes redirectAttributes,@ModelAttribute Usuario usuario){
        try {
            usuarioServicio.save(usuario);
            redirectAttributes.addFlashAttribute("success","Datos cambiados con exito");
        } catch (ErrorWeb e) {
            e.printStackTrace();
            return "redirect:/registro/modificar-usuario";
        }
        
        return "redirect:/lista-usuario";
    }
}
