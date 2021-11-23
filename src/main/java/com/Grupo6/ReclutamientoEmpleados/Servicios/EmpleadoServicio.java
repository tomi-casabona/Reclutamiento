package com.Grupo6.ReclutamientoEmpleados.Servicios;

import com.Grupo6.ReclutamientoEmpleados.Entidades.Categoria;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Foto;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Empleado;
import com.Grupo6.ReclutamientoEmpleados.Entidades.Localidad;
import com.Grupo6.ReclutamientoEmpleados.Enums.CarnetConducir;
import com.Grupo6.ReclutamientoEmpleados.Enums.DisponibilidadHoraria;
import com.Grupo6.ReclutamientoEmpleados.Enums.EstudiosAlcanzados;
import com.Grupo6.ReclutamientoEmpleados.Enums.MovilidadPropia;
import com.Grupo6.ReclutamientoEmpleados.Enums.PosibleReubicacion;
import com.Grupo6.ReclutamientoEmpleados.Enums.Rol;
import com.Grupo6.ReclutamientoEmpleados.Enums.Sexo;
import com.Grupo6.ReclutamientoEmpleados.Errores.ErrorWeb;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.EmpleadoRepositorio;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.LocalidadRepositorio;
import com.Grupo6.ReclutamientoEmpleados.Repositorios.UsuarioRepositorio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tomi
 */
@Service
public class EmpleadoServicio {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;
    @Autowired
    private FotoServicio fotoServicio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private LocalidadRepositorio localidadRepositorio;

    public List<Empleado> listAll() {

        return empleadoRepositorio.findAll();

    }

    @Transactional
    public Empleado save(String id, Empleado empleado, MultipartFile imagen) throws IOException, ErrorWeb {

        empleado.setNombre_usuario(validarUsername(id, empleado.getNombre_usuario()));

        validarEmpleado(empleado.getNombre_usuario(), empleado.getContrasenha(), empleado.getContrasenha(), empleado.getNombre(),
                empleado.getApellido(), empleado.getFechaNac(), empleado.getEmail(), empleado.getSexo(), empleado.getEstudiosAlcanzados(), (MultipartFile) empleado.getFoto(), empleado.getPosiblereubicacion(), empleado.getNumeroTelefonico(), empleado.getMovilidadPropia(),
                empleado.getCategorias(), empleado.getDisponibilidadHoraria(), empleado.getCarnetConducir());

        if (empleado.getLocalidad() == null) {
            throw new ErrorWeb("Ingrese una localidad válida");
        }

        if (imagen == null) {

            empleado.setFoto(empleadoRepositorio.findById(id).get().getFoto());
            
        } else {
            empleado.setFoto(fotoServicio.guardar(imagen));
        }

        if (empleado == null) {
            throw new ErrorWeb("El empleado no existe");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        empleado.setContrasenha(encoder.encode(empleado.getContrasenha()));

        empleado.setRol(Rol.CANDIDATO);

        return empleadoRepositorio.save(empleado);

    }

    public void validarEmpleado(String username, String password, String password2, String nombre, String apellido,
            Date fechaNac, String email, Sexo sexo, EstudiosAlcanzados estudiosAlcanzados,
            MultipartFile foto, PosibleReubicacion posibleReubicacion, String numeroTelefonico, MovilidadPropia movilidadPropia,
            List<Categoria> categorias, DisponibilidadHoraria disponibilidadHoraria, CarnetConducir carnetConducir
    ) throws ErrorWeb {

        if (username == null) {
            throw new ErrorWeb("El nombre de usuario no debe estar vacío");
        }

        if (password == null || password.isEmpty()) {
            throw new ErrorWeb("La contraseña no debe estar vacía");
        }
        if (password2 == null || password2.isEmpty()) {
            throw new ErrorWeb("Debe completar el campo de validación de contraseña");
        }
        if (password.length() < 6) {

            throw new ErrorWeb("La contraseña debe tener al menos seis caracteres");
        }

        if (foto == null) {
            throw new ErrorWeb("La foto no puede estar vacía.");
        }

        if (nombre == null || nombre.length() < 3) {
            throw new ErrorWeb("El nombre debe contener al menos 3 caracteres");
        }
        if (apellido == null || apellido.length() < 2) {
            throw new ErrorWeb("El apellido debe contener al menos 2 caracteres");
        }
        if (fechaNac == null) {
            throw new ErrorWeb("Ingrese su fecha de nacimiento");
        }

        Date hoy = new Date();

        if (hoy.getYear() - fechaNac.getYear() <= 18) {
            throw new ErrorWeb("Debe ser mayor de edad");
        }

        if (email == null) {
            throw new ErrorWeb("Ingrese su email");
        }
        if (sexo == null) {
            throw new ErrorWeb("Ingrese su sexo");
        }
        if (estudiosAlcanzados == null) {
            throw new ErrorWeb("Ingrese el nivel de estudios alcanzado");
        }
        
        if (posibleReubicacion == null) {
            throw new ErrorWeb("Ingrese si está dispuesto a reubicarse");
        }
        if (numeroTelefonico == null || numeroTelefonico.length() != 10) {
            throw new ErrorWeb("Ingrese su número telefónico con diez dígitos, sin 0 y sin 15 ej: 3417123123");
        }
        if (categorias.isEmpty()) {
            throw new ErrorWeb("Ingrese al menos una categoría");
        }
        if (disponibilidadHoraria == null) {
            throw new ErrorWeb("Ingrese su disponibilidad horaria");
        }
        if (carnetConducir == null) {
            throw new ErrorWeb("Ingrese su tipo de carnet de conducir, si posee, o no");
        }
        if (categorias.isEmpty() || categorias == null) {
            throw new ErrorWeb("El campo categoría está vacío");

        }
    }

    public List<Empleado> listAllByCategoriaYLocalidad(String categoria, String localidad) {
        List<Empleado> lista = new ArrayList<>();

        List<Empleado> lista1 = empleadoRepositorio.findByLocalidad(localidad);

        for (Empleado empleado : lista1) {
            for (Categoria empleado1 : empleado.getCategorias()) {
                if (empleado1.getNombre().equals(categoria)) {
                    lista.add(empleado);
                }
            }
        }

        return lista;
    }

    public Empleado findById(String id) {
        return empleadoRepositorio.findById(id).get();
    }

    public Empleado findByUsername(String username) {
        Empleado empleado = (Empleado) usuarioRepositorio.findByUsername(username);
        return empleado;
    }

    public String validarUsername(String id, String username) throws ErrorWeb {

        if (usuarioRepositorio.findById(id).get().getNombre_usuario().equals(username)) {
            return username;
        } else {
            if (usuarioRepositorio.findByUsername(username) != null) {
                throw new ErrorWeb("El nombre de usuario ya se encuentra registrado");
            } else {
                return username;
            }
        }
    }
}
