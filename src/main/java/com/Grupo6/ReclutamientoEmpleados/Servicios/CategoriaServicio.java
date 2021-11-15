

package com.Grupo6.ReclutamientoEmpleados.Servicios;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Categoria;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.CategoriaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomi
 */
@Service
public class CategoriaServicio {
    
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    
    public List<Categoria> listaCategorias (){
        
    return categoriaRepositorio.findAll() ;
    
    }
    
  public Categoria findByid(String id){
      return categoriaRepositorio.findById(id).get();
  }
  
    public List<Categoria> save(List<Categoria> categoria){
        return categoriaRepositorio.saveAll(categoria);
    }
   
}