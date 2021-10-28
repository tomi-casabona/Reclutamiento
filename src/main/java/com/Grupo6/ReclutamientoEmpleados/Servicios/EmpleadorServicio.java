import com.Grupo6.ReclutamientoEmpleados.Entidades.Categoria;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleador;
import com.Grupo6.ReclutamientoEmpleados.Errores.ErrorWeb;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.EmpleadorRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadorServicio {
    
    @Autowired
    private Empleador empleador;
    
    @Autowired
    private EmpleadorRepositorio empleadorRepositorio;
    
    @Transactional
    public Empleador save(Empleador empleador) throws ErrorWeb{
        
        if (empleador.getNombreEmpresa() == null){
            throw new ErrorWeb("El nombre de la empresa no debe estar vacio");
        }
        
        return empleadorRepositorio.save(empleador);
    }
    
}
