package com.Grupo6.ReclutamientoEmpleados.Servicios;

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
import java.util.Date;
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
    private FotoServicio fotoServicio;

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
    public Usuario crearUsuarioEmpresa(String nombre_usuario, String contrase??a, String contrase??a2, String nombre_empresa) throws ErrorWeb {

        Empleador empleador1 = new Empleador();
        empleador1.setContrasenha(contrase??a);
        empleador1.setNombreEmpresa(nombre_empresa);
        empleador1.setNombre_usuario(nombre_usuario);
        empleador1.setRol(Rol.EMPRESA);

        List<Empleador> empleadores = empleadorRepositorio.findAll();

        for (Empleador empleadore : empleadores) {
            if (nombre_empresa.equals(empleadore.getNombreEmpresa())) {
                throw new ErrorWeb("Esta empresa ya se encuentra registrada");
            }
        }

        validarUsername(nombre_usuario);

        if (nombre_usuario == null || nombre_usuario.isEmpty()) {
            throw new ErrorWeb("El nombre de usuario no puede ser nulo");
        }

        if (contrase??a.isEmpty() || contrase??a == null || contrase??a2 == null || contrase??a2.isEmpty() || contrase??a.length() <= 5 || contrase??a2.length() <= 5) {
            throw new ErrorWeb("Ingrese una contrase??a v??lida de m??s de 5 caracteres");
        }

        if (nombre_empresa == null || nombre_empresa.isEmpty()) {
            throw new ErrorWeb("Ingrese un nombre de empresa correcto");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (contrase??a.equals(contrase??a2)) {
            empleador1.setContrasenha(encoder.encode(contrase??a));
        } else {
            throw new ErrorWeb("Las contrase??as deben coincidir");
        }

        return empleadorRepositorio.save(empleador1);
    }

    @Transactional
    public Usuario save(Usuario usuario) throws ErrorWeb {
        if (usuario.getNombre_usuario() == null) {
            throw new ErrorWeb("Ingrese un nombre de usuario correcto");
        }

        if (usuario.getContrasenha() == null || usuario.getContrasenha().length() <= 5) {
            throw new ErrorWeb("Ingrese una contrase??a correcta");
        }

        return usuarioRepositorio.save(usuario);
    }

    @Transactional
    public Usuario crearUsuarioEmpleado(String password2, Empleado empleado, MultipartFile foto) throws ErrorWeb, IOException {

        if (foto == null || foto.isEmpty()) {
            throw new ErrorWeb("La foto no puede estar vacia");
        }else{
        Foto fotox = fotoServicio.guardar(foto);
           empleado.setFoto(fotox);
        }

        if (empleado.getLocalidad() == null) {
            throw new ErrorWeb("Ingrese una localidad v??lida");
        }

     

        validarUsername(empleado.getNombre_usuario());

        empleadoServicio.validarEmpleado(empleado.getNombre_usuario(), empleado.getContrasenha(), password2, empleado.getNombre(), empleado.getApellido(), empleado.getFechaNac(),
                empleado.getEmail(), empleado.getSexo(), empleado.getEstudiosAlcanzados(), foto, empleado.getPosiblereubicacion(),
                empleado.getNumeroTelefonico(), empleado.getMovilidadPropia(), empleado.getCategorias(), empleado.getDisponibilidadHoraria(),
                empleado.getCarnetConducir());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (empleado.getContrasenha().equals(password2)) {
            empleado.setContrasenha(encoder.encode(empleado.getContrasenha()));
        } else {
            throw new ErrorWeb("Las contrase??as deben coincidir");
        }

        empleado.setRol(Rol.CANDIDATO);

        return empleadoRepositorio.save(empleado);
    }

    public void validarUsername(String username) throws ErrorWeb {

        if (usuarioRepositorio.findByUsername(username) != null) {
            throw new ErrorWeb("El nombre de usuario ya existe");

        }
    }

}
