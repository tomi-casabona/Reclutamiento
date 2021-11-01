package com.Grupo6.ReclutamientoEmpleados.Repositorios;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadorRepositorio extends JpaRepository<Empleador, String>{
    @Query("SELECT c FROM Empleador c WHERE c.nombre_usuario= :nombre_usuario")
    public Empleador findByUsername(@Param("nombre_usuario") String username);
}
