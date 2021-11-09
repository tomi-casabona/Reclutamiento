/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Grupo6.ReclutamientoEmpleados.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    @GetMapping("")
    public String login(Model model,@RequestParam(required = false) String error,@RequestParam(required = false) String username,@RequestParam(required = false) String logout){
        if(error != null){
            model.addAttribute("error","El usuario ingresado o la contraseña son incorrectos");
        }
        
        if(username != null){
            model.addAttribute("username",username);
        }
        
        return "login";
    }
}
