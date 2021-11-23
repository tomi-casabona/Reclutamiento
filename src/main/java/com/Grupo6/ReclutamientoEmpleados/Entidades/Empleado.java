package com.Grupo6.ReclutamientoEmpleados.Entidades;

import com.Grupo6.ReclutamientoEmpleados.Enums.CarnetConducir;
import com.Grupo6.ReclutamientoEmpleados.Enums.DisponibilidadHoraria;
import com.Grupo6.ReclutamientoEmpleados.Enums.EstudiosAlcanzados;
import com.Grupo6.ReclutamientoEmpleados.Enums.MovilidadPropia;
import com.Grupo6.ReclutamientoEmpleados.Enums.PosibleReubicacion;
import com.Grupo6.ReclutamientoEmpleados.Enums.Sexo;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Empleado extends Usuario {

    private String nombre;
    private String apellido;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNac;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Categoria> categorias;

    
    @ManyToOne
    private Localidad localidad;

    @OneToOne
    private Foto foto;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @Enumerated(EnumType.STRING)
    private EstudiosAlcanzados estudiosAlcanzados;
    @Enumerated(EnumType.STRING)
    private DisponibilidadHoraria disponibilidadHoraria;
    @Enumerated(EnumType.STRING)
    private MovilidadPropia movilidadPropia;
    @Enumerated(EnumType.STRING)
    private PosibleReubicacion posiblereubicacion;
    @Enumerated(EnumType.STRING)
    private CarnetConducir carnetConducir;

    private String email;
    private String numeroTelefonico;
    private String experienciaLaboral;

    public Empleado() {
    }

    public Empleado(String nombre, String apellido, Date fechaNac, List<Categoria> categorias, Foto foto, Sexo sexo, EstudiosAlcanzados estudiosAlcanzados, DisponibilidadHoraria disponibilidadHoraria, MovilidadPropia movilidadPropia, PosibleReubicacion posiblereubicacion, CarnetConducir carnetConducir, String email, String numeroTelefonico, String experienciaLaboral, Localidad localidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.categorias = categorias;
        this.foto = foto;
        this.sexo = sexo;
        this.estudiosAlcanzados = estudiosAlcanzados;
        this.disponibilidadHoraria = disponibilidadHoraria;
        this.movilidadPropia = movilidadPropia;
        this.posiblereubicacion = posiblereubicacion;
        this.carnetConducir = carnetConducir;
        this.email = email;
        this.numeroTelefonico = numeroTelefonico;
        this.experienciaLaboral = experienciaLaboral;
        this.localidad = localidad;
    }

    public void addCategoria(Categoria e) {
        this.categorias.add(e);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public EstudiosAlcanzados getEstudiosAlcanzados() {
        return estudiosAlcanzados;
    }

    public void setEstudiosAlcanzados(EstudiosAlcanzados estudiosAlcanzados) {
        this.estudiosAlcanzados = estudiosAlcanzados;
    }

    public DisponibilidadHoraria getDisponibilidadHoraria() {
        return disponibilidadHoraria;
    }

    public void setDisponibilidadHoraria(DisponibilidadHoraria disponibilidadHoraria) {
        this.disponibilidadHoraria = disponibilidadHoraria;
    }

    public MovilidadPropia getMovilidadPropia() {
        return movilidadPropia;
    }

    public void setMovilidadPropia(MovilidadPropia movilidadPropia) {
        this.movilidadPropia = movilidadPropia;
    }

    public PosibleReubicacion getPosiblereubicacion() {
        return posiblereubicacion;
    }

    public void setPosiblereubicacion(PosibleReubicacion posiblereubicacion) {
        this.posiblereubicacion = posiblereubicacion;
    }

    public CarnetConducir getCarnetConducir() {
        return carnetConducir;
    }

    public void setCarnetConducir(CarnetConducir carnetConducir) {
        this.carnetConducir = carnetConducir;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getExperienciaLaboral() {
        return experienciaLaboral;
    }

    public void setExperienciaLaboral(String experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }



}
