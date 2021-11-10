

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
    
    @Query("select c from Empleado c where c.categoria= :categoria")
    List<Categoria> listAllByCategoria(@Param("categoria") String categoria);

}
