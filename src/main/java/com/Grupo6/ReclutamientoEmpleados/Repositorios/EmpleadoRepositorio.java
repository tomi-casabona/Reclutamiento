

package com.Grupo6.ReclutamientoEmpleados.Repositorios;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Categoria;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tomi
 */
@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, String>{
    
    @Query("SELECT e FROM Empleado e where e.localidad.nombre like CONCAT('%',:localidad,'%')")
    public List<Empleado> findByLocalidad(@Param("localidad") String localidad);
    

}
