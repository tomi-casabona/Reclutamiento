

package com.Grupo6.ReclutamientoEmpleados.Repositorios;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tomi
 */
@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, String>{

}
