package com.Grupo6.ReclutamientoEmpleados.Controladores;

import com.Grupo6.ReclutamientoEmpleados.Errores.ErrorWeb;
import com.Grupo6.ReclutamientoEmpleados.Servicios.EmpleadorServicio;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/registro-empleador")
public class EmpleadorControlador {
    
    @Autowired
    private EmpleadorServicio empleadorServicio;
    
    @GetMapping("")
    public String registro(){
        return "registro";
    }
    
    @PostMapping("")
    public String registrarNuevoEmpleador(Model model,@RequestParam String username,@RequestParam String password,@RequestParam String password2,@RequestParam MultipartFile foto){
        try {
            empleadorServicio.save(username,password,password2, foto);
            
            return "redirect:/";
        } catch (ErrorWeb e) {
            model.addAttribute("error",e.getMessage());
            model.addAttribute("username",username);
            return "registro";
        } catch (IOException ex) {
            model.addAttribute("error",ex.getMessage());
            return "registro";
        }
    }
    
}
