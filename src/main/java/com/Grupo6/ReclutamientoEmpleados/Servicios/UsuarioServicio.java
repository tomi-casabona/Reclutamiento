package com.Grupo6.ReclutamientoEmpleados.Servicios;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Categoria;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleado;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleador;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Foto;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Usuario;
import com.Grupo6.ReclutamientoEmpleados.Enums.CarnetConducir;
import com.Grupo6.ReclutamientoEmpleados.Enums.DisponibilidadHoraria;
import com.Grupo6.ReclutamientoEmpleados.Enums.EstudiosAlcanzados;
import com.Grupo6.ReclutamientoEmpleados.Enums.MovilidadPropia;
import com.Grupo6.ReclutamientoEmpleados.Enums.PosibleReubicacion;
import com.Grupo6.ReclutamientoEmpleados.Enums.Rol;
import com.Grupo6.ReclutamientoEmpleados.Enums.Sexo;
import com.Grupo6.ReclutamientoEmpleados.Errores.ErrorWeb;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.EmpleadorRepositorio;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.UsuarioRepositorio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private EmpleadoServicio empleadoServicio;
    @Autowired
    private EmpleadorServicio empleadorServicio;
    @Autowired
    private FotoServicio fotoServicio;

    @Autowired
    private EmpleadorRepositorio empleadorRepositorio;
    
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
    public Usuario crearUsuarioEmpresa(String nombre_usuario, String contraseña, String contraseña2, String nombre_empresa) throws ErrorWeb {
        Usuario usuario = new Usuario();

        Empleador empleador1 = new Empleador(nombre_empresa);
        
        List<Empleador> empleadores=empleadorRepositorio.findAll();
        
        for (Empleador empleadore : empleadores) {
            if(nombre_empresa.equals(empleadore.getNombreEmpresa())){
                throw new ErrorWeb("Esta empresa ya esta registrada");
            }
        }
        
        List<Usuario> usuarios=usuarioRepositorio.findAll();
        
        for (Usuario usuario1 : usuarios) {
            if (nombre_usuario.equalsIgnoreCase(usuario1.getNombre_usuario())){
                throw new ErrorWeb("Ese nombre de usuario ya esta en uso");
            }
        }

        if (nombre_usuario == null || nombre_usuario.isEmpty()) {
            throw new ErrorWeb("El nombre de usuario no puede ser nulo");
        }

        if (contraseña.isEmpty() || contraseña == null || contraseña2 == null || contraseña2.isEmpty() || contraseña.length()<=5 || contraseña2.length()<=5) {
            throw new ErrorWeb("Ingrese una contraseña valida de mas de 5 caracteres");
        }
        
        if(nombre_empresa==null || nombre_empresa.isEmpty()){
            throw new ErrorWeb("Ingrese un nombre de empresa correcto");
        }

        usuario.setNombre_usuario(nombre_usuario);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (contraseña.equals(contraseña2)) {
            usuario.setContrasenha(encoder.encode(contraseña));
        } else {
            throw new ErrorWeb("Las contraseñas deben coincidir");
        }

        Empleador empleador = empleadorServicio.save(empleador1);

        usuario.setEmpleador(empleador);

        usuario.setRol(Rol.EMPRESA);

        return usuarioRepositorio.save(usuario);
    }

    @Transactional
    public Usuario save(Usuario usuario) throws ErrorWeb {
        if (usuario.getNombre_usuario() == null) {
            throw new ErrorWeb("Ingrese un nombre de usuario correcto");
        }

        if (usuario.getContrasenha() == null || usuario.getContrasenha().length() <= 5) {
            throw new ErrorWeb("Ingrese una contraseña correcta");
        }

        return usuarioRepositorio.save(usuario);
    }

    @Transactional
    public Usuario crearUsuarioEmpleado(String username, String password, String password2, String nombre, String apellido,
            Date fechaNac, String email, Sexo sexo, EstudiosAlcanzados estudiosAlcanzados,
            MultipartFile foto, PosibleReubicacion posibleReubicacion, String numeroTelefonico, MovilidadPropia movilidadPropia,
             List<Categoria> categorias, DisponibilidadHoraria disponibilidadHoraria, CarnetConducir carnetConducir,
             String otrosDatos) throws ErrorWeb, IOException {

        Usuario usuario = new Usuario();

        Empleado empleado = new Empleado();

        empleadoServicio.validarEmpleado(username, password, password2, nombre, apellido, fechaNac,
                email, sexo, estudiosAlcanzados, foto, posibleReubicacion,
                numeroTelefonico, movilidadPropia, categorias, disponibilidadHoraria,
                carnetConducir, otrosDatos);

        usuario.setNombre_usuario(username);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (password.equals(password2)) {
            usuario.setContrasenha(encoder.encode(password));
        } else {
            throw new ErrorWeb("Las contraseñas deben coincidir");
        }
     
        usuario.setEmpleado(empleado);

        usuario.setRol(Rol.CANDIDATO);
        
        
    empleado.setNombre(nombre);
    empleado.setApellido(apellido);
    
    empleado.setFechaNac(fechaNac);
    
    empleado.setEmail(email);
    empleado.setNumeroTelefonico(numeroTelefonico);
    
    empleado.setCategorias(categorias);
    
    Foto fotografia = fotoServicio.guardar(foto);    
    
    empleado.setFoto(fotografia);    
     
    empleado.setSexo(sexo);
    empleado.setDisponibilidadHoraria(disponibilidadHoraria);    
    empleado.setCarnetConducir(carnetConducir);
    empleado.setMovilidadPropia(movilidadPropia);  
    empleado.setPosiblereubucacion(posibleReubicacion);
    empleado.setEstudiosAlcanzados(estudiosAlcanzados);
    
    empleado.setOtrosDatos(otrosDatos);   
    
      empleadoServicio.save(empleado);
    

        return usuarioRepositorio.save(usuario);
    }

}
