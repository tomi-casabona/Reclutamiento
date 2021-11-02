package com.Grupo6.ReclutamientoEmpleados.Servicios;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleador;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Usuario;
import com.Grupo6.ReclutamientoEmpleados.Enums.Rol;
import com.Grupo6.ReclutamientoEmpleados.Errores.ErrorWeb;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio implements UserDetailsService {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            Usuario user = usuarioRepositorio.findByUsername(username);
            User usuario;

            List<GrantedAuthority> authorities = new ArrayList<>();

            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRol()));

            return new User(username, user.getContrasenha(), authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("El usuario no existe");
        }
    }
    
    @Transactional
    public Usuario crearUsuarioEmpresa(String nombre_usuario,String contraseña,String contraseña2,String nombre_empresa) throws ErrorWeb{
        Usuario usuario=new Usuario();
        
        Empleador empleador1=new Empleador(nombre_empresa);
        
        EmpleadorServicio empleadorServicio= new EmpleadorServicio();
        
        if (nombre_usuario==null){
            throw new ErrorWeb("El nombre de usuario no puede ser nulo");
        }
        
        if(contraseña.isEmpty() ||contraseña==null || contraseña2==null || contraseña2.isEmpty()){
            throw new ErrorWeb("La contraseña no puede estar vacia");
        }
        
        usuario.setNombre_usuario(nombre_usuario);
        
        BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        
        if(contraseña.equals(contraseña2)){
               usuario.setContrasenha(encoder.encode(contraseña)); 
            }else{
                throw new ErrorWeb("Las contraseñas deben coincidir");
            }
        
        Empleador empleador=empleadorServicio.save(empleador1);
        
        usuario.setEmpleador(empleador);
        
        usuario.setRol(Rol.EMPRESA);
        
        return usuarioRepositorio.save(usuario);
    }
    
    @Transactional
    public Usuario save(Usuario usuario) throws ErrorWeb{
        if (usuario.getNombre_usuario()==null){
            throw new ErrorWeb("Ingrese un nombre de usuario correcto");
        }
        
        if (usuario.getContrasenha()==null && usuario.getContrasenha().length() <=5){
            throw new ErrorWeb("Ingrese una contraseña correcta");
        }
        
        return usuarioRepositorio.save(usuario);
    }
    
}
