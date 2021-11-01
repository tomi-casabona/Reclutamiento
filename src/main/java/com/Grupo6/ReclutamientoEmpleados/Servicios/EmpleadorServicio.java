
package com.Grupo6.ReclutamientoEmpleados.Servicios;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleador;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Foto;
import com.Grupo6.ReclutamientoEmpleados.Errores.ErrorWeb;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.EmpleadorRepositorio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.userdetails.User;

@Service
public class EmpleadorServicio implements UserDetailsService{
    
    @Autowired
    private FotoServicio fotoServicio;
    
    @Autowired
    private EmpleadorRepositorio empleadorRepositorio;
    
    @Transactional
    public Empleador save(String username,String password,String password2,MultipartFile archivo) throws ErrorWeb, IOException, IOException{
        Empleador empleador=new Empleador();
        
        if (username == null){
            throw new ErrorWeb("El nombre de la empresa no debe estar vacio");
        }
        
        if (password == null || password.isEmpty() || password2 == null || password2.isEmpty()){
            throw new ErrorWeb("La contraseña no debe estar vacia");
        }
        
        if (archivo == null){
            throw new ErrorWeb("Ingrese una imagen");
        }
        
        empleador.setNombre_usuario(username);
        empleador.setContrasenha(password);
        
        Foto foto= fotoServicio.guardar(archivo);
        empleador.setFoto(foto);
        
        return empleadorRepositorio.save(empleador);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            Empleador empleador = empleadorRepositorio.findByUsername(username);
            User usuario;

            List<GrantedAuthority> authorities = new ArrayList<>();

            authorities.add(new SimpleGrantedAuthority("ROLE_" + empleador.getRol()));

            return new User(username, empleador.getContrasenha(), authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("El usuario no existe");
        }
    }
    
}
