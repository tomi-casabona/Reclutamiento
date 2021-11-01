
package com.Grupo6.ReclutamientoEmpleados.Repositorios;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    @Query("SELECT c FROM Usuario c WHERE c.nombre_usuario= :nombre_usuario")
    public Usuario findByUsername(@Param("nombre_usuario") String username);
}
