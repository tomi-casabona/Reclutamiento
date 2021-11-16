package com.Grupo6.ReclutamientoEmpleados.Servicios;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Categoria;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleado;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleador;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Foto;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Usuario;
import com.Grupo6.ReclutamientoEmpleados.Enums.Rol;
import com.Grupo6.ReclutamientoEmpleados.Errores.ErrorWeb;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.EmpleadoRepositorio;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.EmpleadorRepositorio;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.UsuarioRepositorio;
import java.io.IOException;
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
    private CategoriaServicio categoriaServicio;
    
    @Autowired
    private EmpleadorRepositorio empleadorRepositorio;
    
    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;
    
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
        
        Empleador empleador1 = new Empleador();
        empleador1.setContrasenha(contraseña);
        empleador1.setNombreEmpresa(nombre_empresa);
        empleador1.setNombre_usuario(nombre_usuario);
        empleador1.setRol(Rol.EMPRESA);
        
        List<Empleador> empleadores = empleadorRepositorio.findAll();
        
        for (Empleador empleadore : empleadores) {
            if (nombre_empresa.equals(empleadore.getNombreEmpresa())) {
                throw new ErrorWeb("Esta empresa ya esta registrada");
            }
        }
        
        validarUsername(nombre_usuario);
        
//        List<Usuario> usuarios = usuarioRepositorio.findAll();
//        
//        for (Usuario usuario1 : usuarios) {
//            if (nombre_usuario.equalsIgnoreCase(usuario1.getNombre_usuario())) {
//                throw new ErrorWeb("Ese nombre de usuario ya esta en uso");
//            }
//        }
        
        if (nombre_usuario == null || nombre_usuario.isEmpty()) {
            throw new ErrorWeb("El nombre de usuario no puede ser nulo");
        }
        
        if (contraseña.isEmpty() || contraseña == null || contraseña2 == null || contraseña2.isEmpty() || contraseña.length() <= 5 || contraseña2.length() <= 5) {
            throw new ErrorWeb("Ingrese una contraseña valida de mas de 5 caracteres");
        }
        
        if (nombre_empresa == null || nombre_empresa.isEmpty()) {
            throw new ErrorWeb("Ingrese un nombre de empresa correcto");
        }
        
        usuario.setNombre_usuario(nombre_usuario);
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        if (contraseña.equals(contraseña2)) {
            usuario.setContrasenha(encoder.encode(contraseña));
        } else {
            throw new ErrorWeb("Las contraseñas deben coincidir");
        }
//
//        Empleador empleador = empleadorServicio.save(empleador1);

//        usuario.setEmpleador(empleador);
//
//        usuario.setRol(Rol.EMPRESA);
        return empleadorRepositorio.save(empleador1);
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
    public Usuario crearUsuarioEmpleado(String password2, Empleado empleado, MultipartFile foto) throws ErrorWeb, IOException {
        
        Foto fotox = fotoServicio.guardar(foto);
        
        empleado.setFoto(fotox);   
        
        validarUsername(empleado.getNombre_usuario());
        
//        Usuario usuario = new Usuario();
//        
//        List<Empleado> empleados = empleadoRepositorio.findAll();
//        
//        for (Empleado empleado1 : empleados) {
//            
//            if (empleado.getNombre_usuario().equalsIgnoreCase(empleado1.getNombre_usuario())) {
//                throw new ErrorWeb("El nombre de usuario ya existe");
//            }
//        }
//        for (Empleado empleado1 : empleados) {
//            
//            if (empleado.getEmail().equalsIgnoreCase(empleado1.getEmail())) {
//                throw new ErrorWeb("El email ya corresponde a un usuario");
//            }
//        }        
//        
        empleadoServicio.validarEmpleado(empleado.getNombre_usuario(), empleado.getContrasenha(), password2, empleado.getNombre(), empleado.getApellido(), empleado.getFechaNac(),
                empleado.getEmail(), empleado.getSexo(), empleado.getEstudiosAlcanzados(), foto, empleado.getPosiblereubicacion(),
                empleado.getNumeroTelefonico(), empleado.getMovilidadPropia(), empleado.getCategorias(), empleado.getDisponibilidadHoraria(),
                empleado.getCarnetConducir());
        
//        usuario.setNombre_usuario(empleado.getNombre_usuario());
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        if (empleado.getContrasenha().equals(password2)) {
            empleado.setContrasenha(encoder.encode(empleado.getContrasenha()));
        } else {
            throw new ErrorWeb("Las contraseñas deben coincidir");
        }
        
        empleado.setRol(Rol.CANDIDATO);
        
//        Empleado empleado3 = empleadoRepositorio.save(empleado);
//        
//        usuario.setEmpleado(empleado3);
//        
//        usuario.setRol(Rol.CANDIDATO);
        
        return empleadoRepositorio.save(empleado);
    }
    
    public void validarUsername(String username) throws ErrorWeb{
    
        if (usuarioRepositorio.findByUsername(username) != null) {
            throw new ErrorWeb("El nombre de usuario ya existe");
            
        }
    }
    
}
