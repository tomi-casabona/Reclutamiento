

package com.Grupo6.ReclutamientoEmpleados.Repositorios;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tomi
 */

@Repository
public interface CategoriaRepositorio  extends JpaRepository<Categoria, String>{
     @Query("SELECT c FROM Categoria c WHERE c.nombre = :nombre")
    public Categoria findByname(@Param("nombre") String nombre);
    
}
