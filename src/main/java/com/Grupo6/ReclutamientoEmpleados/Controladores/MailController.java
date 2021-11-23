
package com.Grupo6.ReclutamientoEmpleados.Controladores;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleado;
import com.Grupo6.ReclutamientoEmpleados.Servicios.EmpleadoServicio;
import com.Grupo6.ReclutamientoEmpleados.Servicios.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/filtrar/visualizar-perfil/contactar/enviar-mail")
public class MailController {
    
    @Autowired
    private MailService mailService;
    
    @Autowired
    private EmpleadoServicio empleadoServicio;
    
    @GetMapping("")
    public String index(@RequestParam (required=false) String id,Model model) {
        model.addAttribute("empleado",empleadoServicio.findById(id));
        return "enviar-mail";
    }
    
    @PostMapping("/enviarmail")
    public String enviarMail(@RequestParam (required=false) String id, @RequestParam String asunto, @RequestParam String contenido,RedirectAttributes redirectAttributes) {
        Empleado empleado= empleadoServicio.findById(id);
        mailService.enviarMail(empleado.getEmail(), asunto, contenido);
        redirectAttributes.addFlashAttribute("success","Mail enviado con éxito");
        return "redirect:/";
    }
}
