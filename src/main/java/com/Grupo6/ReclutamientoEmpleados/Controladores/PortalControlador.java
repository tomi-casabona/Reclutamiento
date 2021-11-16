
package com.Grupo6.ReclutamientoEmpleados.Controladores;

import com.Grupo6.ReclutamientoEmpleados.Repositorios.EmpleadoRepositorio;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.UsuarioRepositorio;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.function.ServerRequest;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @GetMapping("/")
    public String index(){
        return "index";
    }
    
    @GetMapping("/registrar-empleador")
    public String registroEmpleador(ModelMap model){
        return "registro-empleador";
    }
    
}
