package com.Grupo6.ReclutamientoEmpleados.Servicios;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Usuario;
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

@Service
public class UsuarioServicio implements UserDetailsService {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            Usuario user = usuarioRepositorio.findByUsername(username);
            org.springframework.security.core.userdetails.User usuario;

            List<GrantedAuthority> authorities = new ArrayList<>();

            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRol()));

            return new org.springframework.security.core.userdetails.User(username, user.getContrasenha(), authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("El usuario no existe");
        }
    }
}
