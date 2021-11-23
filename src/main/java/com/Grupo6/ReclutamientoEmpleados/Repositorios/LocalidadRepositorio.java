

package com.Grupo6.ReclutamientoEmpleados.Repositorios;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tomi
 */
@Repository
    public interface LocalidadRepositorio  extends JpaRepository<Localidad, String>{
     @Query("SELECT l FROM Localidad l WHERE l.nombre = :nombre")
    public Localidad findByname(@Param("nombre") String nombre);

}
