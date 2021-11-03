

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
    
    
//    
//      @PostMapping("/save")
//    public String guardarLibro(ModelMap model, @RequestParam (required = false) String titulo, @RequestParam (required = false) MultipartFile archivo, @RequestParam (required = false) Integer anio, @RequestParam (required = false) Integer ejemplares, @RequestParam (required = false) String autor, @RequestParam (required = false) String editorial) throws ErrorServicio, IOException{
//        try {
//         libroServices.cargarLibro(archivo, titulo, anio, ejemplares, autor, editorial);
//
//        } catch (ErrorServicio e) {
//            model.put("error", e.getMessage());
//            
//            model.put("foto", archivo);
//            model.put("titulo", titulo);
//            model.put("anio", anio);
//            model.put("ejemplares", ejemplares);
//            model.put("autor", autor);
//            model.put("editorial", editorial);
//            return "libro-form";
//        }
//            return "redirect:/empleado/list";
//    }


}
