package com.Grupo6.ReclutamientoEmpleados.Entidades;

import com.Grupo6.ReclutamientoEmpleados.Enums.CarnetConducir;
import com.Grupo6.ReclutamientoEmpleados.Enums.DisponibilidadHoraria;
import com.Grupo6.ReclutamientoEmpleados.Enums.EstudiosAlcanzados;
import com.Grupo6.ReclutamientoEmpleados.Enums.MovilidadPropia;
import com.Grupo6.ReclutamientoEmpleados.Enums.PosibleReubicacion;
import com.Grupo6.ReclutamientoEmpleados.Enums.Sexo;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Empleado extends Usuario {
    
    private String nombre;
    private String apellido;
    private Date fechaNac;
   
    @OneToMany
    private List<Categoria> categorias;
    
    @OneToOne
    private Foto foto;
    
    private Sexo sexo;
    private EstudiosAlcanzados estudiosAlcanzados;
    private DisponibilidadHoraria disponibilidadHoraria;
    private MovilidadPropia movilidadPropia;
    private PosibleReubicacion posiblereubucacion;
    private CarnetConducir carnetConducir;
    private String email;
    private String numeroTelefonico;
    private String experienciaLaboral;    
    private String otrosDatos;
    

    public Empleado() {
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

    public List<Categoria> getCategoria() {
        return getCategorias();
    }

    public void setCategoria(List<Categoria> categoria) {
        this.setCategorias(categoria);
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
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

    public PosibleReubicacion getPosiblereubucacion() {
        return posiblereubucacion;
    }

    public void setPosiblereubucacion(PosibleReubicacion posiblereubucacion) {
        this.posiblereubucacion = posiblereubucacion;
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

    public String getOtrosDatos() {
        return otrosDatos;
    }

    public void setOtrosDatos(String otrosDatos) {
        this.otrosDatos = otrosDatos;
    }

    /**
     * @return the categorias
     */
    public List<Categoria> getCategorias() {
        return categorias;
    }

    /**
     * @param categorias the categorias to set
     */
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    /**
     * @return the estudiosAlcanzados
     */
    public EstudiosAlcanzados getEstudiosAlcanzados() {
        return estudiosAlcanzados;
    }

    /**
     * @param estudiosAlcanzados the estudiosAlcanzados to set
     */
    public void setEstudiosAlcanzados(EstudiosAlcanzados estudiosAlcanzados) {
        this.estudiosAlcanzados = estudiosAlcanzados;
    }

    /**
     * @return the foto
     */
    public Foto getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(Foto foto) {
        this.foto = foto;
    }
    
    
    
}

